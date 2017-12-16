FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER liguohua <liguohua@cloud-star.com.cn>

# 拷贝文件到容器
ADD ./target/qingcheng-java-0.0.1-SNAPSHOT.jar app.jar

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
