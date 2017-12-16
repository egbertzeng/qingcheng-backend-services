package com.qingcheng.code.common.util.basic.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liguohua on 03/01/2017.
 */
public class AppTimeUtils {
    public static SimpleDateFormat simpleDateFormat_1 = new SimpleDateFormat("yyyy-MM-dd [EEEE] HH:mm:ss");

    public static String getTimeString(long t, SimpleDateFormat sdf) {
        return sdf.format(new Date(t));
    }
}
