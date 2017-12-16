package com.qingcheng.code.common.util.advance.filetype;

import com.qingcheng.code.common.constant.AppConstants;

/**
 * Created by liguohua on 26/12/2016.
 */
public class FileTypeUtils {


    /**
     * 根据请求的文件类型，确定数据流类型
     * 比如：
     * docx-->doc
     * doc-->doc
     *
     * @param requestFileType 请求文件类型：docx
     * @return 数据流类型：doc
     */
    public static String getInputDocumentType(String requestFileType) {
        if (requestFileType.endsWith("x")) {
            requestFileType = requestFileType.substring(0, requestFileType.length() - 1);
        }
        return requestFileType;
    }

    /**
     * 此方法用于判断是是office类型，用于进一步确定是否有必要使用openoffice转换数据流
     *
     * @param requestFileType 请求文件类型：
     * @return 是否是office类型的文件
     */
    public static boolean isNeedConvert2PdfStream(String requestFileType) {
        return FileType.NEED_CONVERT_2_PDF_STREAM_TYPES.contains(requestFileType);
    }

    /**
     * 根据请求的文件类信息，确定返回类型信息
     *
     * @param fileType 请求文件路径
     * @return 返回类型信息
     */
    public static String getContentTypeByFileType(String fileType) {
        String contentType = FileType.CONTENT_TYPE_TXT;
        if (fileType.contains(AppConstants.DOTTO)) {
            if (fileType.equalsIgnoreCase(FileType.FILE_TYPE_PDF)) {
                contentType = FileType.CONTENT_TYPE_PDF;
            } else if (fileType.equalsIgnoreCase(FileType.FILE_TYPE_MP4)) {
                contentType = FileType.CONTENT_TYPE_MP4;
            }
        }
        return contentType;
    }

    /**
     * 此方法用于，根据请求路径获取所请求的文件类型
     *
     * @param filePath
     * @return
     */
    public static String getRequestFileType(String filePath) {
        String type="file";
        if (filePath.contains(AppConstants.DOTTO)) {
            type=filePath.substring(filePath.lastIndexOf(AppConstants.DOTTO) + 1, filePath.length()).trim();
        }
        return type;
    }
}
