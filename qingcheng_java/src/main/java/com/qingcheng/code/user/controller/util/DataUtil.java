package com.qingcheng.code.user.controller.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguohua on 2017/6/9.
 */
public class DataUtil {
    /**
     * 所有controller返回数据的名称
     */
    private static String RESPONSE_DATA_NAME = "data";

    /**
     * 此方法用于封装controller返回的数据
     *
     * @param object
     * @return
     */
    public static Object composeResponseData(Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_DATA_NAME, object);
        return map;
    }
}
