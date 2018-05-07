/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : cc_blog

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-05-07 15:54:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_about
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_about
-- ----------------------------
INSERT INTO `t_about` VALUES ('1', '<h1 style=\"box-sizing: border-box; margin-right: 0px; margin-bottom: 16px; margin-left: 0px; font-size: 2.25em; font-family: &quot;Source Sans Pro&quot;, sans-serif; line-height: 1.2; color: rgb(51, 51, 51); position: relative; padding-bottom: 0.3em; border-bottom: 1px solid rgb(238, 238, 238); white-space: normal; margin-top: 0px !important;\">关于本站</h1><ul style=\"box-sizing: border-box; margin-bottom: 16px; padding: 0px 0px 0px 2em; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, Helvetica, &quot;Meiryo UI&quot;, &quot;Malgun Gothic&quot;, &quot;Segoe UI&quot;, &quot;Trebuchet MS&quot;, Monaco, monospace, Tahoma, STXihei, 华文细黑, STHeiti, &quot;Helvetica Neue&quot;, &quot;Droid Sans&quot;, &quot;wenquanyi micro hei&quot;, FreeSans, Arimo, Arial, SimSun, 宋体, Heiti, 黑体, sans-serif; font-size: 14px; white-space: normal;\" class=\" list-paddingleft-2\"><li><p>这里是关于信息。</p></li></ul><p><br/></p>');

