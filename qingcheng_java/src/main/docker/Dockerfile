FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
#当前镜像的维护者和联系方式
MAINTAINER liguohua 2818461312@qq.com
#将打包好的spring程序拷贝到容器中的指定位置
ADD qingcheng-java-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

