//package com.qingcheng.code.common.example;
//
//import io.swagger.annotations.*;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user")
//@Api("userController相关api")
//public class UserController {
//    @ApiOperation("获取用户信息")
//    @ApiImplicitParams({
//        @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名",defaultValue="zhaojigang"),
//        @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="wangna")
//    })
//    @ApiResponses({
//        @ApiResponse(code=400,message="请求参数没填好"),
//        @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//    })
//    @RequestMapping(value="/get/test",method=RequestMethod.GET)
//    public String test(){
//        return null;
//    }
//
//
//}