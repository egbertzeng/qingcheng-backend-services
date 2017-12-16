/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.system.bigdata.zookeeper.service.ZooKeeperService;
import com.qingcheng.code.system.java.JavaApplicationService;
import com.qingcheng.code.system.service.AllServiceManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by liguohua on 4/20/16.
 */
@ServletComponentScan
@EnableScheduling
@MapperScan("com.qingcheng.code.user.dao.mysql")
@SpringBootApplication
@EnableSwagger2
public class App {
    private final static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        //1.check before java application
//        checkBeforJavaApplicationBoot();
        //2.启动java程序
        logger.info("正在启动Java程序......");
        SpringApplication.run(App.class, args);
        //3.check after java application
        //checkAfterJavaApplicationBoot();

    }

    private static void checkBeforJavaApplicationBoot() {
        //1.zookeeper集群初始化
        logger.info("正在尝试启动zookeeper集群......");
        ZooKeeperService.strartZookeeperCluster();
        //2.将本地配置上传到zookeeper
        logger.info("正在尝试初始化zookeeper集群......");
        AppDynamic.configPropertiesInitToZookeeper();
        //2.Java程序初始化
        logger.info("正在尝试初始化Java程序......");
        JavaApplicationService.javaApplicationInit();
        //3.大数据集群初始化
        logger.info("正在尝试初始化大数据集群......");
        AllServiceManager.preStartJavaApplicationDetectBigdataApplication();
    }

    private static void checkAfterJavaApplicationBoot() {
        //5.启动Java轮询大数据
        logger.info("正在尝试轮询大数据集群......");
        AllServiceManager.posStartJavaApplicationDetectBigdataApplication();
        //6.启动Java轮询JAVA集群
        logger.info("正在尝试轮询Java集群.....");
        AllServiceManager.posStartJavaApplicationDetectJavaClusters();
    }

}


