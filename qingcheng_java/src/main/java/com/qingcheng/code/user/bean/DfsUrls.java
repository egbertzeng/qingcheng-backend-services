package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 05/01/2017.
 */
public class DfsUrls {
    private String dfsUrl;
    private boolean trashTask;
    private boolean markTask;
    private boolean downloadTask;
    private boolean copyTask;
    private boolean moveTask;


    public String getDfsUrl() {
        return dfsUrl;
    }

    public void setDfsUrl(String dfsUrl) {
        this.dfsUrl = dfsUrl;
    }

    public boolean isTrashTask() {
        return trashTask;
    }

    public void setTrashTask(boolean trashTask) {
        this.trashTask = trashTask;
    }

    public boolean isMarkTask() {
        return markTask;
    }

    public void setMarkTask(boolean markTask) {
        this.markTask = markTask;
    }

    public boolean isDownloadTask() {
        return downloadTask;
    }

    public void setDownloadTask(boolean downloadTask) {
        this.downloadTask = downloadTask;
    }

    public boolean isCopyTask() {
        return copyTask;
    }

    public void setCopyTask(boolean copyTask) {
        this.copyTask = copyTask;
    }

    public boolean isMoveTask() {
        return moveTask;
    }

    public void setMoveTask(boolean moveTask) {
        this.moveTask = moveTask;
    }
    @Override
    public String toString() {
        return "DfsUrls{" +
                "dfsUrl='" + dfsUrl + '\'' +
                ", trashTask=" + trashTask +
                ", markTask=" + markTask +
                ", downloadTask=" + downloadTask +
                ", copyTask=" + copyTask +
                ", moveTask=" + moveTask +
                '}';
    }
}
