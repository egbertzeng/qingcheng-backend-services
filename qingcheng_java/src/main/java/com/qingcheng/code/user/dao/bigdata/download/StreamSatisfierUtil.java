package com.qingcheng.code.user.dao.bigdata.download;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguohua on 16/01/2017.
 */
public class StreamSatisfierUtil {
    public static final Map<String, StreamSatisfier> map = new HashMap<>();

    public static boolean isUrlInProcessQueue(String url) {
        return map.containsKey(url);
    }

    public static StreamSatisfier getStreamSatisfierByUrl(String uri) {
        if (!isUrlInProcessQueue(uri)) {
            map.put(uri, new StreamSatisfier(uri));
        }
        return map.get(uri);
    }

    public static long getProcessLength(String uri) {
        if (isUrlInProcessQueue(uri)) {
            return getStreamSatisfierByUrl(uri).getFileProgresslLength();
        }
        return 0;
    }

    public static String getProgressPercentStr(String uri) {
        if (isUrlInProcessQueue(uri)) {
            return getStreamSatisfierByUrl(uri).getFileProgressPercentStr();
        }
        return "0.00";
    }

    public static StreamSatisfier removeStreamSatisfierByUrl(String uri) {
        return map.remove(uri);
    }


}
