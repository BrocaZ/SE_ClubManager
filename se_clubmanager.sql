/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : se_clubmanager

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2019-11-20 20:31:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for act
-- ----------------------------
DROP TABLE IF EXISTS `act`;
CREATE TABLE `act` (
  `activityId` int(11) NOT NULL,
  `placeId` int(11) DEFAULT NULL,
  `associationId` int(11) DEFAULT NULL,
  `activityContent` varchar(4000) DEFAULT NULL,
  `leaderSno` varchar(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `attend_number` int(11) DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`activityId`),
  KEY `FK_Relationship_3` (`associationId`),
  KEY `FK_Relationship_7` (`placeId`),
  CONSTRAINT `FK_Relationship_3` FOREIGN KEY (`associationId`) REFERENCES `asso` (`associationId`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`placeId`) REFERENCES `pla` (`placeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of act
-- ----------------------------
INSERT INTO `act` VALUES ('1', '6', '2', '经典诵读竞赛', '31701002', '2019-11-22 18:30:00', '2019-11-22 20:30:00', '30', null, '待审批', '对外开放');
INSERT INTO `act` VALUES ('2', '9', '3', '轮滑灵魂杯', '31701291', '2019-10-29 09:30:00', '2019-10-29 15:30:00', '300', null, '已举办', '对外开放');
INSERT INTO `act` VALUES ('3', '4', '4', '羽毛球锦标赛', '31703828', '2019-12-03 09:30:00', '2019-12-10 14:30:00', '35', null, '待举办', '对外开放');
INSERT INTO `act` VALUES ('4', '7', '6', '摄影讲座', '31708092', '2019-12-12 18:00:00', '2019-12-12 19:30:00', '150', null, '审批不通过', '对外开放');
INSERT INTO `act` VALUES ('5', '7', '6', '摄影小课堂', '31708019', '2019-12-10 18:00:00', '2019-12-10 19:30:00', '50', null, '待举办', '社团内部活动');
INSERT INTO `act` VALUES ('6', '2', '2', '演讲与口才协会在下星期四下午于文四332开例会', '31701005', '2019-11-28 21:00:00', '2019-12-10 21:30:00', '0', null, '待举办', '社团内部活动');

-- ----------------------------
-- Table structure for act_p
-- ----------------------------
DROP TABLE IF EXISTS `act_p`;
CREATE TABLE `act_p` (
  `sno` varchar(50) NOT NULL,
  `activityId` int(11) NOT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`sno`,`activityId`),
  KEY `FK_Relationship_5` (`activityId`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`activityId`) REFERENCES `act` (`activityId`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`sno`) REFERENCES `stu` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of act_p
-- ----------------------------
INSERT INTO `act_p` VALUES ('31701001', '2', '已参加', null);
INSERT INTO `act_p` VALUES ('31701002', '2', '已参加', null);
INSERT INTO `act_p` VALUES ('31701003', '3', '报名', null);
INSERT INTO `act_p` VALUES ('31701004', '3', '报名', null);
INSERT INTO `act_p` VALUES ('31701005', '2', '未参加', null);

-- ----------------------------
-- Table structure for anno
-- ----------------------------
DROP TABLE IF EXISTS `anno`;
CREATE TABLE `anno` (
  `annoId` int(11) NOT NULL,
  `activityId` int(11) DEFAULT NULL,
  `annoContent` varchar(4000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `place` varchar(1000) DEFAULT NULL,
  `annomentType` varchar(100) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`annoId`),
  KEY `FK_Relationship_8` (`activityId`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`activityId`) REFERENCES `act` (`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of anno
-- ----------------------------
INSERT INTO `anno` VALUES ('1', '2', '轮滑灵魂杯成功举办', '2019-10-29 09:30:00', '北校操场旁边', '公开', '已发送', null);
INSERT INTO `anno` VALUES ('2', '1', '经典诵读比赛', '2019-11-22 20:30:00', '文三110', '公开', '待发送', null);
INSERT INTO `anno` VALUES ('3', '3', '羽毛球锦标赛', '2019-12-03 09:30:00', '羽毛球场', '公开', '已发送', null);
INSERT INTO `anno` VALUES ('4', '5', '摄影小课堂', '2019-12-10 18:00:00', '理四108', '社团内部', '已发送', null);
INSERT INTO `anno` VALUES ('5', '6', '演讲与口才协会在下星期四下午于文四332开例会', '2019-11-28 21:00:00', '文四332', '社团内部', '已发送', null);

-- ----------------------------
-- Table structure for asso
-- ----------------------------
DROP TABLE IF EXISTS `asso`;
CREATE TABLE `asso` (
  `associationId` int(11) NOT NULL,
  `placeId` int(11) DEFAULT NULL,
  `associationName` varchar(200) DEFAULT NULL,
  `logo` longblob,
  `chiefSno` varchar(50) DEFAULT NULL,
  `brief_introduction` varchar(4000) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`associationId`),
  KEY `FK_Relationship_6` (`placeId`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`placeId`) REFERENCES `pla` (`placeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asso
-- ----------------------------
INSERT INTO `asso` VALUES ('1', '1', '乐雅歌唱协会', null, '31701002', '乐雅歌唱协会是一个为了接纳更多热爱音乐，接近音乐并能和我们一起发展音乐的更多可能性的人的五星级音乐社团，我们没有太多意义上的偏向性，鼓励各种音乐风格的发展与传播，同时也希望吸收更多新风格。假如你在唱歌方面没有太大优势，有着强势的乐器天赋也可以成为我们很重要的一份子，乐雅，在保持音乐自由性的同时，却有着极强的对音乐更多的执念，每一个活动每一次表演我们都真诚对待，我们希望，让更多人去唱，去登台，去得到观众，去征服观众，最后，去找到自己，追求自己，动人的音乐应该让更多人听到，我们也在等待每一个吸引人的你，从第一人称出发，找到自己，勿忘初心。', '待审批', null);
INSERT INTO `asso` VALUES ('2', '2', '演讲与口才协会', null, '31701005', '浙江大学城市学院演讲与口才协会，简称口协，至今以创办16年，连续获得四年“五星社团”荣誉称号。在2014年，口协成功加入了“全国高校演讲与口才协会联盟”。口协和口协人一路成长，举办了辩论赛、城院演说家、经典诵读大赛等许多校内活动，选拔了许多出色的同学登上更大的平台，社团内部也走出了不少出色的主持人和辩手，在省级市级甚至国家级的比赛中获奖。“实干口协，为梦想发声”！我们为大学生提供发声平台，鼓励大家勇敢的表达自己。“实干、智慧、归属感”！在口协这个大家庭里，你能提升的不仅是表达和演讲等能力，还能感受到归属感，如果你想成为一名优秀的演讲者，那就赶快加入我们吧！口协大家庭，欢迎你的加入！', '已成立', null);
INSERT INTO `asso` VALUES ('3', '3', '自由灵魂轮滑社', null, '31701004', '自由灵魂轮滑社成立于2005年3月21日，成立至今获得过“十佳优秀社团”和“五星级社团”的称号，还获得了“杭州市十佳优秀社团”的荣誉，在2010年Freestyle树人大学站获得了轮舞第一，2011年轮滑盛典中收获个人速桩金牌，社团成员也有不少是cst成员代表过cst参加全国的多次比赛。 \r\n协会成立至今活跃在各个表演舞台上，纳新时的迎新表演，学院10周年的帅气亮相，社团文化节的精心准备等等，舞台上承载着我们太多的回忆。', '已成立', null);
INSERT INTO `asso` VALUES ('4', '4', '羽毛球协会', null, '31701172', '羽协成立至今已经走过了十余个年头,在这十余年间不断发展壮大,现已成为一个每年增加新会员二三百人,拥有组织人员50人，每学年举办四次校级羽毛球赛事的大型社团。自07年开始 羽协成为四星级社团，08年至12年羽协获得五星级社团以及十佳社团的荣誉，今年又再度被评为四星级社团。羽协每年所举办的四大经典赛事：新生杯，联盟杯，锦标赛，师生赛在全院都有不错的反响。我们希望羽能成为大家所喜爱的社团，为城院羽毛球爱好者创造良好的氛围，活跃校园气氛，丰富校园活动。', '已成立', null);
INSERT INTO `asso` VALUES ('5', '5', '网球协会', null, '31728382', '浙江大学城市学院网球协会是ZUCC最具影响力之一的体育类社团，成立于2000年9月。网球拍的每一次挥舞，汗水的挥洒，都是努力的痕迹。网球协会曾经获得浙江大学邀请赛团体第一，科院杯团体第三，城院邀请赛团体第二，省赛团体第二，第三，省赛男单第三，第四，男双第二，2018年，我们网球校队荣获省赛团体第二，男双第二的好成绩。社团荣誉为三星，四星。作为“元老社团”的网球协会，致力于推广和发展我校的网球运动，和其他高校相互交流学习，同时也和让大家深入地认识网球、了解网球、锻炼身体、塑造完美身形，结识更多的球友，给热爱网球的同学们提供展现的平台。', '已解散', null);
INSERT INTO `asso` VALUES ('6', '8', '摄影协会', null, '31708019', '浙江大学城市学院摄影协会成立于2003年，聚集了学院内众多的摄影爱好者，旨在为摄影爱好者提供一个交流沟通的平台，不论是不会拍照的小白还是快门数万的老摄友，只要你有一颗爱摄影的心，摄影协会就是你们的归属，我们一起记录美好和有趣事物，一起享受按下快门的乐趣和光与影的魅力。', '已成立', null);

-- ----------------------------
-- Table structure for asso_p
-- ----------------------------
DROP TABLE IF EXISTS `asso_p`;
CREATE TABLE `asso_p` (
  `sno` varchar(50) NOT NULL,
  `associationId` int(11) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`sno`,`associationId`),
  KEY `FK_Relationship_2` (`associationId`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`associationId`) REFERENCES `asso` (`associationId`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`sno`) REFERENCES `stu` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asso_p
-- ----------------------------
INSERT INTO `asso_p` VALUES ('31507128', '3', '社员', '离开', null);
INSERT INTO `asso_p` VALUES ('31701004', '3', '社长', '在职', null);
INSERT INTO `asso_p` VALUES ('31701005', '2', '社长', '在任', null);
INSERT INTO `asso_p` VALUES ('31802182', '2', '社员', '在职', null);
INSERT INTO `asso_p` VALUES ('31903281', '2', '社员', '在职', null);

-- ----------------------------
-- Table structure for pla
-- ----------------------------
DROP TABLE IF EXISTS `pla`;
CREATE TABLE `pla` (
  `placeId` int(11) NOT NULL,
  `placeName` varchar(500) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`placeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pla
-- ----------------------------
INSERT INTO `pla` VALUES ('1', '文三221', '100', '可用', null);
INSERT INTO `pla` VALUES ('2', '文四332', '100', '可用', null);
INSERT INTO `pla` VALUES ('3', '图书馆门口的空地', '1000', '可用', null);
INSERT INTO `pla` VALUES ('4', '羽毛球场', '600', '可用', null);
INSERT INTO `pla` VALUES ('5', '网球场', '500', '可用', null);
INSERT INTO `pla` VALUES ('6', '文三110', '500', '可用', null);
INSERT INTO `pla` VALUES ('7', '理四108', '300', '可用', null);
INSERT INTO `pla` VALUES ('8', '理四530', '90', '可用', null);
INSERT INTO `pla` VALUES ('9', '北校操场旁边', '0', '可用', null);

-- ----------------------------
-- Table structure for stu
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `sno` varchar(50) NOT NULL,
  `head_image` longblob,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `affiliated_branch` varchar(100) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `class` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES ('31507128', null, '王五', '男', '13528372919', '商学院', '国际贸易', '1504', '123456', '毕业', null);
INSERT INTO `stu` VALUES ('31701001', null, '陈章琦', '女', '13572638273', '计算分院', '计算机', '1701', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31701002', null, '罗灵洁', '女', '13588376250', '计算分院', '计算机', '1701', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31701003', null, '盛雪', '女', '18028172837', '计算分院', '计算机', '1701', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31701004', null, '王秋鸿', '女', '13587722884', '计算分院', '计算机', '1701', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31701005', null, '郑诗雨', '女', '13528372129', '计算分院', '计算机', '1701', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31701373', null, '郑珂亦', '女', '13528379823', '计算分院', '计算机', '1702', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31802182', null, '张三', '男', '13528379823', '外语分院', '商务英语', '1802', '123456', '在校', null);
INSERT INTO `stu` VALUES ('31903281', null, '李四', '男', '13522838091', '医学院', '临床医学', '1903', '123456', '在校', null);
