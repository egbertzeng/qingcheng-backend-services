package com.qingcheng.code.user.controller.api.localfile;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.localfile.LocalFileInfo;
import com.qingcheng.code.user.service.localfile.LocalFileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

/**
 * Created by liguohua on 2017/5/10.
 */
@RestController
public class LocalFileController {
    /**
     * 获取输入路径下的所有文件
     *
     * @param dir
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_LOCALFILE_GET_FILELIST, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public static Object getAllFilesByDirPath(@RequestParam(value = AppRestUrls.REST_URL_UTIL_COMMON_PARAS_DIR) String dir) {
        List<LocalFileInfo> fileList = new LinkedList<>();
        LocalFileService.getAllFilesByDirPath(dir, fileList);
        return composeResponseData(fileList);
    }

    /**
     * 判断ts文件是否存在
     *
     * @param path ts文件路径
     * @return 是否存在
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_LOCALFILE_CHECK_TSFILE_ISEXIST, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public static Object fileIsExist(@RequestParam(value = AppRestUrls.REST_URL_UTIL_COMMON_PARAS_FILE) String path) {
        Map<String, Boolean> map = new HashMap<>();
        //后缀名不正确
        if (!path.trim().endsWith("ts")) {
            return composeResponseData(false);
        }
        //文件是否存在
        return composeResponseData(LocalFileService.fileIsExist(path));
    }

}
