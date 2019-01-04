/*
 Navicat Premium Data Transfer

 Source Server         : loclahost
 Source Server Type    : MariaDB
 Source Server Version : 100311
 Source Host           : localhost:3306
 Source Schema         : source

 Target Server Type    : MariaDB
 Target Server Version : 100311
 File Encoding         : 65001

 Date: 04/01/2019 11:24:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(11) NOT NULL,
  `up_area_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES (1, '公司', 0, '0', '0');

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operate_time` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES (1, 'admin', '2018-10-16 13:05:03', '方法名称【delById】操作数据：[31]');
INSERT INTO `tb_log` VALUES (2, 'admin', '2018-10-17 13:16:52', '方法名称【update】操作数据：[com.geely.model.User@1a7034b9]');
INSERT INTO `tb_log` VALUES (3, 'admin', '2018-10-17 13:19:43', '方法名称【add】操作数据：[com.geely.model.OperateTime@16e90ee2]');
INSERT INTO `tb_log` VALUES (4, 'admin', '2018-10-17 13:19:43', '方法名称【add】异常信息：org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'examin_user\' in \'class com.geely.model.OperateTime\'');
INSERT INTO `tb_log` VALUES (5, 'admin', '2018-10-17 13:20:30', '方法名称【add】操作数据：[com.geely.model.OperateTime@31b5249a]');
INSERT INTO `tb_log` VALUES (6, 'admin', '2018-10-17 13:20:30', '方法名称【add】异常信息：org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'examin_user\' in \'class com.geely.model.OperateTime\'');
INSERT INTO `tb_log` VALUES (7, 'admin', '2018-10-17 13:35:53', '方法名称【update】操作数据：[com.geely.model.User@631d2ba1]');
INSERT INTO `tb_log` VALUES (8, 'admin', '2018-10-17 16:58:51', '方法名称【add】操作数据：[com.geely.model.OperateTime@4793a13]');
INSERT INTO `tb_log` VALUES (9, 'admin', '2018-10-17 16:58:51', '方法名称【add】异常信息：org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'examin_user\' in \'class com.geely.model.OperateTime\'');
INSERT INTO `tb_log` VALUES (10, 'admin', '2018-10-17 17:01:46', '方法名称【add】操作数据：[com.geely.model.OperateTime@2e6272c1]');
INSERT INTO `tb_log` VALUES (11, 'admin', '2018-10-17 17:01:46', '方法名称【add】异常信息：org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'examin_user\' in \'class com.geely.model.OperateTime\'');
INSERT INTO `tb_log` VALUES (12, 'admin', '2018-10-17 17:04:19', '方法名称【add】操作数据：[com.geely.model.OperateTime@6a90d675]');
INSERT INTO `tb_log` VALUES (13, 'admin', '2018-10-17 17:10:31', '方法名称【add】操作数据：[com.geely.model.OperateTime@57e424ce]');
INSERT INTO `tb_log` VALUES (14, 'admin', '2018-10-18 12:25:15', '方法名称【update】操作数据：[com.geely.model.Menu@4ed5960d]');
INSERT INTO `tb_log` VALUES (15, 'a', '2018-10-18 12:26:15', '方法名称【add】操作数据：[com.geely.model.OperateTime@4d9d1a52]');
INSERT INTO `tb_log` VALUES (16, 'a', '2018-10-18 12:26:20', '方法名称【delById】操作数据：[2]');
INSERT INTO `tb_log` VALUES (17, 'admin', '2018-10-18 12:27:15', '方法名称【add】操作数据：[com.geely.model.OperateTime@eb70084]');
INSERT INTO `tb_log` VALUES (18, 'admin', '2018-10-18 12:27:20', '方法名称【delById】操作数据：[3]');
INSERT INTO `tb_log` VALUES (19, 'admin', '2018-12-12 15:01:41', '方法名称【deleteById】操作数据：[11]');
INSERT INTO `tb_log` VALUES (20, 'admin', '2018-12-12 15:01:50', '方法名称【deleteById】操作数据：[10]');
INSERT INTO `tb_log` VALUES (21, 'admin', '2018-12-12 15:01:55', '方法名称【deleteById】操作数据：[9]');
INSERT INTO `tb_log` VALUES (22, 'admin', '2018-12-12 15:05:56', '方法名称【add】操作数据：[com.bhhgz.model.Menu@507433ed]');
INSERT INTO `tb_log` VALUES (23, 'admin', '2018-12-27 10:16:11', '方法名称【deleteById】操作数据：[11]');
INSERT INTO `tb_log` VALUES (24, 'admin', '2018-12-27 10:16:31', '方法名称【update】操作数据：[com.source.model.Role@73e45e1e]');
INSERT INTO `tb_log` VALUES (25, 'admin', '2018-12-27 10:16:49', '方法名称【deleteById】操作数据：[11]');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(11) NOT NULL,
  `up_menu_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '系统配置', 1, '0', '无');
INSERT INTO `tb_menu` VALUES (2, '菜单管理', 2, '1', 'admin/menu_list.html');
INSERT INTO `tb_menu` VALUES (3, '角色管理', 2, '1', 'admin/role_list.html');
INSERT INTO `tb_menu` VALUES (4, '权限管理', 2, '1', 'admin/operation_list.html');
INSERT INTO `tb_menu` VALUES (5, '区域管理', 2, '1', 'admin/area_list.html');
INSERT INTO `tb_menu` VALUES (6, '日志管理', 2, '1', 'admin/log_list.html');
INSERT INTO `tb_menu` VALUES (8, '用户管理', 2, '1', 'admin/user_list.html');

-- ----------------------------
-- Table structure for tb_operatetime
-- ----------------------------
DROP TABLE IF EXISTS `tb_operatetime`;
CREATE TABLE `tb_operatetime`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_account` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_nick_name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_id` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `overtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `explains` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `booker` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(2) NOT NULL,
  `examin_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `examine_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_operatetime
-- ----------------------------
INSERT INTO `tb_operatetime` VALUES (1, '2018-10-17', 'admin', '超级管理员', '公司', '2018-10-17 17:10:19', '2018-10-18 17:10:20', '24', '1', 'sadaf', 'adfad', 'adfasd', 'adfasd', 0, NULL, NULL);

-- ----------------------------
-- Table structure for tb_operation
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation`;
CREATE TABLE `tb_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_operation
-- ----------------------------
INSERT INTO `tb_operation` VALUES (1, 'test', '\\test');
INSERT INTO `tb_operation` VALUES (3, '测试', '吃二十');
INSERT INTO `tb_operation` VALUES (4, '测试对对对', '/jjjj');

-- ----------------------------
-- Table structure for tb_p1
-- ----------------------------
DROP TABLE IF EXISTS `tb_p1`;
CREATE TABLE `tb_p1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_p1
-- ----------------------------
INSERT INTO `tb_p1` VALUES (1, '哈哈哈');
INSERT INTO `tb_p1` VALUES (2, '嘿嘿');

-- ----------------------------
-- Table structure for tb_p2
-- ----------------------------
DROP TABLE IF EXISTS `tb_p2`;
CREATE TABLE `tb_p2`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_p2
-- ----------------------------
INSERT INTO `tb_p2` VALUES (1, '修改ddd', '000000');
INSERT INTO `tb_p2` VALUES (2, '哈哈', '11111');
INSERT INTO `tb_p2` VALUES (3, '哈哈', '111112');
INSERT INTO `tb_p2` VALUES (4, '哈哈', '111112');

-- ----------------------------
-- Table structure for tb_p3
-- ----------------------------
DROP TABLE IF EXISTS `tb_p3`;
CREATE TABLE `tb_p3`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p1_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_p3
-- ----------------------------
INSERT INTO `tb_p3` VALUES (1, '宝鸡虢镇', 1);
INSERT INTO `tb_p3` VALUES (2, '陈仓区', 2);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operations` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (14, '管理员', '1,2,3,4,5,6,8', '', 0);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(266) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (2, 'admin', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', '14', '2018-08-19', '2018-10-15', '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
