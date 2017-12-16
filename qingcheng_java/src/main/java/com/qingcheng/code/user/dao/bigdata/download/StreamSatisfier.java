package com.qingcheng.code.user.dao.bigdata.download;

import com.qingcheng.code.common.util.basic.number.NumberUtils;

/**
 * Created by liguohua on 16/01/2017.
 */
public class StreamSatisfier {
    private String fileUrL;
    private long fileTatalLength;
    private long fileProgresslLength;
    private double fileProgressPercent;
    private String fileProgressPercentStr;

    public StreamSatisfier() {
    }

    public StreamSatisfier(String fileUrL) {
        this.fileUrL = fileUrL;
    }

    public String getFileUrL() {
        return fileUrL;
    }

    public void setFileUrL(String fileUrL) {
        this.fileUrL = fileUrL;
    }

    public long getFileTatalLength() {
        return fileTatalLength;
    }

    public void setFileTatalLength(long fileTatalLength) {
        this.fileTatalLength = fileTatalLength;
    }

    public long getFileProgresslLength() {
        return fileProgresslLength;
    }

    public void setFileProgresslLength(long fileProgresslLength) {
        //处理字节数
        this.fileProgresslLength = fileProgresslLength;
        //处理百分比
        double p = (fileProgresslLength * 1.0 / this.fileTatalLength) * 100;
        if (p > 100.0) {
            p = 100.0;
        }
        this.fileProgressPercent = p;
        //处理百分比字符串
        this.fileProgressPercentStr = NumberUtils.formatDecimalDouble(this.fileProgressPercent, NumberUtils.df_1);

    }

    public double getFileProgressPercent() {
        return fileProgressPercent;
    }

    public void setFileProgressPercent(double fileProgressPercent) {
        this.fileProgressPercent = fileProgressPercent;
    }

    public String getFileProgressPercentStr() {
        return fileProgressPercentStr;
    }

    public void setFileProgressPercentStr(String fileProgressPercentStr) {
        this.fileProgressPercentStr = fileProgressPercentStr;
    }

    @Override
    public String toString() {
        return "StreamSatisfier{" +
                "fileUrL='" + fileUrL + '\'' +
                ", fileTatalLength=" + fileTatalLength +
                ", fileProgresslLength=" + fileProgresslLength +
                ", fileProgressPercent=" + fileProgressPercent +
                ", fileProgressPercentStr='" + fileProgressPercentStr + '\'' +
                '}';
    }
}
