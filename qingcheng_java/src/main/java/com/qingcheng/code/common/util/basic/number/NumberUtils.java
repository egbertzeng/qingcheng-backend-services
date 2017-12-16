package com.qingcheng.code.common.util.basic.number;

import java.text.DecimalFormat;

/**
 * Created by liguohua on 03/01/2017.
 */
public class NumberUtils {
    public static final DecimalFormat df_1 = new DecimalFormat("#.0");
    public static final DecimalFormat df_2 = new DecimalFormat("#.00");

    public static String formatDecimalDouble(String d, DecimalFormat df) {
        return formatDecimalDouble(Double.parseDouble(d), df);
    }
    public static String formatDecimalDouble(double d, DecimalFormat df) {
        return df.format(d);
    }
}
