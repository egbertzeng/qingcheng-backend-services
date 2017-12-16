package com.qingcheng.code.user.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liguohua on 2017/5/18.
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.qingcheng.code.user.controller.api.*.*.*(..))")
    public void log() {

    }

    @Before(value = "log()")
    public void beforeLog() {
        logger.info("before method invoked!");
    }

    @After(value = "log()")
    public void afterLog() {
        logger.info("after method invoked!");
    }

    @AfterReturning(returning = "obj", value = "log()")
    public void afterReturningLog(Object obj) {
        //输出所有返回值信息
        logger.debug("afterReturning  method invoked!" + obj);
    }

}
