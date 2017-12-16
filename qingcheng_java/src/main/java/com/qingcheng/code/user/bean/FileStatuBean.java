package com.qingcheng.code.user.bean;

import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.common.util.advance.filetype.FileTypeUtils;
import com.qingcheng.code.common.util.advance.it.ItUtils;
import com.qingcheng.code.common.util.advance.sematicui.SematicUtils;

public class FileStatuBean {
    private String fullPath;
    private String path;
    private String length;
    private long lengthL;
    private boolean thisIsdir;
    private short block_replication;
    private String blocksize;
    private long blocksizeL;
    private String modification_time;
    private String access_time;
    private String permission;
    private String owner;
    private String group;
    private String icon;
    //能不能压缩
    private boolean canCompress = true;
    //能不能解压
    private boolean canDeCompress = false;

    //已经处理的长度
    private long processLengthL;

    // 已经处理的百分比
    private String progressPercentStr;
    private String fileType;
    //删除后，页面不再显示
    private boolean thisIscanDiplay;
    //剪切后，改项不能访问
    private boolean thisIslocked;

    private boolean thisIsFavorted;

    public FileStatuBean(String fullPath, String path, long length, boolean isdir, short block_replication, long blocksize, String modification_time, String access_time, String permission, String owner, String group, boolean thisIscanDiplay, boolean thisIsFavorted, long processLengthL, String progressPercentStr) {
        this.fullPath = fullPath;
        this.path = path;
        this.thisIsdir = isdir;
        this.block_replication = block_replication;
        this.modification_time = modification_time;
        this.access_time = access_time;
        this.permission = permission;
        this.owner = owner;
        this.group = group;
        this.thisIscanDiplay = thisIscanDiplay;
        this.thisIslocked = false;
        this.thisIsFavorted = thisIsFavorted;
        //文件已经处理的长度
        this.processLengthL = processLengthL;
        // 已经处理的百分比
        this.progressPercentStr = progressPercentStr;
        //文件大小
        this.lengthL = length;
        this.length = getFileSize(length);
        //文件块大小
        blocksizeL = blocksize;
        this.blocksize = getFileSize(blocksize);
        //文件类型
        if (this.thisIsdir) {
            this.fileType = FileType.FILE_TYPE_DIR;
        } else {
            this.fileType = FileTypeUtils.getRequestFileType(path);
        }
        //文件的icon和能否压缩解压
        if (this.thisIsdir) {
            this.icon = SematicUtils.SMATICUI_folder;
            //文件夹不能压缩，不能解压
            canCompress = false;
            canDeCompress = false;
        } else {
            if (path.endsWith(FileType.FILE_TYPE_MP4)) {
                //mp4
                this.icon = SematicUtils.SMATICUI_video_play_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_AVI)) {
                //avi
                this.icon = SematicUtils.SMATICUI_file_video_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_MP3)) {
                //mp3
                this.icon = SematicUtils.SMATICUI_music;
            } else if (path.endsWith(FileType.FILE_TYPE_PNG)) {
                //png
                this.icon = SematicUtils.SMATICUI_file_image_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_PDF)) {
                //PDF
                this.icon = SematicUtils.SMATICUI_file_pdf_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_DOC) || path.endsWith(FileType.FILE_TYPE_DOCX)) {
                //word
                this.icon = SematicUtils.SMATICUI_file_word_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_PPT) || path.endsWith(FileType.FILE_TYPE_PPTX)) {
                //ppt
                this.icon = SematicUtils.SMATICUI_file_powerpoin_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_EXCEL) || path.endsWith(FileType.FILE_TYPE_EXCELX)) {
                //excel
                this.icon = SematicUtils.SMATICUI_file_excel_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_HTML)) {
                //html
                this.icon = SematicUtils.SMATICUI_internet_explorer;
            } else if (path.endsWith(FileType.FILE_TYPE_AVRO) || path.endsWith(FileType.FILE_TYPE_TXT) || path.endsWith(FileType.FILE_TYPE_JSON) || path.endsWith(FileType.FILE_TYPE_CSV)) {
                //txt,json,csv,avro
                this.icon = SematicUtils.SMATICUI_file_text_outline;
            } else if (path.endsWith(FileType.FILE_TYPE_DEFLATE) || path.endsWith(FileType.FILE_TYPE_DEFAULT) || path.endsWith(FileType.FILE_TYPE_BZ2) || path.endsWith(FileType.FILE_TYPE_GZ) || path.endsWith(FileType.FILE_TYPE_TAR) || path.endsWith(FileType.FILE_TYPE_ZIP) || path.endsWith(FileType.FILE_TYPE_RAR) || path.endsWith(FileType.FILE_TYPE_TGZ) || path.endsWith(FileType.FILE_TYPE_SEQ)) {
                //tar,zip,rar,tgz||seq,bz2,gz,deflate,default
                this.icon = SematicUtils.SMATICUI_file_archive_outline;
                //不能压缩
                this.canCompress = false;
                //可以解压
                this.canDeCompress = true;
            } else {
                //other
                this.icon = SematicUtils.SMATICUI_file_outline;
            }
        }
    }

    private String getFileSize(long blocksize) {
        String size = "0";
        if (!this.thisIsdir) {
            size = ItUtils.getInfoCapacityStr(blocksize);
        }
        return size;
    }

    public String getProgressPercentStr() {
        return progressPercentStr;
    }

    public void setProgressPercentStr(String progressPercentStr) {
        this.progressPercentStr = progressPercentStr;
    }

    public long getProcessLengthL() {
        return processLengthL;
    }

    public void setProcessLengthL(long processLengthL) {
        this.processLengthL = processLengthL;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isThisIsdir() {
        return thisIsdir;
    }

    public void setThisIsdir(boolean thisIsdir) {
        this.thisIsdir = thisIsdir;
    }

    public short getBlock_replication() {
        return block_replication;
    }

    public void setBlock_replication(short block_replication) {
        this.block_replication = block_replication;
    }

    public String getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(String blocksize) {
        this.blocksize = blocksize;
    }

    public String getModification_time() {
        return modification_time;
    }

    public void setModification_time(String modification_time) {
        this.modification_time = modification_time;
    }

    public String getAccess_time() {
        return access_time;
    }

    public void setAccess_time(String access_time) {
        this.access_time = access_time;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getLengthL() {
        return lengthL;
    }

    public void setLengthL(long lengthL) {
        this.lengthL = lengthL;
    }

    public long getBlocksizeL() {
        return blocksizeL;
    }

    public void setBlocksizeL(long blocksizeL) {
        this.blocksizeL = blocksizeL;
    }

    public boolean isThisIscanDiplay() {
        return thisIscanDiplay;
    }

    public void setThisIscanDiplay(boolean thisIscanDiplay) {
        this.thisIscanDiplay = thisIscanDiplay;
    }

    public boolean isThisIslocked() {
        return thisIslocked;
    }

    public void setThisIslocked(boolean thisIslocked) {
        this.thisIslocked = thisIslocked;
    }

    public boolean isThisIsFavorted() {
        return thisIsFavorted;
    }

    public void setThisIsFavorted(boolean thisIsFavorted) {
        this.thisIsFavorted = thisIsFavorted;
    }

    public boolean isCanCompress() {
        return canCompress;
    }

    public void setCanCompress(boolean canCompress) {
        this.canCompress = canCompress;
    }

    public boolean isCanDeCompress() {
        return canDeCompress;
    }

    public void setCanDeCompress(boolean canDeCompress) {
        this.canDeCompress = canDeCompress;
    }
}
