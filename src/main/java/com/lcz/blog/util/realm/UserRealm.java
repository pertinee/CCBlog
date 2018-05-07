package com.lcz.blog.util.realm;

import java.util.List;

import com.lcz.blog.bean.PermissionBean;
import com.lcz.blog.bean.RoleBean;
import com.lcz.blog.bean.UserBean;
import com.lcz.blog.service.PermissionService;
import com.lcz.blog.service.RoleService;
import com.lcz.blog.service.UserService;
import com.lcz.blog.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的指定Shiro验证用户登录的类
 * @author abel.lin
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String)super.getAvailablePrincipal(principals);
        UserBean member = userService.queryUserByName(currentUsername);
        if(member == null){
            throw new AuthenticationException("账号不存在");
        }
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        List<RoleBean> roleList = roleService.queryRoleByUserId(member.getId());
        if(roleList != null && roleList.size() > 0){
            for(RoleBean role : roleList){
                if(role.getName() != null){
                    simpleAuthorInfo.addRole(role.getName());
                }
            }
        }

        List<PermissionBean> permList = permissionService.queryPermByUserId(member.getId());
        if(permList != null && permList.size() > 0){
            for(PermissionBean perm : permList){
                if(perm.getName() != null){
                    simpleAuthorInfo.addStringPermission(perm.getName());
                }
            }
        }
        return simpleAuthorInfo;

    }


    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        UserBean currentUser = userService.queryUserByName(token.getUsername());
        String md5pwd = MD5Utils.generatorMD5(String.valueOf(token.getPassword()));
        //账号不存在
        if(currentUser == null) {
            throw new UnknownAccountException("账号不存在");
        }
        //密码错误
        if(!md5pwd.equalsIgnoreCase(currentUser.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }
        //账号锁定
        if(currentUser.getIsLocked() !=null && currentUser.getIsLocked() == 1){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(currentUser.getUsername(), currentUser.getPassword(), this.getName());
        //密码清空，防止窃取
        currentUser.setPassword("");
        this.setSession("currentUser", currentUser);
        return authcInfo;

    }

    /**
     * 保存登录名
     */
    private void setSession(Object key, Object value){
        Session session = getSession();
        System.out.println("Session默认超时时间为：" + (session.getTimeout()/60000) + "分");
        if(null != session){
            session.setAttribute(key, value);
        }
    }

    private Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }
}