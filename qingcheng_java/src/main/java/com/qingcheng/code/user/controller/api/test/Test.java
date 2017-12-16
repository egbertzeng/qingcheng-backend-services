//package com.qingcheng.code.user.controller.test;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by liguohua on 08/02/2017.
// */
//@RestController
//public class Test {
//    private static String responseName = "data";
//
//    @RequestMapping(value = "/test/get1", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public Map<String, String> testGet() {
//        Map<String, String> map = new HashMap<>();
//        String res = "get!这是来自spring后台的数据！";
//        map.put(responseName, res);
//        return map;
//    }
//
//    @RequestMapping(value = "/test/get2", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public Map<String, String> testGet2(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
//        Map<String, String> map = new HashMap<>();
//        String res = "get!这是来自spring后台的数据！" + name + "," + age;
//        map.put(responseName, res);
//        return map;
//    }
//
//    @RequestMapping(value = "/test/post1", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public Map<String, String> testPost1() {
//        Map<String, String> map = new HashMap<>();
//        String res = "post!这是来自spring后台的数据！";
//        map.put(responseName, res);
//        return map;
//    }
//
//    @RequestMapping(value = "/test/post2", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public Map<String, String> testPost2(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
//        Map<String, String> map = new HashMap<>();
//        String res = "post!这是来自spring后台的数据！" + name + "," + age;
//        map.put(responseName, res);
//        return map;
//    }
//}
