/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 5/1/16.
 */
public class VideoFile {
    public String bgColor;
    private String id;
    private String fileName;
    private String fileUrl;
    private String filePoster;
    private boolean playing;

    public VideoFile(String id, String fileName, String fileUrl, String filePoster) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.filePoster = filePoster;
        this.bgColor = "";
    }

    public VideoFile(String fileName, String fileUrl, String filePoster) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.filePoster = filePoster;
    }

    public VideoFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilePoster() {
        return filePoster;
    }

    public void setFilePoster(String filePoster) {
        this.filePoster = filePoster;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return "VideoFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", filePoster='" + filePoster + '\'' +
                '}';
    }
}
