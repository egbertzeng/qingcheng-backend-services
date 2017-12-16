package com.qingcheng.code.user.service.localfile;

import com.qingcheng.code.common.util.basic.time.AppTimeUtils;
import com.qingcheng.code.user.bean.localfile.LocalFileInfo;

import java.io.File;
import java.util.List;

/**
 * Created by liguohua on 2017/5/10.
 */
public class LocalFileService {
    public static boolean fileIsExist(String path){

        File file=new File(path.trim());
        return  file.exists();
    }
    public static void getAllFilesByDirPath(String dir, List<LocalFileInfo> fileList) {
        File file = new File(dir);
        if (file.isFile()) {
            throw new RuntimeException("你输入的不是目录");
        }
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isFile()) {
                fileList.add(new LocalFileInfo(f.getAbsolutePath().toString(), AppTimeUtils.getTimeString(f.lastModified(), AppTimeUtils.simpleDateFormat_1) , "file", f.length()));
            } else {
                getAllFilesByDirPath(f.getAbsolutePath(), fileList);
            }
        }
    }
}
