/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : se_clubmanager

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2019-11-23 19:40:41
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
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`sno`) REFERENCES `stu` (`sno`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`activityId`) REFERENCES `act` (`activityId`)
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
  `assoId` int(11) NOT NULL,
  `activityId` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `annoContent` varchar(4000) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `href` varchar(500) DEFAULT NULL,
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
INSERT INTO `anno` VALUES ('1', '3', '2', '轮滑灵魂杯成功举办', '轮滑灵魂杯成功举办', '2019-10-29 09:30:00', null, 'public', null, null);
INSERT INTO `anno` VALUES ('2', '2', '1', '经典诵读比赛', '经典诵读比赛', '2019-11-22 20:30:00', null, 'public', null, null);
INSERT INTO `anno` VALUES ('3', '4', '3', '羽毛球锦标赛', '羽毛球锦标赛', '2019-12-03 09:30:00', null, 'public', null, null);
INSERT INTO `anno` VALUES ('4', '6', '5', '摄影小课堂', '摄影小课堂', '2019-12-10 18:00:00', null, 'secret', null, null);
INSERT INTO `anno` VALUES ('5', '2', '6', '演讲与口才协会例会通知', '演讲与口才协会在下星期四下午于文四332开例会', '2019-11-28 21:00:00', null, 'secret', null, null);
INSERT INTO `anno` VALUES ('6', '6', '4', '关于举办摄影知识讲座的通知', '摄影知识讲座将于xx月xx日 在xxx举办', '2019-11-23 13:32:15', null, 'public', null, null);

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
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`sno`) REFERENCES `stu` (`sno`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`associationId`) REFERENCES `asso` (`associationId`)
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
INSERT INTO `pla` VALUES ('1', '文一312', '500', 'unavailable', null);
INSERT INTO `pla` VALUES ('2', '文四332', '100', 'available', null);
INSERT INTO `pla` VALUES ('3', '图书馆门口的空地', '1000', 'available', null);
INSERT INTO `pla` VALUES ('4', '羽毛球场', '600', 'available', null);
INSERT INTO `pla` VALUES ('5', '网球场', '500', 'available', null);
INSERT INTO `pla` VALUES ('6', '文三110', '500', 'available', null);
INSERT INTO `pla` VALUES ('7', '理四108', '300', 'available', null);
INSERT INTO `pla` VALUES ('8', '理四530', '90', 'available', null);
INSERT INTO `pla` VALUES ('9', '北校操场旁边', '0', 'available', null);

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
INSERT INTO `stu` VALUES ('31507128', null, '王五', '男', '13528372919', '商学院', '国际贸易', '1504', '123456', '管理员', null);
INSERT INTO `stu` VALUES ('31701001', null, '陈章琦', '女', '13572638273', '计算分院', '计算机', '1701', '123456', null, null);
INSERT INTO `stu` VALUES ('31701002', null, '罗灵洁', '女', '13588376250', '计算分院', '计算机', '1701', '123456', null, null);
INSERT INTO `stu` VALUES ('31701003', null, '盛雪', '女', '18028172837', '计算分院', '计算机', '1701', '123456', null, null);
INSERT INTO `stu` VALUES ('31701004', 0xFFD8FFE000104A46494600010101004800480000FFDB004300090607080706090807080A0A090B0D160F0D0C0C0D1B14151016201D2222201D1F1F2428342C242631271F1F2D3D2D3135373A3A3A232B3F443F384334393A37FFDB0043010A0A0A0D0C0D1A0F0F1A37251F253737373737373737373737373737373737373737373737373737373737373737373737373737373737373737373737373737FFC000110802BC02BC03012200021101031101FFC4001A000101010101010100000000000000000000040102030507FFC4002A10010100020202010401040301010000000001021103310421123233415122131442712352612481FFC40014010100000000000000000000000000000000FFC40014110100000000000000000000000000000000FFDA000C03010002110311003F00FD74000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006CED5CE924ED5CE811800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001B3B569276AE7408C000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006CED5CE924ED5CE811800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001B3B573A493B573A04600000000000000000000000000000000000000000000000000000000006E001B9FB66C1A3374DD068E774D83A19B9FB6FCA7EC00D80000000000000000000000000000000000000000000000000000000D9DAB9D249DAB04600000000000000000000000000000000000000000000000000016B9B6DEA6C1D6E337FAF6EB0E1B9F72C5387898CF7F2A08F59DFF001AEF1E1CAF72BE86384C63B0458F892F76BB9E161FBAA804DFDA63FBADFED71FDA80137F6B8FEEB3FB3C3F7550092F8587FDABCF2F1BE3D6EAF340F97971E73AC6B9FE53BC74FABA79F270CCC1F3FE4DDBDF93C4C6756BC72E2CB1FC5060E6FCE7F8D377F40E866CD834370D801B36006C00000000000000000000000000000000000006CED5CE924ED5CE81180000000000000000000000000000000000000000000001680E6E44973B22CE1F1BE3F9D826E2E1FEA77EB4B38B8261F9DBDA491A0C9246800000000000000000000CD4680E6E32FE1E79704CBF2F6012DF125FF2717C29FF0065A03E7DF164FCB9BC3A7D26681F32F153FA75F4F53F46A03E6DE3F8E3B647BF959FACB1D27C7A06800000000000000000000000000000000000D9DAB9D249DAB0460000000000000000000000000000000000000000000CB7405ADE3E3BC967E1BC3C5792CBF85FC7C7309D40670F14C27BD57A800000000000000000000000000000000000000E3973F863F2756E90F95CDF2DE101E5C97E7CB693D3319E9A0000000000000000000000000000000000000D9DAB9D249DAB9D02300000000000000000000000000000000000000000196E9BC5C773CA5FC331973CE48BF838BE18EAF60EB8B8E613A7A0000000000000000000000000000000000000000020F27C8DDB863B9657849BBBAA7CAE1D4B94FDA6C7D7A074000000000000000000000000000000000000003676AE749276AC1180000000000000000000000000000000000000039BEFD36DF4EF838EE5C92FE01EFE2717F1DD9EF6AD98C98CD4F4D00000000000000000000000000000000000000000006652653563E773F1DC33B97E1F49E3E471FCF0D4EC104BE9ACB3E39FC5A0000000000000000000000000000000000000D9DAB9D249DAB9D02300000000000000000000000000000000000002DD4073AF95D45FE2E1F1E39B9ED278F85BCD2FE1F464927A068000000000000000000000000000000000000000000001A0043E5716AE59E9E18DDC7D0F231F971D9ADBE7D9F0CFE20D000000000000000000000000000000000001B3B573A493B573A046000000000000000000000000000000000000E73EBD3A6613E5C9F1059E2613FA78E5AF6A5E7C18FC78E47A000000000000000000000000000000000000000000000000032CDBE7F938EB9ADFC3E8A5F2F0FE19640967439C2FA740000000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000000000BE9D78B37E4471974F7F171FE58D05D0000000000000000000000000000000000000000000000000001E5E4CDF1651EAE739BC6C07CBD6AE9D1CF3E3CDA00000000000000000000000000000000001B3B573A493B573A0460000000000000000000000000000000000039CFE95BE24FE18D45C9F4BE878B3FE1C41EC000000000000000000000000000000000000000000000000000003E6F973FFA1CBD3CB9FF0035794E81A000000000000000000000000000000003676AD24ED5CE811800000000000000000000000000000000000E73FA5F43C5FB18BE7E7F4BE878BF63107B000000000000000000000000000000000000000000000000000000F9FE5FDEAF2C7A7AF97F7ABCB1E81A000000000000000000000000000000003676AE749276AE7408C00000000000000000000000000000000000739FD2FA1E2FD8C5F3F3FA5F43C5FB1883D8000000000000000000000000000000000000000000000000000007CFF2FEF57963D3D7CBFBB5E58F40D000000000000000000000000000000001B3B573A493B573A0460000000000000000000000000000000000039CFE97D0F17EC62F9F9F4FA1E2FD8C41EC000000000000000000000000000000000000000000000000000003E7F97F76BCB1E9EBE5FDEAF2C7A06800000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000065F405BA24B95D475C3C77932D2FC38B1C64F536087FB5CF39DAEE0C2F1F14C6F71DC9274D000000000000000000000000000000000000000000000000000000049E478F9726772C6A7BC19E13DD7D365C65EE03E56F5755D6F6B39B86596C911652E196A834200000000000000000000000000000D9DAB9D249DAB046000000000000000000000000000000E73AE9CDF7417F8F8498CBAFC3DDC70FDAC5D800000000000000000000000000000000000000000000000000000000000017DA0F330FF0093717A5F2E77FE81262D660D00000000000000000000000000006CED5CE924ED5CE81180000000000000000000000000000039CBD3A739CF40FA5C1F6B1FF4F44FE367BC663B50000000003360D19B360D000066C1A0003366C1A000000336D0019B0680000000003360D19B360D00000000049E5E5EF4AB687CCCBFE4901E38B598B40000000000000000000000000001B3B573A493B573A046000000000000000000000000000000CCA6DA03D3C4CF5C977D2F977D3E54B70BB8FA3C39CCB09FB07A000000000000000000000000000000000000000000000000000000000000E792FC70B5F3B972FEA67BED5795C9A971DF68F080EA000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000001994DC7AF8BC9ACF57D479B9BFC7DC07D497736D7878FC932C71C77EDEE000000000000000000000000000000000000000000000000000000038E4CBE38DAEEDD22F2B97F95C67541E3CB97F533D8E719A740000000000000000000000000000000D9DAB9D249DAB9D0230000000000000000000000000000000002801C597C33DBE87167F3C657CECA6E3D3C7E5B8E531BD03E80CC72994DB40000000000000000000000000000000000000000000000000079F2F24C30B77EE038F2397E38D9BF6877F2BBBDB7933BC9C9F2AD9340000000000000000000000000000000000D9DAB493B573A046000000000000000000000000000000000000E729E9D00F5F1B9ACF8E17AFDAD9771F2ECD5DA9F1B9B5AC740B00000000000000000000000000000000000000000000001CE797C66C0E4CE618EF6F9FCDC973CEFE9BCFCB73CAE2E319A024D3400000000000000000000000000000000001B3B573A493B573A046000000000000000000000000000000000000000E6FABB7459E815F899FCB8FFF00D50F9DE3E7F1CE63FF00AFA33D8000000000000000000000000000000000000000000087CACFE5BC15F2E7F0C76F9D95F972DA0633D3400000000000000000000000000000000000001B3B569276AE7408C0000000000000000000000000000000000000000067596D6F8B9FCF0DA2B3D3BE0E4F86531FFD07D1192EE340000000000000000000000000000000000000079F36730C7609BCBE4F94B8CFDBC319E8CAFCB92DFC5680000000000000000000000000000000000000003676AE749276AE7408C000000000000000000000000000000000000000001CDF576E8B014F8BCBBC7DF6A9F2F0CAE19CFD3E870E733C7701E80000000000000000000000000000000000032DD21F2797E56E3FAAF6F279758FA473DDDD06E33D3400000000000000000000000000000000000000001B3B569276AE7408C00000000000000000000000000000000000000000000658EF8397E16635CB9B3DEC1F531CBE51A87C6E6F8FACEFBB56CBB068000000000000000000000000000003CF9B9271E3BAEB933984F6F9FCBC973CACDEE039CB2B9E577D364649A680000000000000000000000000000000000000000003676AE749276AE7408C000000000000000000000000000000000000000000000007366AEE28F1F9F5EB3BEEBC5C59ACF107D69D0E70BB91D00000000000000000000000000E73CE613DBA4BE6DB240787372DCED9327123319EDD00000000000000000000000000000000000000000000003676AE749276AE7408C000000000000000000000000000000000000000000000001CE5F562E9CE5F5C07D1E1BB8F47970F4F500000000000000000000000001279FD62AD279FD6209B16B31680000000000000000000000000000000000000000000003676AE749276AE7408C000000000000000000000000000000000000000000000001CE5F5E2EAF4E64DE78FFB07D2E39A8ED93A8D000000000000000000000000012F9D373154F0F2B1F94808B16B99F55740000000000000000000000000000000000000000000000D9DAB9D249DAB04600000000000000000000000000000000000000000000005A0CCAFA7A78B87CF76FE2BCA4B9E5353D3E87071CE397FF41EB3A0000000000000000000000000007394DC7403E5652E39E5FEDD451E570EE7A4B2EAE81D000000000000000000000000000000000000000000000D9DAB9D249DAB9D023000000000000000000000000000000000000000000065BA02DD326F3CA4D7A6E18DCF293A5DC3C338E77B067070CC23DC00000000000000000000000000000000B368BC8E0D7F29EF6B4D6C1F2A5B2EABA51CFE3EE6F6977F1CB40E82500000000000000000000000000000000000000001B3B573A493B573A0460000000000000000000000000000000000000006D972D331C6E7649EB605AF4E1E0BC977BD3DF87C6B8EADB2A9924FC039E2E3F84D76EC000000000000000000000000000000000000007873707CE7ABA7B80F979E178EFEC976FA39E1329D449CDE3658EF2DFA0790E77F1BAD365D834000000000000000000000000000000000006CED5CE924ED5CE81180000000000000000000000000000003360D1CDC9B8E373BA805CB4C9BCEEA28E3F1B2DCB7DC53871638C9FC66C12F178D95B32B7D2BC38F1C67D336EE4D007FA0000000000000000000000000000000000000000000006592F6D01E3CBC33297524A9393C7CB0DDDBE8B2C97B80F9532D7A752ED772706394BA9225CFC6CE7BD8381CDB71EDB32D8342500000000000000000000000000001B3B573A493B573A0460000000000000000000000001D39B9C074CB969B8F1679F55471F8D67D52504B37974F4C7C6CF2FCADC78B09FE2EE493A04FC7E3EBEA92BDB1E3C27534EC035A0000000000000000000000000000000000000000000000000000000006592B4079E5C5859F4C4FC9E35BF4FA5803E6E5C19E1DD71F2D3EA5C65EE3CF3E0C2CF58C0412EDAF5E4F1B3FC7A78E532C3B068C994AD00000000000000000000006CED5A49DAB9D023000000000000000000073720756E9CDCB6EB0E2CF3F73A55C5E3E327F2C41363C19E7D29E2F1E4FAE4AF7C7198F534E81CE38638F534E800000000000000000000000000000000000000000000000000000000000000000000000000D6DC65C585EF18EC047C9E3657E89227CB0CB0FA9F51C65C78653DCD83E6CCA3A7BF2F8D7FC2693E58E587D40D192EDA0000000000000000D9DAB9D249DAB9D02300000000000000065B216FE23AE3E1CB3B2FC7D039932CFE951C3E37E7931F6F7E3E0C30FC3D41CE1C78E13F8CD3A000000000000000000000000000000000000000000000000000000000000000000000000000000000000001E59F0E19F736F501F3F9783396FC23CB7ABABDBEA59B89F97C7C6CDE33D825DEC66586785F73509760D000000000001B3B573A493B573A0460000000000CB740DB5CFBCAEB1F6DC71CB3BA93D2BE0F1E4D657B079F078FB92E72ED5E18CC26A3A93400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003CF938B1CE7B45CBC370B7E32E9F4599E3329AA0F992EBD5EDAF6E7E0D6EE3BB53CDE3EB2F541D06C00000006CED5CE924ED5CE811800000032DD016E9BC785E4CBAF471F1DE4CB565D2EE2E39863019C3C53091ECC01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C01A30068C00B369BC8E097794ED480F97AB8DD59A752AAF238665BCBF28ECB8DD5F40E83600003676AE749276AC11800016832DD378F0BC996ACF4CC71B9DD2EE1E398E301BC584C318F40000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013F91C332972FCA8340F97EF1BABE9D28F27865DE5F98965B3BF40E8006CED5CE924ED5CE81180039BEDD57A78B379D07B78FC531932FDA824D400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001966E23F278BF97C96B9E49FC283E6E35D197603676AE749276AE740FFFD9, '王秋鸿', '女', '13588332884', '计算分院', '计算机', '1701', '987654321', null, null);
INSERT INTO `stu` VALUES ('31701005', null, '郑诗雨', '女', '13528372129', '计算分院', '计算机', '1701', '123456', null, null);
INSERT INTO `stu` VALUES ('31701373', null, '郑珂亦', '女', '13528379823', '计算分院', '计算机', '1702', '123456', null, null);
INSERT INTO `stu` VALUES ('31802182', null, '张三', '男', '13528379823', '外语分院', '商务英语', '1802', '123456', null, null);
INSERT INTO `stu` VALUES ('31903281', null, '李四', '男', '13522838091', '医学院', '临床医学', '1903', '123456', null, null);
