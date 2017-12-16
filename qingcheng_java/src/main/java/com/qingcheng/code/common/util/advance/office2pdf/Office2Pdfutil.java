package com.qingcheng.code.common.util.advance.office2pdf;


import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.qingcheng.code.common.constant.TemplateConstaints;
import org.apache.hadoop.fs.FSDataInputStream;

import java.io.OutputStream;
import java.net.ConnectException;

public class Office2Pdfutil {

    private static final OpenOfficeConnection connection;

    //1.连接openoffice
    static {
        connection = new SocketOpenOfficeConnection(TemplateConstaints.OPENOFFICE_SERVER_HOST, TemplateConstaints.OPENOFFICE_SERVER_PORT);
        try {
            connection.connect();
        } catch (ConnectException e) {
            throw new RuntimeException("can not connet the openoffice server! please make sure openoffice server is running! ",e);
        }
    }

    //2.对office格式的数据流进行转换，转换为PDF格式的数据流
    public static void officeStream2PdfStream(OutputStream outputStream, FSDataInputStream inputStream, String inputFormate) throws ConnectException {
        //2.1定义格式
        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
        DocumentFormat doc = formatReg.getFormatByFileExtension(inputFormate);
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf");
        //2.2执行转换操作
        StreamOpenOfficeDocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(inputStream, doc, outputStream, pdf);
    }
}
