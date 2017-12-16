package com.qingcheng.code.user.controller.view;

import com.qingcheng.code.user.controller.api.bigdata.AlluxioHdfsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by liguohua on 15/02/2017.
 */
@Controller
public class Index {
    @Autowired
    private  AlluxioHdfsController alluxioHdfsController;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String,Object> map) {
        map.put("userName","zhangsan");

        Object obj = alluxioHdfsController.makerPlaylistByURL("/qingcheng/video");
        map.put("vedios",obj);
        return "index";
    }
}

