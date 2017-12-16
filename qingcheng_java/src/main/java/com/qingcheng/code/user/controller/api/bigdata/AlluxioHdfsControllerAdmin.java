package com.qingcheng.code.user.controller.api.bigdata;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.user.bean.MoveBean;
import com.qingcheng.code.user.service.bigdata.AlluxioHdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liguohua on 2017/5/13.
 */
@RestController
@Transactional
public class AlluxioHdfsControllerAdmin {
    @Autowired
    private AlluxioHdfsService alluxioHdfsService;

    /**
     * 此方法用于移动文件(真移动)
     */
    @RequestMapping(value = AppRestUrls.REST_URL_COURSE_ADMIN_DFS_RENAME_REST_URL, method = RequestMethod.POST)
    public boolean moveToDfs(@RequestBody MoveBean moveBean) {
        //1.获取参数
        String from = moveBean.getFrom().trim();
        String to = moveBean.getTo().trim();
        if (("".equals(to)) || from.equals(to)) {
            return false;
        }
        if (to.endsWith(AppConstants.SLASH)) {
            to = to.substring(0, to.length() - 2);
        }
        //2.创建上级目的路径的父目录
        String toParent = to.substring(0, to.lastIndexOf(AppConstants.SLASH));
        alluxioHdfsService.mkdirs(toParent);
        //3.移动文件
        return alluxioHdfsService.rename(from, to);
    }
}
