package com.qingcheng.code.user.bean.localfile;

/**
 * Created by liguohua on 2017/5/10.
 */
public class LocalFileInfo {
    private String abpath;
    private String mdate;
    private String type;
    private long size;

    public LocalFileInfo(String abpath, String mdate, String type, long size) {
        this.abpath = abpath;
        this.mdate = mdate;
        this.type = type;
        this.size = size;
    }

    public String getAbpath() {
        return abpath;
    }

    public void setAbpath(String abpath) {
        this.abpath = abpath;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LocalFileInfo{" +
                "abpath='" + abpath + '\'' +
                ", mdate='" + mdate + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
