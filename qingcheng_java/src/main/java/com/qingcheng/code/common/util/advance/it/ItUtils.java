package com.qingcheng.code.common.util.advance.it;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.util.basic.number.NumberUtils;

/**
 * Created by liguohua on 03/01/2017.
 */
public class ItUtils {
    public static String getInfoCapacityStr(long blocksize) {
        String size = "0.0";
        int i = 1024;
        long b = 1;
        long B = i * b;
        long M = i * B;
        long G = i * M;
        long T = i * G;
        long P = i * T;
        long E = i * P;
        long Z = i * E;
        if (blocksize == 0) {
            //do nothing
        } else if (blocksize > 0 && blocksize < B) {
            size = NumberUtils.formatDecimalDouble((blocksize / b) + AppConstants.DOTTO + (blocksize % b), NumberUtils.df_2) + AppConstants.INFO_BIT;
        } else if (blocksize > B && blocksize < M) {
            size = NumberUtils.formatDecimalDouble((blocksize / B) + AppConstants.DOTTO + (blocksize % B), NumberUtils.df_2) + AppConstants.INFO_KB;
        } else if (blocksize > M && blocksize < G) {
            size = NumberUtils.formatDecimalDouble((blocksize / M) + AppConstants.DOTTO + (blocksize % M), NumberUtils.df_2) + AppConstants.INFO_MB;
        } else if (blocksize > G && blocksize < T) {
            size = NumberUtils.formatDecimalDouble((blocksize / G) + AppConstants.DOTTO + (blocksize % G), NumberUtils.df_2) + AppConstants.INFO_GB;
        } else if (blocksize > T && blocksize < P) {
            size = NumberUtils.formatDecimalDouble((blocksize / T) + AppConstants.DOTTO + (blocksize % T), NumberUtils.df_2) + AppConstants.INFO_TB;
        } else if (blocksize > P && blocksize < E) {
            size = NumberUtils.formatDecimalDouble((blocksize / P) + AppConstants.DOTTO + (blocksize % P), NumberUtils.df_2) + AppConstants.INFO_PB;
        } else if (blocksize > E && blocksize < Z) {
            size = NumberUtils.formatDecimalDouble((blocksize / E) + AppConstants.DOTTO + (blocksize % E), NumberUtils.df_2) + AppConstants.INFO_EB;
        } else if (blocksize > Z) {
            size = NumberUtils.formatDecimalDouble((blocksize / Z) + AppConstants.DOTTO + (blocksize % Z), NumberUtils.df_2) + AppConstants.INFO_ZB;
        } else {
            throw new RuntimeException("System error! File Size Is Too Larger!");
        }
        return size;
    }


}
