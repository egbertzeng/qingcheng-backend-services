package com.qingcheng.code.common.util.basic.str;

import java.util.UUID;

/**
 * Created by liguohua on 2017/5/7.
 */
public class UuidUtil {
    public static String getUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUuid64() {
        return getUuid32() + getUuid32();
    }

    public static String getUuid96() {
        return getUuid32() + getUuid64();
    }

    public static String getUuid128() {
        return getUuid64() + getUuid64();
    }

    public static String getUuid256() {
        return getUuid128() + getUuid128();
    }
}