-- ----------------------------
-- Table structure for t_apis
-- ----------------------------
DROP TABLE IF EXISTS `t_apis`;
CREATE TABLE `t_apis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_apis
-- ----------------------------
INSERT INTO `t_apis` VALUES ('1', '第一篇博客', 'The begining');
INSERT INTO `t_apis` VALUES ('2', 'css自适应垂直居中样式', 'css自适应垂直居中');
INSERT INTO `t_apis` VALUES ('3', '详解spring 每个jar的作用。', '详解spring 每个jar的作用');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '0 about',
  `user_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` mediumtext NOT NULL,
  `create_date` datetime NOT NULL,
  `clicks` int(11) DEFAULT '0',
  `remark` varchar(500) NOT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `is_draft` int(1) DEFAULT '0',
  PRIMARY KEY (`id`,`category_id`,`user_id`,`title`),
  UNIQUE KEY `articleId` (`id`) USING BTREE,
  KEY `FK_t_article` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '9', '1', 'The begining', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-30 21:28:27', '19', '第一篇博客', null, '0');
INSERT INTO `t_article` VALUES ('2', '6', '2', 'css自适应垂直居中', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 13:14:16', '6', 'css自适应垂直居中样式', null, '0');
INSERT INTO `t_article` VALUES ('3', '1', '1', 'Docker入门', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 13:37:59', '15', 'Docker入门阶段性学习。', null, '0');
INSERT INTO `t_article` VALUES ('4', '2', '1', 'POST和PATCH的区别', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 13:43:00', '3', 'patch方法用来更新局部资源，这句话我们该如何理解？', null, '0');
INSERT INTO `t_article` VALUES ('5', '5', '1', 'CentOS安装redis集群', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 13:57:05', '11', '本篇博客讲解如何在CentOS安装redis集群，并记录了redis集群的操作。', null, '0');
INSERT INTO `t_article` VALUES ('6', '5', '1', 'properties配置文件的读取', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 13:58:54', '8', 'java类中，properties配置文件的读取工具类。', null, '0');
INSERT INTO `t_article` VALUES ('7', '5', '1', 'CentOS7下搭建solr6.6全文检索服务器及IK分词器配置', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-10-31 14:17:13', '32', 'CentOS7下搭建solr6.6全文检索服务器及IK分词器配置。', null, '0');
INSERT INTO `t_article` VALUES ('8', '5', '1', 'Spring中的@Transactional概念', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-01 09:29:28', '18', 'Spring中的@Transactional基于动态代理的机制，提供了一种透明的事务管理机制，方便快捷解决在开发中碰到的问题。', null, '0');
INSERT INTO `t_article` VALUES ('9', '3', '1', 'Spring+SpringMVC 配置事务管理无效原因及解决方案', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-01 09:55:24', '12', '一般我们在Spring的配置文件application.xml中对Service层代码配置事务管理，可以对Service的方法进行AOP增强或事务处理如事务回滚，但是遇到一个问题，在Controller类中调用Service层方法，配置的事务管理会失效，查询相关资料发现原因。', null, '0');
INSERT INTO `t_article` VALUES ('10', '3', '3', '详解spring 每个jar的作用', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-04 00:17:30', '5', '详解spring 每个jar的作用。', null, '1');
INSERT INTO `t_article` VALUES ('11', '2', '1', 'mybatis generator 排序问题', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-04 12:12:01', '8', '解决mybatis generator 创建的查询中的排序问题。', null, '1');
INSERT INTO `t_article` VALUES ('12', '6', '1', '禁止自动填充浏览器记住的密码', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-06 09:24:59', '28', '取自网易邮箱登录的例子。使用了autocomplete=\"new-password\"', null, '1');
INSERT INTO `t_article` VALUES ('13', '1', '1', '静态文件防止缓存', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-11-06 14:25:51', '50', '两种方法防止静态文件缓存问题。', null, '0');
INSERT INTO `t_article` VALUES ('14', '3', '1', 'ueditor使用', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据。', '2017-12-09 15:47:55', '7', 'ueditor使用。', null, '0');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `categoryId` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '互联网/大数据');
INSERT INTO `t_category` VALUES ('2', '语言基础');
INSERT INTO `t_category` VALUES ('3', '开发框架');
INSERT INTO `t_category` VALUES ('4', '课程资源');
INSERT INTO `t_category` VALUES ('5', '软件设计');
INSERT INTO `t_category` VALUES ('6', 'Web前端');
INSERT INTO `t_category` VALUES ('7', '数据库技术');
INSERT INTO `t_category` VALUES ('8', '操作系统');
INSERT INTO `t_category` VALUES ('9', '随笔');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('02f2aeba-0869-4937-bb6a-e81030a58ff5', 'admin', '用户登录', 'com.lcz.blog.controller.sys.SysLoginController.login()', '\"admin\"', '127.0.0.1', '2018-05-07 15:20:33');
INSERT INTO `t_log` VALUES ('4783eebb-7987-4d74-b5ab-c89c0795d980', 'admin', '用户登录', 'com.lcz.blog.controller.sys.SysLoginController.login()', '\"admin\"', '0:0:0:0:0:0:0:1', '2018-05-07 15:47:20');
INSERT INTO `t_log` VALUES ('8b2aae43-ef81-4067-8e18-0cbc3edea42b', 'admin', '用户登录', 'com.lcz.blog.controller.sys.SysLoginController.login()', '\"admin\"', '0:0:0:0:0:0:0:1', '2018-05-07 15:49:24');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '管理员');
INSERT INTO `t_permission` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'customer');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int(1) DEFAULT '0' COMMENT '0 user 1 admin',
  `email` varchar(50) NOT NULL,
  `website` varchar(50) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT '0' COMMENT '锁定（0：正常；1：锁定）',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`username`,`nickname`),
  UNIQUE KEY `userId` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'top', '202cb962ac59075b964b07152d234b70', '1', '644533836@qq.com', 'https://www.baidu.com', '/static/image/tx.jpg', '0', '2017-06-06 18:10:42');
INSERT INTO `t_user` VALUES ('2', 'tom', 'tomy', '202cb962ac59075b964b07152d234b70', '0', '344733624@qq.com', 'https://www.baidu.com', '/static/image/tx.jpg', '0', '2017-07-05 12:05:04');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for t_web
-- ----------------------------
DROP TABLE IF EXISTS `t_web`;
CREATE TABLE `t_web` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `title` text NOT NULL,
  `front_page` int(11) NOT NULL,
  `sys_page` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web
-- ----------------------------
INSERT INTO `t_web` VALUES ('1', 'CCBlog', 'CC的博客', '5', '5');
