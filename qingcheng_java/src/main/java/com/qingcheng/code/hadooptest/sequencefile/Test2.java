/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.hadooptest.sequencefile;

/**
 * Created by liguohua on 16/7/28.
 */
public class Test2 {
//    public static void main(String[] args) throws  Exception{
//        System.setProperty("HADOOP_USER_NAME","root");
//        System.setProperty("HADOOP_HOME","");
//
//        Configuration conf = new Configuration();
//        conf.set("hadoop.user.name","root");
//        conf.set("hadoop.home.dir","");
//        conf.set("fs.defaultFS", "hdfs://qingcheng11:9000");
//
//        FileSystem fs = FileSystem.getLocal(conf);
//        Path seqFilePath = new Path("/qingcheng/test/file.seq");
//
//
//        SequenceFile.Writer writer = SequenceFile.createWriter(conf,
//                Writer.file(seqFilePath), Writer.keyClass(Text.class),
//                Writer.valueClass(IntWritable.class));
//
////        writer.append(new Text("key1"), new ByteWritable(1));
////        writer.append(new Text("key2"), new ByteWritable(2));
//
//        writer.close();
//
//        SequenceFile.Reader reader = new SequenceFile.Reader(conf,
//                SequenceFile.Reader.file(seqFilePath));
//
//        Text key = new Text();
//        IntWritable val = new IntWritable();
//
//        while (reader.next(key, val)) {
//            System.err.println(key + "\t" + val);
//        }
//
//        reader.close();
//
//    }
//    public static void main0(String[] args) throws  Exception{
//        System.setProperty("HADOOP_USER_NAME","root");
//        System.setProperty("HADOOP_HOME","");
//
//        Configuration conf = new Configuration();
//        conf.set("hadoop.user.name","root");
//        conf.set("hadoop.home.dir","");
//        conf.set("fs.defaultFS", "hdfs://qingcheng11:9000");
//
//        FileSystem fs = FileSystem.getLocal(conf);
//        Path seqFilePath = new Path("/qingcheng/test/file.seq");
//
//
//        SequenceFile.Writer writer = SequenceFile.createWriter(conf,
//                Writer.file(seqFilePath), Writer.keyClass(Text.class),
//                Writer.valueClass(IntWritable.class));
//
//        writer.append(new Text("key1"), new IntWritable(1));
//        writer.append(new Text("key2"), new IntWritable(2));
//
//        writer.close();
//
//        SequenceFile.Reader reader = new SequenceFile.Reader(conf,
//                SequenceFile.Reader.file(seqFilePath));
//
//        Text key = new Text();
//        IntWritable val = new IntWritable();
//
//        while (reader.next(key, val)) {
//            System.err.println(key + "\t" + val);
//        }
//
//        reader.close();
//
//    }
}
