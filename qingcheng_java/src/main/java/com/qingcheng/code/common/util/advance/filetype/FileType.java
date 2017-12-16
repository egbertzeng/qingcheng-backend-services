package com.qingcheng.code.common.util.advance.filetype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguohua on 27/12/2016.
 */
public class FileType {

    //0.目录
    public static final String FILE_TYPE_DIR = "dir";
    //Hadoop压缩格式
    public static final String FILE_TYPE_SEQ = "seq";
    public static final String FILE_TYPE_GZ = "gz";
    public static final String FILE_TYPE_BZ2 = "bz2";
    public static final String FILE_TYPE_DEFLATE = "deflate";
    public static final String FILE_TYPE_DEFAULT = "default";

    //普通压缩格式
    public static final String FILE_TYPE_ZIP = "zip";
    public static final String FILE_TYPE_RAR = "rar";
    public static final String FILE_TYPE_TAR = "tar";
    public static final String FILE_TYPE_TGZ = "tgz";

    //1.常用文件类型
    //1.不需要转换的非office类型
    public static final String FILE_TYPE_PDF = "pdf";
    public static final String FILE_TYPE_MP4 = "mp4";
    public static final String FILE_TYPE_AVI = "avi";
    public static final String FILE_TYPE_MP3 = "mp3";
    public static final String FILE_TYPE_PNG = "png";
    public static final String FILE_TYPE_TXT = "txt";
    public static final String FILE_TYPE_CSV = "csv";
    public static final String FILE_TYPE_AVRO = "avro";
    public static final String FILE_TYPE_JSON = "json";
    public static final String FILE_TYPE_HTML = "html";
    //1.2需要转换的office类型
    public static final String FILE_TYPE_DOC = "doc";
    public static final String FILE_TYPE_DOCX = "docx";
    public static final String FILE_TYPE_PPT = "ppt";
    public static final String FILE_TYPE_PPTX = "pptx";
    public static final String FILE_TYPE_EXCEL = "xls";
    public static final String FILE_TYPE_EXCELX = "xlsx";

    //1.3分类类型
    //需要转换为pdf数据流的类型们
    public static final List<String> NEED_CONVERT_2_PDF_STREAM_TYPES = new ArrayList<>();
    //4.contentType类型
    public static final String CONTENT_TYPE_PDF = "application/pdf";
    public static final String CONTENT_TYPE_MP4 = "video/mpeg4";
    //public static final String CONTENT_TYPE_MP4 = "video/mp4";
    //public static final String CONTENT_TYPE_MP4 = "application/octet-stream";
    public static final String CONTENT_TYPE_TXT = "text/plain";
    public static final String CONTENT_TYPE_OCTET = "application/octet-stream";
    public static final String CONTENT_TYPE_JSON = "application/json";

    static {
        NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_DOC);
        NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_DOCX);
        NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_PPT);
        NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_PPTX);
        //NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_TXT);
        //NEED_CONVERT_2_PDF_STREAM_TYPES.add(FILE_TYPE_CSV);
    }
}
