#一、数据库准备工作
[0：备份数据库的mysql脚本](qingchengkeji.sql)


##
```
1.导出数据库（sql脚本）  
mysqldump -u 用户名 -p 数据库名 > 导出的文件名
mysqldump -u root -p qingchengkeji > qingchengkeji.sql
```

##0.登陆MySQL
```
1.登陆命令
mysql -u root -p

2.输入密码
qingcheng

3.授权用户登陆权限
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'qingcheng' WITH GRANT OPTION;  
GRANT ALL PRIVILEGES ON *.* TO 'root'@'qingcheng11' IDENTIFIED BY 'qingcheng' WITH GRANT OPTION;  
FLUSH PRIVILEGES; 
```
##1.查看数据库字符集
```
show variables like "%character%";
```
##2.设置数据库字符集
```
set character_set_client=utf8; 
set character_set_results=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_server=utf8;
set character_set_filesystem=utf8;
```
##3.设置数据库字符集
```
1.编辑文件
vim /etc/my.cnf

2.添加内容如下
[client]
default-character-set=utf8
 
[mysql]
default-character-set=utf8
 
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
```
##重启mysql服务
```
service mysqld stop
service mysqld start
```


##设置某个字段的字符集

###查看表的字符集
show full columns from CourseComment;
###更改某一列的字符集
alter table CourseComment modify column commentContent varchar(500) character set utf8 not null;



##二、创建数据库，数据表的工作

##1.创建数据库
```
CREATE DATABASE qingchengkeji;
USE qingchengkeji;
```
##2.创建课程表
```
CREATE TABLE Course
(
    courseID VARCHAR(200) PRIMARY KEY NOT NULL,
    courseName VARCHAR(200),
    courseSubName VARCHAR(1200),
    courseUrl VARCHAR(1200),
    courseDescription VARCHAR(2000),
    coursePoster VARCHAR(200) DEFAULT '/poster.png',
    courseOnLine TINYINT(1) DEFAULT '1',
    coursePlayTimes INT(11) DEFAULT '1000',
    courseIsRecommended TINYINT(1) DEFAULT '0',
    courseIsRecommendedForLoop TINYINT(1) DEFAULT '0',
    courseRecommendStart INT(11) DEFAULT '0',
    categoryId VARCHAR(40),
    CONSTRAINT Course_Category_categoryId_fk FOREIGN KEY (categoryId) REFERENCES Category (categoryId) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX Course_Category_categoryId_fk ON Course (categoryId);
```

##5.插入数据
```
insert into
Course(courseID,courseName,courseSubName,courseUrl,courseDescription)
values
("c-0001","武神赵子龙电视剧","2015年最火的大数据技术--spark", "/qingcheng/video", "spark实战教程是张东老师,努力打造的精品课程!"),
("c-0002","冰河世纪电视剧","2015年最火的大数据技术--hadoop", "/qingcheng/video2", "hadoop实战教程是刘东老师,努力打造的精品课程!");

```
##6.查询数据
```
SELECT * FROM Course;
```





##类别表

###创建类别表
```
CREATE TABLE Category
(
    categoryId VARCHAR(40) NOT NULL,
    categoryName VARCHAR(40),
    categoryNameCn VARCHAR(40),
    categoryIconName VARCHAR(40),
    categoryIsOnLine TINYINT(1) DEFAULT '0'
);
CREATE UNIQUE INDEX Category_categoryId_uindex ON Category (categoryId);
CREATE UNIQUE INDEX Category_categoryNameCn_uindex ON Category (categoryNameCn);
CREATE UNIQUE INDEX Category_categoryName_uindex ON Category (categoryName);
```







