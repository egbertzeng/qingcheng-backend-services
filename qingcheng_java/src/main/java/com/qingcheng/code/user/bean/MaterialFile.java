package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 2017/5/29.
 */
public class MaterialFile {
    private String fileName;
    private String filePath;
    //用于控制是否要立即显示
    private boolean shouldDisplay;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isShouldDisplay() {

        return shouldDisplay;
    }

    public void setShouldDisplay(boolean shouldDisplay) {
        this.shouldDisplay = shouldDisplay;
    }

    @Override
    public String toString() {
        return "MaterialFile{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", shouldDisplay=" + shouldDisplay +
                '}';
    }
}
