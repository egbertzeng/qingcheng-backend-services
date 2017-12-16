/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.controller.api.media;


import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.user.service.media.FfmpegService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.qingcheng.code.common.constant.AppRestUrls.*;
import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

/**
 * Created by liguohua on 5/1/16.
 */
@RestController
public class FfmpegController {
    @RequestMapping(value = REST_URL_UTIL_MEDIA_DIR_TO_COURSE, method = RequestMethod.GET)
    public static Object convertDir2CourseNoNeedComposeTsFile(
            @RequestParam(REST_URL_UTIL_COMMON_PARAS_DIR) String dir,
            @RequestParam(value = REST_URL_UTIL_COMMON_PARAS_FILE) String prefixTs,
            @RequestParam(value = "composeTsFile", defaultValue = "false") boolean composeTsFile) {
        System.err.println(dir);
        FfmpegService.convertDir2Course(prefixTs, dir, composeTsFile);
        return composeResponseData("ok");
    }

    @RequestMapping(value = AppRestUrls.REST_URL_UTIL_MEDIA_VIDEO_TO_TS, method = RequestMethod.POST)
    public static void copyMp4ToTs(@RequestParam(REST_URL_UTIL_COMMON_PARAS_FILE) String file) {
        FfmpegService.copyMp4ToTs(file);
    }
}
