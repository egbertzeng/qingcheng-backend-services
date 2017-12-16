/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

/*
 */

package com.qingcheng.code.hadooptest.sequencefile;

/**
 * Created by liguohua on 16/7/28.
 */
public class Test {
//    private static final String[] DATA = { "One, two, buckle my shoe",
//            "Three, four, shut the door", "Five, six, pick up sticks",
//            "Seven, eight, lay them straight", "Nine, ten, a big fat hen" };
//    public static void main(String[] args) throws  Exception{
//        String  path="/qingcheng/test";
//       Object o= new HDFSFileSystemRestDao().makerPlaylistByURL(path);
//        System.out.println(o);
//        System.setProperty("HADOOP_USER_NAME","root");
//        System.setProperty("HADOOP_HOME","/");
//        Configuration conf = new Configuration();
//        conf.set("hadoop.job.user", "root");
//        Path seqFilePath = new Path("/qingcheng/test2/file.seq");
////        // Writer内部类用于文件的写操作,假设Key和Value都为Text类型
////        SequenceFile.Writer writer = SequenceFile.createWriter(conf,
////                Writer.file(seqFile), Writer.keyClass(Text.class),
////                Writer.valueClass(Text.class),
////                Writer.compression(SequenceFile.CompressionType.NONE));
////
////        // 通过writer向文档中写入记录
////        writer.append(new Text("key1"), new Text("value1"));
////        writer.append(new Text("key2"), new Text("value3"));
////        writer.append(new Text("key3"), new Text("value3"));
////        IOUtils.closeStream(writer);// 关闭write流
//        // 通过reader从文档中读取记录
//        SequenceFile.Reader reader = new SequenceFile.Reader(conf,
//                SequenceFile.Reader.file(seqFilePath));
//        Text key = new Text();
//        Text value = new Text();
//        while (reader.next(key, value)) {
//            System.out.println(key);
//            System.out.println(value);
//        }
//        IOUtils.closeStream(reader);// 关闭read流
//    }
}
