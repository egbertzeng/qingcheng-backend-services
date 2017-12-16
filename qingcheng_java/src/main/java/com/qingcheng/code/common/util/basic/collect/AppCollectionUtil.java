/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.basic.collect;

import java.util.Collection;

/**
 * Created by liguohua on 16/9/11.
 */
public class AppCollectionUtil {
    public static boolean isEmpty(Collection<String> collection) {
        if (collection == null || collection.size() == 0) {
            //is empty
            return true;
        }
        // is not empty
        return false;
    }
}
