-- MySQL dump 10.13  Distrib 5.6.31, for Linux (x86_64)
--
-- Host: localhost    Database: qingchengkeji
-- ------------------------------------------------------
-- Server version	5.6.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `categoryId` varchar(40) NOT NULL,
  `categoryName` varchar(40) DEFAULT NULL,
  `categoryNameCn` varchar(40) DEFAULT NULL,
  `categoryIconName` varchar(40) DEFAULT NULL,
  `categoryIsOnLine` tinyint(1) DEFAULT '0',
  UNIQUE KEY `Category_categoryId_uindex` (`categoryId`),
  UNIQUE KEY `Category_categoryNameCn_uindex` (`categoryNameCn`),
  UNIQUE KEY `Category_categoryName_uindex` (`categoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES ('c-0001','bigdata','大数据','cubes',1),('c-0002','cloud computing','云计算','cloud',1),('c-0003','ios','苹果ios','apple',1),('c-0004','android ','安卓','android ',1),('c-0005','windows','windows','windows',1),('c-0006','linux','linux','linux ',1),('c-0007','JavaScript','前端JavaScript','chrome ',1),('c-0008','git','git版本控制','git',1),('c-0009','database','数据库','database',1),('c-0010','Css3','前端CSS','css3',1),('c-0011','Html5','Html5','html5',1);
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `courseID` varchar(200) NOT NULL DEFAULT '',
  `courseName` varchar(200) DEFAULT NULL,
  `courseSubName` varchar(1200) DEFAULT NULL,
  `courseUrl` varchar(1200) DEFAULT NULL,
  `courseDescription` varchar(2000) DEFAULT NULL,
  `coursePoster` varchar(200) DEFAULT '/poster.png',
  `courseOnLine` tinyint(1) DEFAULT '1',
  `coursePlayTimes` int(11) DEFAULT '1000',
  `courseIsRecommended` tinyint(1) DEFAULT '0',
  `courseRecommendStart` int(11) DEFAULT '0',
  `courseIsRecommendedForLoop` tinyint(1) DEFAULT '0',
  `categoryId` varchar(40) DEFAULT NULL,
  `teacherEmail` varchar(200) DEFAULT NULL,
  `materialDirName` varchar(200) DEFAULT '',
  PRIMARY KEY (`courseID`),
  KEY `Course_Category_categoryId_fk` (`categoryId`),
  KEY `Course_user_email_fk` (`teacherEmail`),
  CONSTRAINT `Course_Category_categoryId_fk` FOREIGN KEY (`categoryId`) REFERENCES `Category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Course_user_email_fk` FOREIGN KEY (`teacherEmail`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('c-0001','武神赵子龙电视剧','2015年最火的大数据技术--spark','/qingcheng/video','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,7,1,'c-0001','teacher1@qq.com','material'),('c-0002','冰河世纪电视剧','2015年最火的大数据技术--hadoop','/qingcheng/video2','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,1000,1,1,1,'c-0002','teacher1@qq.com','material'),('c-0003','武神赵子龙电视剧2','2015年最火的大数据技术--spark2','/qingcheng/video','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,5,1,'c-0003','teacher1@qq.com','material'),('c-0004','冰河世纪电视剧2','2015年最火的大数据技术--hadoop2','/qingcheng/video2','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,1000,1,4,1,'c-0004','teacher1@qq.com','material'),('c-0005','冰河世纪电视剧3','2015年最火的大数据技术--hadoop3','/qingcheng/video2','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,859,1,7,1,'c-0003','teacher1@qq.com','material'),('c-0006','武神赵子龙电视剧3','2015年最火的大数据技术--spark3','/qingcheng/video','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,5,1,'c-0002','teacher1@qq.com','material'),('c-0007','武神赵子龙电视剧4','2015年最火的大数据技术--spark4','/qingcheng/video2','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,4,1,'c-0001','teacher1@qq.com','material'),('c-0008','冰河世纪电视剧5','2015年最火的大数据技术--hadoop5','/qingcheng/video','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,477,1,3,1,'c-0004','teacher2@qq.com',''),('c-0009','spark实战教程','2017年最火的大数据技术--spark','/qingcheng/video3','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,4,1,'c-0005','teacher1@qq.com',''),('c-0010','武神赵子龙电视剧9','2015年最火的大数据技术--hadoop3','/qingcheng/video3','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,1000,1,1,1,'c-0001','teacher1@qq.com',''),('c-0011','武神赵子龙电视剧11','2015年最火的大数据技术--hadoop5','/qingcheng/video4','spark实战教程是张东老师,努力打造的精品课程!','/poster.png',1,1000,1,505,1,'c-0009','teacher1@qq.com',''),('c-0012','冰河世纪电视剧3','2017年最火的大数据技术--spark','/qingcheng/video4','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,1000,1,8,1,'c-0007','teacher2@qq.com',''),('c-0013','冰河世纪电视剧4001','2015年最火的大数据技术--hadoop5999','/qingcheng/video2','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',1,23445,1,3,1,'c-0001','teacher2@qq.com',''),('c-0014','冰河世纪电视剧3','2017年最火的大数据技术--spark','/qingcheng/maizi333','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',0,1000,1,1,1,'c-0001','teacher2@qq.com',''),('c-0015','冰河世纪电视剧4001','2017年最火的大数据技术--spark','/qingcheng/mayun','hadoop实战教程是刘东老师,努力打造的精品课程!','/poster.png',0,33333333,1,111111,1,'c-0001','teacher2@qq.com','');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseComment`
--

DROP TABLE IF EXISTS `CourseComment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CourseComment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) CHARACTER SET latin1 NOT NULL,
  `courseID` varchar(200) CHARACTER SET latin1 NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `commentCanDisplay` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseComment`
--

LOCK TABLES `CourseComment` WRITE;
/*!40000 ALTER TABLE `CourseComment` DISABLE KEYS */;
INSERT INTO `CourseComment` VALUES (158,'565380490@qq.com','c-0007','城：这个课程真心不错，老师讲的非常棒！给个好评！','2017-06-11 08:05:46',1),(159,'lisi@qq.com','c-0007','刀 :这个课程真心不错，老师讲的非常棒！给个好评！','2017-06-11 08:06:38',1),(160,'565380490@qq.com','c-0007','城：这个课程真心不错，老师讲的非常棒！给个好评！','2017-06-11 08:06:57',1),(161,'565380490@qq.com','c-0007','应该错不了！c','2017-06-11 08:07:33',1),(162,'lisi@qq.com','c-0007','应该错不了同意！','2017-06-11 08:07:57',1),(163,'lisi@qq.com','c-0009','这个课程老师讲的非常棒！我喜欢，是我的菜！','2017-06-11 08:08:57',1),(164,'565380490@qq.com','c-0009','我也是这么觉得的，这个课程还是非常给力的！','2017-06-11 08:09:30',1),(165,'565380490@qq.com','c-0009','真心觉得这个课程好，面向终端用户！','2017-06-11 08:10:45',1),(166,'565380490@qq.com','c-0009','我也是这么觉得的！','2017-06-11 08:11:23',1),(167,'565380490@qq.com','c-0009','好吧，真心喜欢这个课程！','2017-06-11 08:11:48',1),(168,'565380490@qq.com','c-0009','好好学习天天向上','2017-06-11 08:12:10',1),(169,'lisi@qq.com','c-0009','老子也是非常爱学生的主子！','2017-06-11 08:12:35',1),(170,'565380490@qq.com','c-0009','虚心使人进步，骄傲使人落后！','2017-06-11 08:14:58',1),(171,'565380490@qq.com','c-0009','你懂什么啊！这些东西不是你能理解的好吧！','2017-06-11 08:15:19',1);
/*!40000 ALTER TABLE `CourseComment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DfsUrls`
--

DROP TABLE IF EXISTS `DfsUrls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DfsUrls` (
  `dfsUrl` varchar(120) NOT NULL DEFAULT '',
  `trashTask` tinyint(1) DEFAULT '0',
  `markTask` tinyint(1) DEFAULT '0',
  `downloadTask` tinyint(1) DEFAULT '0',
  `copyTask` tinyint(1) DEFAULT '0',
  `moveTask` tinyint(1) DEFAULT '0',
  `historyTask` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`dfsUrl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DfsUrls`
--

LOCK TABLES `DfsUrls` WRITE;
/*!40000 ALTER TABLE `DfsUrls` DISABLE KEYS */;
INSERT INTO `DfsUrls` VALUES ('',0,0,0,0,0,0),('/',0,0,0,0,0,0),('/alluxio',0,0,0,0,0,0),('/alluxio/journal',0,0,0,0,0,0),('/alluxio/journal/BlockMaster',0,0,0,0,0,0),('/alluxio/journal/FileSystemMaster',0,0,0,0,0,0),('/alluxio/journal/FileSystemMaster/checkpoint.data',0,0,0,0,0,0),('/alluxio/journal/LineageMaster',0,0,0,0,0,0),('/alluxio/journal/_format_1483670018755',0,0,0,0,0,0),('/f4',0,0,0,0,0,0),('/flink-metadata',0,0,0,0,0,0),('/flink-metadata/checkpoints',0,0,0,0,0,0),('/flink-metadata/checkpoints/30c131a43001089d7bdb67efcd1773bc',0,0,0,0,0,0),('/flink-metadata/checkpoints/a06ac7d01a40931098ec4abce73aa607',0,0,0,0,0,0),('/flink-metadata/checkpoints/ea5b561a458801a1c66bc828eb0ebe02',0,0,0,0,0,0),('/flink-metadata/recovery',0,0,0,0,0,0),('/flink-metadata/recovery/blob',0,0,0,0,0,0),('/flink-metadata/recovery/submittedJobGrapha0950e89dc0d',0,0,0,0,0,0),('/history',0,0,0,0,0,0),('/history/done',0,0,0,0,0,0),('/history/done/2016',0,0,0,0,0,0),('/history/done/2016/11',0,0,0,0,0,0),('/history/done/2016/11/09',0,0,0,0,0,0),('/history/done/2016/11/10',0,0,0,0,0,0),('/history/done/2016/11/13',0,0,0,0,0,0),('/history/done/2016/11/22',0,0,0,0,0,0),('/history/done/2016/11/25',0,0,0,0,0,0),('/history/done_intermediate',0,0,0,0,0,0),('/history/done_intermediate/root',0,0,0,0,0,0),('/iiiiiii',0,0,0,0,0,0),('/input',0,0,0,0,0,1),('/input/2.txt',0,0,0,0,0,0),('/input/bigdata',0,0,0,0,0,0),('/input/bigdata/bank',0,0,0,0,0,0),('/input/bigdata/bank/bank-full.csv',0,0,0,0,0,0),('/input/bigdata/bank/bank-names.txt',0,0,0,0,0,0),('/input/bigdata/bank/bank.csv',0,0,0,0,0,0),('/input/flink',0,0,0,0,0,0),('/input/flink/applestock.csv',0,0,0,0,0,0),('/input/flink/README.txt',0,0,0,0,0,0),('/input/flink/sales.csv',0,0,0,0,0,0),('/input/flink/stream',0,0,0,0,0,0),('/input/kv2',0,0,0,0,0,0),('/input/kv2/kv',0,0,0,0,0,0),('/input/mahout',0,0,0,0,0,0),('/input/mahout/20news_all',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-test',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-test/alt.atheism',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-test/misc.forsale',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-test/soc.religion.christian',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-train',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-train/alt.atheism',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-train/misc.forsale',0,0,0,0,0,0),('/input/mahout/20news_all/20news-bydate-train/soc.religion.christian',0,0,0,0,0,0),('/input/mahout/20news_all_mi',0,0,0,0,0,0),('/input/mahout/20news_all_rt',0,0,0,0,0,0),('/input/mahout/20news_all_rt/train-vectors',0,0,0,0,0,0),('/input/mahout/20news_all_seq',0,0,0,0,0,0),('/input/mahout/20news_all_testing',0,0,0,0,0,0),('/input/mahout/20news_all_vec',0,0,0,0,0,0),('/input/mahout/20news_all_vec/tokenized-documents',0,0,0,0,0,0),('/input/mahout/input001',0,0,0,0,0,0),('/input/mahout/pa3',0,0,0,0,0,0),('/input/mahout/pa3/mi',0,0,0,0,0,0),('/input/mahout/pa3/mi/nbmodel',0,0,0,0,0,0),('/input/mahout/pa3/re',0,0,0,0,0,0),('/input/mahout/pa3/re/train-vectors',0,0,0,0,0,0),('/input/mahout/pa3/tst',0,0,0,0,0,0),('/input/mahout/pa3/vec',0,0,0,0,0,0),('/input/mahout/pa3/vec/tf-vectors',0,0,0,0,0,0),('/input/mahout/pa3/vec/tfidf-vectors',0,0,0,0,0,0),('/input/mahout/pa3/vec/wordcount',0,0,0,0,0,0),('/input/mahout/pre',0,0,0,0,0,0),('/input/README.txt',0,0,0,0,0,0),('/input/spark',0,0,0,0,0,0),('/input/spark/checkpoint',0,0,0,0,0,0),('/input/spark/checkpoint/d81561c3-0756-478d-b760-603f7a1f009b',0,0,0,0,0,0),('/input/spark/checkpoint/d81561c3-0756-478d-b760-603f7a1f009b/rdd-2',0,0,0,0,0,0),('/output',0,0,0,0,0,0),('/output/datasink',0,0,0,0,0,0),('/output/datasink/sales.csv',0,0,0,0,0,0),('/output/flink',0,0,0,0,0,0),('/output/flink/dataset',0,0,0,0,0,0),('/output/flink/dataset/testdata',0,0,0,0,0,0),('/output/flink/datasink',0,0,0,0,0,0),('/output/flink/itblog',0,0,0,0,0,0),('/output/flink/itblog/MultipleTextOutputFormat',0,0,0,0,0,0),('/output/flink/MultipleTextOutputFormat',0,0,0,0,0,0),('/output/flink/MultipleTextOutputFormat/java',0,0,0,0,0,0),('/output/flink/MultipleTextOutputFormat/java/002',0,0,0,0,0,0),('/output/flink/MultipleTextOutputFormat/scala',0,0,0,0,0,0),('/output/flink/MultipleTextOutputFormat/scala/001',0,0,0,0,0,0),('/output/flink/readme_result',0,0,0,0,0,0),('/output/spark',0,0,0,0,0,0),('/output/spark/alluxio',0,0,0,0,0,0),('/output/spark/alluxio/test001.txt',0,0,0,0,0,0),('/output/spark/alluxio/test002.txt',0,0,0,0,0,0),('/output/spark/file1.avro',0,0,0,0,0,0),('/output/spark/file2.avro',0,0,0,0,0,0),('/output/spark/numbers',0,0,0,0,0,0),('/output/spark/numbers1',0,0,0,0,0,0),('/output/spark/numbers1/part-00000',0,0,0,0,0,0),('/output/spark/sparksession',0,0,0,0,0,0),('/output/spark/sparksession/saveAsTextFile',0,0,0,0,0,0),('/output/spark/sparksession/text',0,0,0,0,0,0),('/output/spark/sparksession/text/writerText',0,0,0,0,0,0),('/output/spark/sparksession/writerForma',0,0,0,0,0,0),('/qingcheng',0,0,0,0,0,0),('/qingcheng/carousel',0,0,0,0,0,0),('/qingcheng/docs',0,0,0,0,0,0),('/qingcheng/docs/test',0,0,0,0,0,0),('/qingcheng/mayun',0,0,0,0,0,0),('/qingcheng/mp3',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo.mp3',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo.mp3.gz2',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.1484811443900',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.biz',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.bz2',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.bzip',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.default',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.default.1484811077059',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.deflate',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.gz',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.gz2',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.gzip',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.lz4',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.lzo',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.snappy',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.SnappyCodec',0,0,0,0,0,0),('/qingcheng/mp3/xiaopingguo2.mp3.zip',0,0,0,0,0,0),('/qingcheng/mp3/yy',0,0,0,0,0,0),('/qingcheng/rr',0,0,0,0,0,0),('/qingcheng/seq',0,0,0,0,0,0),('/qingcheng/seq/yingyong_gg0_720p.png',0,0,0,0,0,0),('/qingcheng/seq/yingyong_gg1_720p.mp4',0,0,0,0,0,0),('/qingcheng/test',0,0,0,0,0,0),('/qingcheng/test2',0,0,0,0,0,0),('/qingcheng/thshdshd',0,0,0,0,0,0),('/qingcheng/ui',0,0,0,0,0,0),('/qingcheng/video',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.mp4',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.mp4.default',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.png',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.png.deflate',0,0,0,0,0,0),('/qingcheng/video/bingchuanshiji3.png.gz',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.mp4',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.mp4.default',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.png',0,0,0,0,0,0),('/qingcheng/video/demo_meizhuang_1080p.png.gz',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.mp4',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.mp4.default',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.png',0,0,0,0,0,0),('/qingcheng/video/demo_shop_1080p.png.gz',0,0,0,0,0,0),('/qingcheng/video/oceans.mp4',0,0,0,0,0,0),('/qingcheng/video/oceans.mp4.default',0,0,0,0,0,0),('/qingcheng/video/oceans.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/oceans.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/oceans.png',0,0,0,0,0,0),('/qingcheng/video/poster.png',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg0_720p.mp4',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg0_720p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg0_720p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg0_720p.png',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg1_720p.mp4',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg1_720p.png',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg2_720p.mp4',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg2_720p.png',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg3_720p.mp4',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg3_720p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/yingyong_gg3_720p.png',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.mp4',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.mp4.bz2',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.mp4.default',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video/yingyong_m0_720p.png',0,0,0,0,0,0),('/qingcheng/video/yy',0,0,0,0,0,0),('/qingcheng/video2',0,0,0,0,0,0),('/qingcheng/video2/poster.png.default',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg0_720p.mp4',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg0_720p.mp4.default',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg0_720p.png',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg1_720p.mp4',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg1_720p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg2_720p.mp4.default',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg2_720p.mp4.gz',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg3_720p.mp4',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg3_720p.mp4.default',0,0,0,0,0,0),('/qingcheng/video2/yingyong_gg3_720p.png',0,0,0,0,0,0),('/qingcheng/video2/yingyong_m0_720p.mp4',0,0,0,0,0,0),('/qingcheng/video2/yingyong_m0_720p.mp4.bz2',0,0,0,0,0,0),('/qingcheng/video2/yingyong_m0_720p.mp4.default',0,0,0,0,0,0),('/qingcheng/video2/yingyong_m0_720p.mp4.deflate',0,0,0,0,0,0),('/qingcheng/video2/yingyong_m0_720p.mp4.gz',0,0,0,0,0,0),('/qingcheng/yy',0,0,0,0,0,0),('/test',0,0,0,0,0,0),('/test/f1',0,0,0,0,0,0),('/test/f1/poster.png',0,0,0,0,0,0),('/test/f2',0,0,0,0,0,0),('/test/f4',0,0,0,0,0,0),('/test/f4/f4-1',0,0,0,0,0,0),('/test/f4/poster.png',0,0,0,0,0,0),('/test/pom.xml',0,0,0,0,0,0),('/test/xiaojiantou',0,0,0,0,0,0),('/test0000001',0,0,0,0,0,0),('/test0000001/f2',0,0,0,0,0,0),('/test0000001/f2/poster.png',0,0,0,0,0,0),('/test0000001/f2/ttt',0,0,0,0,0,0),('/test0000001/f222',0,0,0,0,0,0),('/test0000001/f222www',0,0,0,0,0,0),('/test0000001/f3',0,0,0,0,0,0),('/test0000001/f3/f4-1',0,0,0,0,0,0),('/test0000001/f3/f4.1483708855154',0,0,0,0,0,0),('/test0000001/f3/f4.1483708855154/f4-1',0,0,0,0,0,0),('/test0000001/f3/f4.1483708855154/f4-1/poster.png',0,0,0,0,0,0),('/test0000001/f3/f4.1483708855154/poster.png',0,0,0,0,0,0),('/test0000001/f3/poster.png',0,0,0,0,0,0),('/test0000001/f4',0,0,0,0,0,0),('/test0000001/f4.1483708855154',0,0,0,0,0,0),('/test0000001/f4/f4-1',0,0,0,0,0,0),('/test0000001/f4/f4-1/poster.png',0,0,0,0,0,0),('/test0000001/f4/poster.png',0,0,0,0,0,0),('/test0000001/ffff',0,0,0,0,0,0),('/test0000001/gg',0,0,0,0,0,0),('/test0000001/ggee',0,0,0,0,0,0),('/test0000001/iiiiiii',0,0,0,0,0,0),('/test0000001/poster.png',0,0,0,0,0,0),('/test0000001/poster.png.1483708665006',0,0,0,0,0,0),('/test0000001/rrr',0,0,0,0,0,0),('/test0000001/test0000004',0,0,0,0,0,0),('/test0000001/ttt',0,0,0,0,0,0),('/test0000001/uiui',0,0,0,0,0,0),('/test0000001/xiaozhang',0,0,0,0,0,0),('/test0000002',0,0,0,0,0,0),('/test0000002/eee',0,0,0,0,0,0),('/test0000002/f2',0,0,0,0,0,0),('/test0000002/f2/poster.png',0,0,0,0,0,0),('/test0000002/f3',0,0,0,0,0,0),('/test0000002/f3/poster.png',0,0,0,0,0,0),('/test0000002/f4',0,0,0,0,0,0),('/test0000002/f4-1',0,0,0,0,0,0),('/test0000002/f4.1483708855154',0,0,0,0,0,0),('/test0000002/f4/f4-1',0,0,0,0,0,0),('/test0000002/f4/f4-1/poster.png',0,0,0,0,0,0),('/test0000002/f4/f4.1483708855154',0,0,0,0,0,0),('/test0000002/f4/poster.png',0,0,0,0,0,0),('/test0000002/f4/test0000004',0,0,0,0,0,0),('/test0000002/ff',0,0,0,0,0,0),('/test0000002/hh',0,0,0,0,0,0),('/test0000002/hhh',0,0,0,0,0,0),('/test0000002/hhhjjj',0,0,0,0,0,0),('/test0000002/poster.png',0,0,0,0,0,0),('/test0000002/ui',0,0,0,0,0,0),('/test0000003',0,0,0,0,0,0),('/test0000003/f2',0,0,0,0,0,0),('/test0000003/f2/poster.png',0,0,0,0,0,0),('/test0000003/f3',0,0,0,0,0,0),('/test0000003/f3/poster.png',0,0,0,0,0,0),('/test0000003/f4',0,0,0,0,0,0),('/test0000003/f4/f4-1',0,0,0,0,0,0),('/test0000003/f4/f4-1/poster.png',0,0,0,0,0,0),('/test0000003/f4/poster.png',0,0,0,0,0,0),('/test0000003/poster.png',0,0,0,0,0,0),('/test0000004',0,0,0,0,0,0),('/test0000004/f3',0,0,0,0,0,0),('/test0000004/f4/f4-1/poster.png',0,0,0,0,0,0),('/test0000004/f4/poster.png',0,0,0,0,0,0),('/test0000004/poster.png',0,0,0,0,0,0),('/test0000005',0,0,0,0,0,0),('/test0000005/f2',0,0,0,0,0,0),('/test0000005/f2/iiiiiii',0,0,0,0,0,0),('/test0000005/f2/iiiiiiir',0,0,0,0,0,0),('/test0000005/f2/poster.png',0,0,0,0,0,0),('/test0000005/f2/poster.png.gz',0,0,0,0,0,0),('/test0000005/f3',0,0,0,0,0,0),('/test0000005/f3/poster.png',0,0,0,0,0,0),('/test0000005/f4',0,0,0,0,0,0),('/test0000005/f4/poster.png',0,0,0,0,0,0),('/test0000005/gg',0,0,0,0,0,0),('/test0000005/hh',0,0,0,0,0,0),('/test0000005/poster.png',0,0,0,0,0,0),('/test0000005/test0000001',0,0,0,0,0,0),('/test0000005/test0000002',0,0,0,0,0,0),('/test0000005/test0000003',0,0,0,0,0,0),('/test0000005/xiaozhang',0,0,0,0,0,0),('/test001',0,0,0,0,0,0),('/test001/f4',0,0,0,0,0,0),('/test001/f4/f4-1',0,0,0,0,0,0),('/test002',0,0,0,0,0,0),('/test002/f4',0,0,0,0,0,0),('/test002/f4/f4-1',0,0,0,0,0,0),('/test300',0,0,0,0,0,0),('/test300/test',0,0,0,0,0,0),('/test300/test/f1',0,0,0,0,0,0),('/test300/test/f3',0,0,0,0,0,0),('/test300/test/f4',0,0,0,0,0,0),('/test300/test/f4/f4-1',0,0,0,0,0,0),('/test301',0,0,0,0,0,0),('/test301/test',0,0,0,0,0,0),('/test301/test/f1',0,0,0,0,0,0),('/test301/test/f1/.poster.png.crc',0,0,0,0,0,0),('/test301/test/f1/poster.png',0,0,0,0,0,0),('/test301/test/f2',0,0,0,0,0,0),('/test301/test/f3',0,0,0,0,0,0),('/test301/test/f4',0,0,0,0,0,0),('/test301/test/f4/f4-1',0,0,0,0,0,0),('/tmp',0,0,0,0,0,0),('/tmp/hadoop-yarn',0,0,0,0,0,0),('/tmp/hadoop-yarn/staging',0,0,0,0,0,0),('/tmp/logs',0,0,0,0,0,0),('/tmp/logs/root',0,0,0,0,0,0),('/tmp/logs/root/logs',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1478759969788_0001',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1478759969788_0006',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1478759969788_0009',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1478759969788_0011',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1479104909268_0001',0,0,0,0,0,0),('/tmp/logs/root/logs/application_1480225702874_0001',0,0,0,0,0,0),('/user',0,0,0,0,0,0),('/user/root',0,0,0,0,0,0),('/user/root/.flink',0,0,0,0,0,0),('/user/root/.flink/application_1480226943961_0008',0,0,0,0,0,0),('/user/root/.flink/application_1480226943961_0008/lib',0,0,0,0,0,0),('/user/root/output',0,0,0,0,0,0),('/user/root/output/clusters-1',0,0,0,0,0,0),('/user/root/output/data',0,0,0,0,0,0),('/user/root/testdata',0,0,0,0,0,0);
/*!40000 ALTER TABLE `DfsUrls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserCourse`
--

DROP TABLE IF EXISTS `UserCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserCourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) DEFAULT NULL,
  `courseID` varchar(200) DEFAULT NULL,
  `favorite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCourse`
--

LOCK TABLES `UserCourse` WRITE;
/*!40000 ALTER TABLE `UserCourse` DISABLE KEYS */;
INSERT INTO `UserCourse` VALUES (218,'2869258498@qq.com','c-0010',1),(219,'2869258498@qq.com','c-0001',1),(220,'2869258498@qq.com','c-0002',1),(221,'2869258498@qq.com','c-0013',1),(222,'2869258498@qq.com','c-0006',1),(223,'2869258498@qq.com','c-0007',1),(228,'2869258498@qq.com','c-0005',1),(229,'2869258498@qq.com','c-0003',1),(230,'2869258498@qq.com','c-0009',1),(232,'2869258498@qq.com','c-0008',1),(284,'2869258498@qq.com','c-0004',1),(380,'lisi@qq.com','c-0003',1),(412,'565380490@qq.com','c-0008',1),(413,'565380490@qq.com','c-0002',1),(415,'565380490@qq.com','c-0004',1),(416,'565380490@qq.com','c-0013',1),(417,'565380490@qq.com','c-0006',1),(420,'565380490@qq.com','c-0005',1),(422,'565380490@qq.com','c-0001',1),(423,'565380490@qq.com','c-0009',1),(424,'lisi@qq.com','c-0009',1),(425,'lisi@qq.com','c-0002',1),(426,'lisi@qq.com','c-0006',1),(428,'lisi@qq.com','c-0010',1),(430,'565380490@qq.com','c-0010',1),(439,'2818461312@qq.com','c-0006',1),(442,'lisi@qq.com','c-0013',1);
/*!40000 ALTER TABLE `UserCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserUser`
--

DROP TABLE IF EXISTS `UserUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromEmail` varchar(200) NOT NULL,
  `toEmail` varchar(200) NOT NULL,
  `relationType` int(11) DEFAULT '0',
  `relationStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserUser`
--

LOCK TABLES `UserUser` WRITE;
/*!40000 ALTER TABLE `UserUser` DISABLE KEYS */;
INSERT INTO `UserUser` VALUES (36,'2869258498@qq.com','teacher1@qq.com',1,1),(37,'2869258498@qq.com','2869258498@qq.com',2,1),(39,'2869258498@qq.com','teacher2@qq.com',1,1),(40,'2869258498@qq.com','565380490@qq.com',2,1),(42,'2869258498@qq.com','lisi@qq.com',2,1),(111,'565380490@qq.com','lisi@qq.com',2,1),(121,'565380490@qq.com','teacher2@qq.com',1,1),(129,'565380490@qq.com','2869258498@qq.com',2,1),(138,'lisi@qq.com','565380490@qq.com',2,1),(141,'lisi@qq.com','lisi@qq.com',2,1),(142,'lisi@qq.com','teacher1@qq.com',1,1),(145,'lisi@qq.com','teacher2@qq.com',1,1),(152,'2818461312@qq.com','teacher1@qq.com',1,1),(160,'2818461312@qq.com','565380490@qq.com',2,1),(162,'lisi@qq.com','2869258498@qq.com',2,1),(163,'2818461312@qq.com','teacher2@qq.com',1,1),(164,'2818461312@qq.com','lisi@qq.com',2,1),(166,'565380490@qq.com','565380490@qq.com',2,1);
/*!40000 ALTER TABLE `UserUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(400) DEFAULT '/qingcheng/default/photo',
  `slogan` varchar(500) DEFAULT '自信人生二百年，会当水击三千里。',
  `activeStatus` tinyint(1) DEFAULT '0',
  `activeCode` varchar(265) DEFAULT NULL,
  `gender` tinyint(1) NOT NULL DEFAULT '1',
  `role` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2818461312@qq.com','Administrator','2818461312','/qingcheng/default/photo','自信人生二百年，会当水击三千里。',1,NULL,1,1),(10,'lisi@qq.com','雪夜飞刀','lisi','/qingcheng/default/photo','好好学习，天天向上，不断进步！',1,NULL,1,0),(66,'565380490@qq.com','大漠孤城','565380490','/qingcheng/default/photo','自信人生二百年，会当水击三千里。',1,'',0,0),(67,'2869258498@qq.com','南天柳叶','2869258498','/qingcheng/default/photo','自信人生二百年，会当水击三千里。',1,'',1,0),(70,'teacher1@qq.com','海阔天空','teacher1','/qingcheng/default/photo','自信人生二百年，会当水击三千里。',1,NULL,1,2),(71,'teacher2@qq.com','沉鱼落雁','teacher2','/qingcheng/default/photo','云卷云舒，生活淡如水。花开花落，爱情味若茶。',1,NULL,0,2),(82,'teacher3@qq.com','风流倜傥','teacher3','/qingcheng/default/photo','自信人生二百年，会当水击三千里。',1,NULL,1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-11  8:17:00
