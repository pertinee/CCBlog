package com.lcz.blog.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * IP地址
 * @author luchunzhou
 */
public class IPUtils {
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
        	logger.error("IPUtils ERROR ", e);
        }
        
//        //使用代理，则获取第一个IP地址
//        if(StringUtils.isEmpty(ip) && ip.length() > 15) {
//			if(ip.indexOf(",") > 0) {
//				ip = ip.substring(0, ip.indexOf(","));
//			}
//		}
        
        return ip;
    }

    /**
     * 根据Ip获取设备归属地
     *
     * @param ip
     * @return
     */
    public static String getAddrByIP(String ip) {
        StringBuffer sb = new StringBuffer();
        try {
            HttpClient hc = new HttpClient();
            HttpMethod hm = new GetMethod("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            hc.executeMethod(hm);
            String res = hm.getResponseBodyAsString();
            JSONObject resp = JSON.parseObject(res);
            if (resp.containsKey("data")) {
                JSONObject data = resp.getJSONObject("data");
                if (data.containsKey("country")) {
                    sb.append("国家:" + data.getString("country") + " ");
                }
                if (data.containsKey("region")) {
                    sb.append("省:" + data.getString("region") + " ");
                }
                if (data.containsKey("city")) {
                    sb.append("市:" + data.getString("city") + " ");
                }
                if (data.containsKey("county")) {
                    sb.append("县:" + data.getString("county") + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getAddrByIP("115.199.150.247"));
    }
	
}
