#一、概述
```
1.采用开启单独的openoffice服务器来转换字节流，将office字节流转成为pdf字节流
2.前端统一使用pdf.s进行展示
```
##1.openoffice服务器配置
```
1.安装软件
yum install libreoffice-headless
2.启动服务
/usr/bin/soffice --headless --accept="socket,host=qingcheng11,port=8100;urp;" --nofirststartwizard &
```
##2.此方案的缺点
```
执行速度非常慢！
```
