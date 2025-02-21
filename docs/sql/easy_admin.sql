-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 47.95.214.75    Database: easy_admin
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bas_notice_record`
--

DROP TABLE IF EXISTS `bas_notice_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_notice_record` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `notice_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `is_read` bit(1) DEFAULT NULL COMMENT '是否已读',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：用户阅读公告记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_notice_record`
--

LOCK TABLES `bas_notice_record` WRITE;
/*!40000 ALTER TABLE `bas_notice_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `bas_notice_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_organization`
--

DROP TABLE IF EXISTS `bas_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_organization` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父类机构ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品ID',
  `link_man` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `link_tel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `link_email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地',
  `used_end_time` datetime DEFAULT NULL COMMENT '使用截止时间',
  `level` int DEFAULT NULL COMMENT '等级',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路径',
  `type` int DEFAULT NULL COMMENT '类型',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `is_system` bit(1) DEFAULT b'0' COMMENT '是否总部',
  `is_saas` bit(1) DEFAULT NULL COMMENT '是否saas',
  `is_enabled` bit(1) DEFAULT NULL COMMENT '是否可用',
  `sys_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编码',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：机构管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_organization`
--

LOCK TABLES `bas_organization` WRITE;
/*!40000 ALTER TABLE `bas_organization` DISABLE KEYS */;
INSERT INTO `bas_organization` VALUES ('1785229829122285568',NULL,'pure-admin科技有限公司','https://hk-oss.hb0730.me/Y4E3RFXLbAgS-Bh_1715311088593.png','1785229367733444609','测试联系人','13111111111','','',NULL,1,'1785229829122285568',1,'',_binary '',_binary '',_binary '','PA001','superadmin','2024-04-30 16:48:54','superadmin','2024-05-10 11:18:13');
/*!40000 ALTER TABLE `bas_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_permission`
--

DROP TABLE IF EXISTS `bas_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_permission` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT b'1' COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT b'1' COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：菜单与权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_permission`
--

LOCK TABLES `bas_permission` WRITE;
/*!40000 ALTER TABLE `bas_permission` DISABLE KEYS */;
INSERT INTO `bas_permission` VALUES ('1785232090411360258',NULL,'/bas','','','','系统管理','ep:setting',_binary '',1,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:57:53','superadmin','2024-04-30 17:10:43');
INSERT INTO `bas_permission` VALUES ('1785232201522667522','1785232090411360258','/bas/org/index','BasOrgManager','','basic/org/index','机构管理','',_binary '',1,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:58:20','superadmin','2024-04-30 16:59:29');
INSERT INTO `bas_permission` VALUES ('1785232361749274625','1785232090411360258','/bas/user/index','BasUserManager','','basic/user/index','用户管理','',_binary '',2,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:58:58','superadmin','2024-04-30 17:12:34');
INSERT INTO `bas_permission` VALUES ('1785232457840779265','1785232090411360258','/bas/role/index','BasRoleManager','','basic/role/index','角色管理','',_binary '',4,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:59:21','superadmin','2024-05-07 15:06:22');
INSERT INTO `bas_permission` VALUES ('1785232570692722689','1785232201522667522','','','','','机构查询','',_binary '',1,_binary '',_binary '\0','',4,'bas:org:query',_binary '','superadmin','2024-04-30 16:59:48',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232622488182786','1785232201522667522','','','','','机构新增','',_binary '',2,_binary '',_binary '\0','',4,'bas:org:save',_binary '','superadmin','2024-04-30 17:00:00',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232698728046594','1785232201522667522','','','','','机构修改','',_binary '',3,_binary '',_binary '\0','',4,'bas:org:update',_binary '','superadmin','2024-04-30 17:00:18',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232754180939778','1785232201522667522','','','','','机构删除','',_binary '',4,_binary '',_binary '\0','',4,'bas:org:delete',_binary '','superadmin','2024-04-30 17:00:31',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232852340236290','1785232361749274625','','','','','用户查询','',_binary '',1,_binary '',_binary '\0','',4,'bas:user:query',_binary '','superadmin','2024-04-30 17:00:55',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232914025865217','1785232361749274625','','','','','用户新增','',_binary '',2,_binary '',_binary '\0','',4,'bas:user:save',_binary '','superadmin','2024-04-30 17:01:09',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785232967222222850','1785232361749274625','','','','','用户更新','',_binary '',3,_binary '',_binary '\0','',4,'bas:user:update',_binary '','superadmin','2024-04-30 17:01:22',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233044208672769','1785232361749274625','','','','','重置密码','',_binary '',4,_binary '',_binary '\0','',4,'bas:user:restPassword',_binary '','superadmin','2024-04-30 17:01:40',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233103767789570','1785232361749274625','','','','','用户删除','',_binary '',5,_binary '',_binary '\0','',4,'bas:user:delete',_binary '','superadmin','2024-04-30 17:01:55',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233168422985730','1785232457840779265','','','','','角色查询','',_binary '',1,_binary '',_binary '\0','',4,'bas:role:query',_binary '','superadmin','2024-04-30 17:02:10',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233233040433154','1785232457840779265','','','','','角色新增','',_binary '',2,_binary '',_binary '\0','',4,'bas:role:save',_binary '','superadmin','2024-04-30 17:02:25',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233282499665922','1785232457840779265','','','','','角色修改','',_binary '',3,_binary '',_binary '\0','',4,'bas:role:update',_binary '','superadmin','2024-04-30 17:02:37',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233355887403010','1785232457840779265','','','','','角色删除','',_binary '',4,_binary '',_binary '\0','',4,'bas:role:delete',_binary '','superadmin','2024-04-30 17:02:55',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1785233423612829698','1785232457840779265','','','','','角色赋权','',_binary '',5,_binary '',_binary '\0','',4,'bas:role:grant',_binary '','superadmin','2024-04-30 17:03:11',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1787740678002376706','1785232090411360258','/bas/post/index','BasPostManger','','basic/post/index','岗位管理','',_binary '',3,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-07 15:06:07',NULL,NULL);
INSERT INTO `bas_permission` VALUES ('1787743874431168514','1787740678002376706','','','','','岗位查询','',_binary '',1,_binary '',_binary '\0','',4,'bas:post:page',_binary '','superadmin','2024-05-07 15:18:49','superadmin','2024-05-07 15:50:16');
INSERT INTO `bas_permission` VALUES ('1787743960653475841','1787740678002376706','','','','','岗位新增','',_binary '',2,_binary '',_binary '\0','',4,'bas:post:save',_binary '','superadmin','2024-05-07 15:19:10','superadmin','2024-05-07 15:50:21');
INSERT INTO `bas_permission` VALUES ('1787744037283409922','1787740678002376706','','','','','岗位更新','',_binary '',3,_binary '',_binary '\0','',4,'bas:post:update',_binary '','superadmin','2024-05-07 15:19:28','superadmin','2024-05-07 15:50:27');
INSERT INTO `bas_permission` VALUES ('1787744103364669441','1787740678002376706','','','','','岗位删除','',_binary '',4,_binary '',_binary '\0','',4,'bas:post:delete',_binary '','superadmin','2024-05-07 15:19:44','superadmin','2024-05-07 15:50:31');
INSERT INTO `bas_permission` VALUES ('1788818051707748353','1785232090411360258','/bas/attachment/index','BasAttachmentManager','','basic/attachment/index','附件管理','',_binary '',5,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-10 14:27:13','superadmin','2024-05-10 14:27:19');
INSERT INTO `bas_permission` VALUES ('1788818181580177410','1788818051707748353','','','','','附件删除','',_binary '',1,_binary '',_binary '\0','',4,'common:attachment:delete',_binary '','superadmin','2024-05-10 14:27:44',NULL,NULL);
/*!40000 ALTER TABLE `bas_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_role`
--

DROP TABLE IF EXISTS `bas_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_role` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `data_scope` tinyint DEFAULT NULL COMMENT '数据范围',
  `sys_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统编码',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_role`
--

LOCK TABLES `bas_role` WRITE;
/*!40000 ALTER TABLE `bas_role` DISABLE KEYS */;
INSERT INTO `bas_role` VALUES ('1785229829160034304','管理角色','PA001',NULL,1,'PA001',1,1,'2024-04-30 16:48:54','superadmin','2024-05-10 14:27:58','superadmin');
/*!40000 ALTER TABLE `bas_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_role_permission`
--

DROP TABLE IF EXISTS `bas_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_role_permission` (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_role_permission`
--

LOCK TABLES `bas_role_permission` WRITE;
/*!40000 ALTER TABLE `bas_role_permission` DISABLE KEYS */;
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232090411360258');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232201522667522');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232361749274625');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232457840779265');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232570692722689');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232622488182786');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232698728046594');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232754180939778');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232852340236290');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232914025865217');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785232967222222850');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233044208672769');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233103767789570');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233168422985730');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233233040433154');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233282499665922');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233355887403010');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1785233423612829698');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1787740678002376706');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1787743874431168514');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1787743960653475841');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1787744037283409922');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1787744103364669441');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1788818051707748353');
INSERT INTO `bas_role_permission` VALUES ('1785229829160034304','1788818181580177410');
/*!40000 ALTER TABLE `bas_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_user`
--

DROP TABLE IF EXISTS `bas_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_user` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `org_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT '0' COMMENT '性别,0 保密,1 男,2 女',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '最近修改密码时间',
  `sys_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统代码',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_user`
--

LOCK TABLES `bas_user` WRITE;
/*!40000 ALTER TABLE `bas_user` DISABLE KEYS */;
INSERT INTO `bas_user` VALUES ('1785229829181005824','1785229829122285568','13111111111','$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC','管理员','13111111111',NULL,NULL,0,'2024-10-28 08:13:47',NULL,'PA001',1,1,'2024-04-30 16:48:54','superadmin','2024-04-30 18:04:44','superadmin');
/*!40000 ALTER TABLE `bas_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_user_role`
--

DROP TABLE IF EXISTS `bas_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bas_user_role` (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_user_role`
--

LOCK TABLES `bas_user_role` WRITE;
/*!40000 ALTER TABLE `bas_user_role` DISABLE KEYS */;
INSERT INTO `bas_user_role` VALUES ('1785229829160034304','1785229829181005824');
/*!40000 ALTER TABLE `bas_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `notice_time_start` datetime DEFAULT NULL COMMENT '公告日期',
  `notice_time_end` datetime DEFAULT NULL COMMENT '公共结束时间',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `org_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '网点ID',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：系统公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_permission` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT b'1' COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT b'1' COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：菜单与权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES ('1',NULL,'/sys',NULL,NULL,NULL,'系统管理','ri:settings-3-line',_binary '',1,_binary '',_binary '\0',NULL,1,NULL,_binary '','admin','2024-03-25 09:36:20','admin','2024-03-25 09:36:20');
INSERT INTO `sys_permission` VALUES ('10','8',NULL,NULL,NULL,NULL,'角色新增',NULL,_binary '',2,_binary '',_binary '\0',NULL,4,'sys:role:add',_binary '','admin','2024-03-25 13:12:48','admin','2024-03-25 13:12:48');
INSERT INTO `sys_permission` VALUES ('11','8',NULL,NULL,NULL,NULL,'角色修改',NULL,_binary '',3,_binary '',_binary '\0',NULL,4,'sys:role:update',_binary '','admin','2024-03-25 13:13:15','admin','2024-03-25 13:13:15');
INSERT INTO `sys_permission` VALUES ('12','8',NULL,NULL,NULL,NULL,'角色删除',NULL,_binary '',4,_binary '',_binary '\0',NULL,4,'sys:role:delete',_binary '','admin','2024-03-25 13:13:42','admin','2024-03-25 13:13:42');
INSERT INTO `sys_permission` VALUES ('13','8',NULL,NULL,NULL,NULL,'角色赋权',NULL,_binary '',5,_binary '',_binary '\0',NULL,4,'sys:role:assignPermission',_binary '','admin','2024-03-25 13:14:12','admin','2024-03-25 13:14:12');
INSERT INTO `sys_permission` VALUES ('1785218829636894721','2','','','','','用户查询','',_binary '',1,_binary '',_binary '\0','',4,'sys:user:query',_binary '','superadmin','2024-04-30 16:05:11','superadmin','2024-04-30 16:05:11');
INSERT INTO `sys_permission` VALUES ('1785219038316101633','2','','','','','用户新增','',_binary '',2,_binary '',_binary '\0','',4,'sys:user:add',_binary '','superadmin','2024-04-30 16:06:01','superadmin','2024-04-30 16:06:01');
INSERT INTO `sys_permission` VALUES ('1785219103000657921','2','','','','','用户修改','',_binary '',3,_binary '',_binary '\0','',4,'sys:user:update',_binary '','superadmin','2024-04-30 16:06:17','superadmin','2024-04-30 16:06:17');
INSERT INTO `sys_permission` VALUES ('1785219231749013505','2','','','','','用户删除','',_binary '',4,_binary '',_binary '\0','',4,'sys:user:delete',_binary '','superadmin','2024-04-30 16:06:47','superadmin','2024-04-30 16:06:47');
INSERT INTO `sys_permission` VALUES ('1785219294940397569','2','','','','','重置密码','',_binary '',5,_binary '',_binary '\0','',4,'sys:user:resetPassword',_binary '','superadmin','2024-04-30 16:07:02','superadmin','2024-04-30 16:07:02');
INSERT INTO `sys_permission` VALUES ('1785225857788874754','1','/sys/notice/index','noticeManager','','sys/notice/index','系统公告','ep:bell-filled',_binary '',5,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:33:07','superadmin','2024-04-30 16:33:07');
INSERT INTO `sys_permission` VALUES ('1785226155672539138','1785225857788874754','','','','','公告查询','',_binary '',1,_binary '',_binary '\0','',4,'sys:notice:query',_binary '','superadmin','2024-04-30 16:34:18','superadmin','2024-04-30 16:34:18');
INSERT INTO `sys_permission` VALUES ('1785226275357003777','1785225857788874754','','','','','公告新增','',_binary '',2,_binary '',_binary '\0','',4,'sys:notice:add',_binary '','superadmin','2024-04-30 16:34:47','superadmin','2024-04-30 16:34:47');
INSERT INTO `sys_permission` VALUES ('1785226339936702465','1785225857788874754','','','','','公告修改','',_binary '',3,_binary '',_binary '\0','',4,'sys:notice:update',_binary '','superadmin','2024-04-30 16:35:02','superadmin','2024-04-30 16:35:02');
INSERT INTO `sys_permission` VALUES ('1785226422149255170','1785225857788874754','','','','','公告删除','',_binary '',4,_binary '',_binary '\0','',4,'sys:notice:delete',_binary '','superadmin','2024-04-30 16:35:22','superadmin','2024-04-30 16:35:22');
INSERT INTO `sys_permission` VALUES ('1785226686633676802',NULL,'/tenant','','','','商户管理','ri:organization-chart',_binary '',2,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:36:25','superadmin','2024-04-30 16:36:25');
INSERT INTO `sys_permission` VALUES ('1785227063890350082','1785226686633676802','/tenant/org/index','TenantOrgManager','','sys/tenant/org/index','履约商管理','',_binary '',1,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:37:55','superadmin','2024-04-30 16:37:55');
INSERT INTO `sys_permission` VALUES ('1785227207171969025','1785227063890350082','','','','','履约商查询','',_binary '',1,_binary '',_binary '\0','',4,'tenant:org:query',_binary '','superadmin','2024-04-30 16:38:29','superadmin','2024-04-30 16:38:29');
INSERT INTO `sys_permission` VALUES ('1785227275484598274','1785227063890350082','','','','','履约商新增','',_binary '',2,_binary '',_binary '\0','',4,'tenant:org:add',_binary '','superadmin','2024-04-30 16:38:45','superadmin','2024-04-30 16:38:45');
INSERT INTO `sys_permission` VALUES ('1785227334389403649','1785227063890350082','','','','','履约商修改','',_binary '',3,_binary '',_binary '\0','',4,'tenant:org:update',_binary '','superadmin','2024-04-30 16:38:59','superadmin','2024-04-30 16:38:59');
INSERT INTO `sys_permission` VALUES ('1785227498244083713','1785227063890350082','','','','','履约商删除','',_binary '',4,_binary '',_binary '\0','',4,'tenant:org:delete',_binary '','superadmin','2024-04-30 16:39:38','superadmin','2024-04-30 16:39:38');
INSERT INTO `sys_permission` VALUES ('1785228542034370562','1785226686633676802','/tenant/product/index','ProductManager','','sys/tenant/product/index','产品管理','',_binary '',2,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:43:47','superadmin','2024-04-30 16:43:47');
INSERT INTO `sys_permission` VALUES ('1785228667859296258','1785228542034370562','','','','','产品查询','',_binary '',1,_binary '',_binary '\0','',4,'tenant:product:query',_binary '','superadmin','2024-04-30 16:44:17','superadmin','2024-04-30 16:44:17');
INSERT INTO `sys_permission` VALUES ('1785228729737863169','1785228542034370562','','','','','产品新增','',_binary '',2,_binary '',_binary '\0','',4,'tenant:product:add',_binary '','superadmin','2024-04-30 16:44:32','superadmin','2024-04-30 16:44:32');
INSERT INTO `sys_permission` VALUES ('1785228787493429250','1785228542034370562','','','','','产品更新','',_binary '',3,_binary '',_binary '\0','',4,'tenant:product:update',_binary '','superadmin','2024-04-30 16:44:46','superadmin','2024-04-30 16:44:46');
INSERT INTO `sys_permission` VALUES ('1785228851183935490','1785228542034370562','','','','','产品删除','',_binary '',4,_binary '',_binary '\0','',4,'tenant:product:delete',_binary '','superadmin','2024-04-30 16:45:01','superadmin','2024-04-30 16:45:01');
INSERT INTO `sys_permission` VALUES ('1785228948500176897','1785228542034370562','','','','','产品赋权','',_binary '',5,_binary '',_binary '\0','',4,'tenant:product:grant',_binary '','superadmin','2024-04-30 16:45:24','superadmin','2024-04-30 16:45:24');
INSERT INTO `sys_permission` VALUES ('1785231431528144897','1785226686633676802','/tenant/permission/index','TenantPermissionManger','','sys/tenant/permission/index','履约端权限','',_binary '\0',3,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 16:55:16','superadmin','2024-04-30 16:55:16');
INSERT INTO `sys_permission` VALUES ('1785231706154393601','1785231431528144897','','','','','维护履约端权限','',_binary '',1,_binary '',_binary '\0','',4,'tenant:permission:update',_binary '','superadmin','2024-04-30 16:56:21','superadmin','2024-04-30 16:56:21');
INSERT INTO `sys_permission` VALUES ('1785243846168895490',NULL,'/monitor','','','','系统监控','ep:monitor',_binary '',3,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 17:44:36','superadmin','2024-04-30 17:44:36');
INSERT INTO `sys_permission` VALUES ('1785244014243045378','1785243846168895490','/monitor/task/index','TaskJobManger','','sys/monitor/task/index','定时任务','',_binary '',1,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-04-30 17:45:16','superadmin','2024-04-30 17:45:16');
INSERT INTO `sys_permission` VALUES ('1785474078014992385','1785244014243045378','','','','','定时任务查询','',_binary '',1,_binary '',_binary '\0','',4,'sys:quartz:job:query',_binary '','superadmin','2024-05-01 08:59:27','superadmin','2024-05-01 08:59:27');
INSERT INTO `sys_permission` VALUES ('1785474153176920065','1785244014243045378','','','','','定时任务详情','',_binary '',2,_binary '',_binary '\0','',4,'sys:quartz:job:info',_binary '','superadmin','2024-05-01 08:59:45','superadmin','2024-05-01 08:59:45');
INSERT INTO `sys_permission` VALUES ('1785474214451507202','1785244014243045378','','','','','定时任务新增','',_binary '',3,_binary '',_binary '\0','',4,'sys:quartz:job:add',_binary '','superadmin','2024-05-01 09:00:00','superadmin','2024-05-01 09:00:00');
INSERT INTO `sys_permission` VALUES ('1785474310224244738','1785244014243045378','','','','','定时任务修改','',_binary '',4,_binary '',_binary '\0','',4,'sys:quartz:job:update',_binary '','superadmin','2024-05-01 09:00:23','superadmin','2024-05-01 09:00:23');
INSERT INTO `sys_permission` VALUES ('1785474379866468353','1785244014243045378','','','','','定时任务删除','',_binary '',5,_binary '',_binary '\0','',4,'sys:quartz:job:delete',_binary '','superadmin','2024-05-01 09:00:39','superadmin','2024-05-01 09:00:39');
INSERT INTO `sys_permission` VALUES ('1785474480643010562','1785244014243045378','','','','','定时任务暂停','',_binary '',6,_binary '',_binary '\0','',4,'sys:quartz:job:pause',_binary '','superadmin','2024-05-01 09:01:03','superadmin','2024-05-01 09:01:03');
INSERT INTO `sys_permission` VALUES ('1785474547487633409','1785244014243045378','','','','','定时任务恢复','',_binary '',7,_binary '',_binary '\0','',4,'sys:quartz:job:resume',_binary '','superadmin','2024-05-01 09:01:19','superadmin','2024-05-01 09:01:19');
INSERT INTO `sys_permission` VALUES ('1785474601975836674','1785244014243045378','','','','','立即执行','',_binary '',8,_binary '',_binary '\0','',4,'sys:quartz:job:run',_binary '','superadmin','2024-05-01 09:01:32','superadmin','2024-05-01 09:01:32');
INSERT INTO `sys_permission` VALUES ('1786930858465288193','1','/sys/dict/index','dictManger','','sys/dict/index','数据字典','ep:notebook',_binary '',4,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-05 09:28:11','superadmin','2024-05-05 09:28:11');
INSERT INTO `sys_permission` VALUES ('1786931490559483905','1786930858465288193','','','','','字典查询','',_binary '',1,_binary '',_binary '\0','',4,'sys:dict:query',_binary '','superadmin','2024-05-05 09:30:42','superadmin','2024-05-05 09:30:42');
INSERT INTO `sys_permission` VALUES ('1786931562953170945','1786930858465288193','','','','','字典新增','',_binary '',2,_binary '',_binary '\0','',4,'sys:dict:add',_binary '','superadmin','2024-05-05 09:30:59','superadmin','2024-05-05 09:30:59');
INSERT INTO `sys_permission` VALUES ('1786931631978831874','1786930858465288193','','','','','字典更新','',_binary '',3,_binary '',_binary '\0','',4,'sys:dict:update',_binary '','superadmin','2024-05-05 09:31:15','superadmin','2024-05-05 09:31:15');
INSERT INTO `sys_permission` VALUES ('1786931710399733762','1786930858465288193','','','','','字典删除','',_binary '',4,_binary '',_binary '\0','',4,'sys:dict:delete',_binary '','superadmin','2024-05-05 09:31:34','superadmin','2024-05-05 09:31:34');
INSERT INTO `sys_permission` VALUES ('1786931835352244226','1786930858465288193','','','','','字典项查询','',_binary '',5,_binary '',_binary '\0','',4,'sys:dict:item:query',_binary '','superadmin','2024-05-05 09:32:04','superadmin','2024-05-05 09:32:04');
INSERT INTO `sys_permission` VALUES ('1786931957188386818','1786930858465288193','','','','','字典项新增','',_binary '',6,_binary '',_binary '\0','',4,'sys:dict:item:add',_binary '','superadmin','2024-05-05 09:32:33','superadmin','2024-05-05 09:32:33');
INSERT INTO `sys_permission` VALUES ('1786932016130940930','1786930858465288193','','','','','字典项修改','',_binary '',7,_binary '',_binary '\0','',4,'sys:dict:item:update',_binary '','superadmin','2024-05-05 09:32:47','superadmin','2024-05-05 09:32:47');
INSERT INTO `sys_permission` VALUES ('1786932087664795649','1786930858465288193','','','','','字典项删除','',_binary '',8,_binary '',_binary '\0','',4,'sys:dict:item:delete',_binary '','superadmin','2024-05-05 09:33:04','superadmin','2024-05-05 09:33:04');
INSERT INTO `sys_permission` VALUES ('1788816279836606466',NULL,'/base','','','','基础数据','ri:database-fill',_binary '',4,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-10 14:20:10','superadmin','2024-05-10 14:20:10');
INSERT INTO `sys_permission` VALUES ('1788816437806678018','1788816279836606466','/base/area','BasAreaMananger','','sys/base/area/index','地区管理','',_binary '',1,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-10 14:20:48','superadmin','2024-05-10 14:20:48');
INSERT INTO `sys_permission` VALUES ('1788816704757350401','1788816279836606466','/base/attachment','BaseAttachmentManger','','sys/base/attachment/index','附件管理','',_binary '',2,_binary '',_binary '\0','',1,'',_binary '','superadmin','2024-05-10 14:21:52','superadmin','2024-05-10 14:21:52');
INSERT INTO `sys_permission` VALUES ('1788816788207222786','1788816437806678018','','','','','地区查询','',_binary '',1,_binary '',_binary '\0','',4,'sys:area:query',_binary '','superadmin','2024-05-10 14:22:12','superadmin','2024-05-10 14:22:12');
INSERT INTO `sys_permission` VALUES ('1788816841206448129','1788816437806678018','','','','','地区保存','',_binary '',2,_binary '',_binary '\0','',4,'sys:area:add',_binary '','superadmin','2024-05-10 14:22:24','superadmin','2024-05-10 14:22:24');
INSERT INTO `sys_permission` VALUES ('1788816893278732290','1788816437806678018','','','','','地区更新','',_binary '',3,_binary '',_binary '\0','',4,'sys:area:update',_binary '','superadmin','2024-05-10 14:22:37','superadmin','2024-05-10 14:22:37');
INSERT INTO `sys_permission` VALUES ('1788816951759912962','1788816437806678018','','','','','地区删除','',_binary '',3,_binary '',_binary '\0','',4,'sys:area:delete',_binary '','superadmin','2024-05-10 14:22:51','superadmin','2024-05-10 14:22:51');
INSERT INTO `sys_permission` VALUES ('1788817046085615618','1788816704757350401','','','','','附件删除','',_binary '',1,_binary '',_binary '\0','',4,'common:attachment:delete',_binary '','superadmin','2024-05-10 14:23:13','superadmin','2024-05-10 14:23:13');
INSERT INTO `sys_permission` VALUES ('2','1','/sys/user/index','userManger',NULL,'sys/user/index','用户管理','ri:admin-line',_binary '',1,_binary '',_binary '\0',NULL,1,NULL,_binary '','admin','2024-03-25 09:37:45','admin','2024-03-25 09:37:45');
INSERT INTO `sys_permission` VALUES ('3','1','/sys/menu/index','menuManger',NULL,'sys/menu/index','菜单管理','ep:menu',_binary '',3,_binary '',_binary '\0',NULL,1,NULL,_binary '','admin','2024-03-25 09:40:13','admin','2024-03-25 09:40:13');
INSERT INTO `sys_permission` VALUES ('4','3',NULL,NULL,NULL,NULL,'菜单查询',NULL,_binary '',1,_binary '',_binary '\0',NULL,4,'sys:permission:query',_binary '','admin','2024-03-25 13:07:00','admin','2024-03-25 13:07:00');
INSERT INTO `sys_permission` VALUES ('5','3',NULL,NULL,NULL,NULL,'菜单新增',NULL,_binary '',2,_binary '',_binary '\0',NULL,4,'sys:permission:add',_binary '','admin','2024-03-25 13:07:37','admin','2024-03-25 13:07:37');
INSERT INTO `sys_permission` VALUES ('6','3',NULL,NULL,NULL,NULL,'菜单修改',NULL,_binary '',3,_binary '',_binary '\0',NULL,4,'sys:permission:update',_binary '','admin','2024-03-25 13:08:00','admin','2024-03-25 13:08:00');
INSERT INTO `sys_permission` VALUES ('7','3',NULL,NULL,NULL,NULL,'菜单删除',NULL,_binary '',4,_binary '',_binary '\0',NULL,4,'sys:permission:delete',_binary '','admin','2024-03-25 13:08:35','admin','2024-03-25 13:08:35');
INSERT INTO `sys_permission` VALUES ('8','1','/sys/role/index','roleManger',NULL,'sys/role/index','角色管理','ri:admin-fill',_binary '',2,_binary '',_binary '\0',NULL,1,NULL,_binary '','admin','2024-03-25 13:11:42','admin','2024-03-25 13:11:42');
INSERT INTO `sys_permission` VALUES ('9','8',NULL,NULL,NULL,NULL,'角色查询',NULL,_binary '',1,_binary '',_binary '\0',NULL,4,'sys:role:query',_binary '','admin','2024-03-25 13:12:18','admin','2024-03-25 13:12:18');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_product`
--

DROP TABLE IF EXISTS `sys_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_product` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品代码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `principal` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系方式',
  `site_num` int DEFAULT NULL COMMENT '站点数量',
  `account_num` int DEFAULT NULL COMMENT '账号数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：产品管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_product`
--

LOCK TABLES `sys_product` WRITE;
/*!40000 ALTER TABLE `sys_product` DISABLE KEYS */;
INSERT INTO `sys_product` VALUES ('1785229367733444609','T001','正式产品','','',0,0,'',_binary '','superadmin','2024-04-30 16:47:04','superadmin','2024-05-10 14:27:58');
/*!40000 ALTER TABLE `sys_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_product_permission`
--

DROP TABLE IF EXISTS `sys_product_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_product_permission` (
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '履约端-权限ID',
  PRIMARY KEY (`product_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：产品与菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_product_permission`
--

LOCK TABLES `sys_product_permission` WRITE;
/*!40000 ALTER TABLE `sys_product_permission` DISABLE KEYS */;
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232090411360258');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232201522667522');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232361749274625');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232457840779265');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232570692722689');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232622488182786');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232698728046594');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232754180939778');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232852340236290');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232914025865217');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785232967222222850');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233044208672769');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233103767789570');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233168422985730');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233233040433154');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233282499665922');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233355887403010');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1785233423612829698');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1787740678002376706');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1787743874431168514');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1787743960653475841');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1787744037283409922');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1787744103364669441');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1788818051707748353');
INSERT INTO `sys_product_permission` VALUES ('1785229367733444609','1788818181580177410');
/*!40000 ALTER TABLE `sys_product_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('1','超级管理员','admin','超级管理员',1,1,'2024-03-25 09:40:43','admin','2024-05-10 14:23:31','superadmin');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_permission` (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端： 角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES ('1','1');
INSERT INTO `sys_role_permission` VALUES ('1','10');
INSERT INTO `sys_role_permission` VALUES ('1','11');
INSERT INTO `sys_role_permission` VALUES ('1','12');
INSERT INTO `sys_role_permission` VALUES ('1','13');
INSERT INTO `sys_role_permission` VALUES ('1','1785218829636894721');
INSERT INTO `sys_role_permission` VALUES ('1','1785219038316101633');
INSERT INTO `sys_role_permission` VALUES ('1','1785219103000657921');
INSERT INTO `sys_role_permission` VALUES ('1','1785219231749013505');
INSERT INTO `sys_role_permission` VALUES ('1','1785219294940397569');
INSERT INTO `sys_role_permission` VALUES ('1','1785225857788874754');
INSERT INTO `sys_role_permission` VALUES ('1','1785226155672539138');
INSERT INTO `sys_role_permission` VALUES ('1','1785226275357003777');
INSERT INTO `sys_role_permission` VALUES ('1','1785226339936702465');
INSERT INTO `sys_role_permission` VALUES ('1','1785226422149255170');
INSERT INTO `sys_role_permission` VALUES ('1','1785226686633676802');
INSERT INTO `sys_role_permission` VALUES ('1','1785227063890350082');
INSERT INTO `sys_role_permission` VALUES ('1','1785227207171969025');
INSERT INTO `sys_role_permission` VALUES ('1','1785227275484598274');
INSERT INTO `sys_role_permission` VALUES ('1','1785227334389403649');
INSERT INTO `sys_role_permission` VALUES ('1','1785227498244083713');
INSERT INTO `sys_role_permission` VALUES ('1','1785228542034370562');
INSERT INTO `sys_role_permission` VALUES ('1','1785228667859296258');
INSERT INTO `sys_role_permission` VALUES ('1','1785228729737863169');
INSERT INTO `sys_role_permission` VALUES ('1','1785228787493429250');
INSERT INTO `sys_role_permission` VALUES ('1','1785228851183935490');
INSERT INTO `sys_role_permission` VALUES ('1','1785228948500176897');
INSERT INTO `sys_role_permission` VALUES ('1','1785231431528144897');
INSERT INTO `sys_role_permission` VALUES ('1','1785231706154393601');
INSERT INTO `sys_role_permission` VALUES ('1','1785243846168895490');
INSERT INTO `sys_role_permission` VALUES ('1','1785244014243045378');
INSERT INTO `sys_role_permission` VALUES ('1','1785474078014992385');
INSERT INTO `sys_role_permission` VALUES ('1','1785474153176920065');
INSERT INTO `sys_role_permission` VALUES ('1','1785474214451507202');
INSERT INTO `sys_role_permission` VALUES ('1','1785474310224244738');
INSERT INTO `sys_role_permission` VALUES ('1','1785474379866468353');
INSERT INTO `sys_role_permission` VALUES ('1','1785474480643010562');
INSERT INTO `sys_role_permission` VALUES ('1','1785474547487633409');
INSERT INTO `sys_role_permission` VALUES ('1','1785474601975836674');
INSERT INTO `sys_role_permission` VALUES ('1','1786930858465288193');
INSERT INTO `sys_role_permission` VALUES ('1','1786931490559483905');
INSERT INTO `sys_role_permission` VALUES ('1','1786931562953170945');
INSERT INTO `sys_role_permission` VALUES ('1','1786931631978831874');
INSERT INTO `sys_role_permission` VALUES ('1','1786931710399733762');
INSERT INTO `sys_role_permission` VALUES ('1','1786931835352244226');
INSERT INTO `sys_role_permission` VALUES ('1','1786931957188386818');
INSERT INTO `sys_role_permission` VALUES ('1','1786932016130940930');
INSERT INTO `sys_role_permission` VALUES ('1','1786932087664795649');
INSERT INTO `sys_role_permission` VALUES ('1','1788816279836606466');
INSERT INTO `sys_role_permission` VALUES ('1','1788816437806678018');
INSERT INTO `sys_role_permission` VALUES ('1','1788816704757350401');
INSERT INTO `sys_role_permission` VALUES ('1','1788816788207222786');
INSERT INTO `sys_role_permission` VALUES ('1','1788816841206448129');
INSERT INTO `sys_role_permission` VALUES ('1','1788816893278732290');
INSERT INTO `sys_role_permission` VALUES ('1','1788816951759912962');
INSERT INTO `sys_role_permission` VALUES ('1','1788817046085615618');
INSERT INTO `sys_role_permission` VALUES ('1','2');
INSERT INTO `sys_role_permission` VALUES ('1','3');
INSERT INTO `sys_role_permission` VALUES ('1','4');
INSERT INTO `sys_role_permission` VALUES ('1','5');
INSERT INTO `sys_role_permission` VALUES ('1','6');
INSERT INTO `sys_role_permission` VALUES ('1','7');
INSERT INTO `sys_role_permission` VALUES ('1','8');
INSERT INTO `sys_role_permission` VALUES ('1','9');
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `gender` tinyint DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录ip',
  `last_pwd_reset_time` datetime DEFAULT NULL COMMENT '最后修改密码的时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端： 用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','superadmin','管理员','$2a$10$8FPJCvoFt7YvzVKSWUSg1.tnDDkQGJ5da/F2c2EDfzsbOFAzypQqu',0,'example@example.com','13800000000','https://avatars.githubusercontent.com/u/52290618','2024-10-28 08:52:05',NULL,NULL,'租户-超级管理员',1,1,'2024-03-23 09:17:46','admin','2024-12-02 09:24:07','superadmin');
INSERT INTO `sys_user` VALUES ('1853682097739292674','admin','Aurora','$2a$10$yo77tOUECR64TedbvYMUuOgYBERqzN/YlNVIM2ctVtpMRTtrBJa6a',1,'example@example.com','13800000000','https://avatars.githubusercontent.com/u/52290618','2004-09-20 16:07:17',NULL,NULL,NULL,0,0,'2024-11-05 14:13:47','superadmin','2024-11-05 14:13:47','superadmin');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('1','1');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'easy_admin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 12:13:00
