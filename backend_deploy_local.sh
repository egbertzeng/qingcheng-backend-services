#!/bin/bash
#########################################
#author: liguohua
#email : liguohua@cloud-star.com.cn
#date  : 2017.08.22
#########################################

#1.定义相关变量
_private_docker_registry_uri=qingcheng11:5000
_project_name=qingcheng
_module_names=(
                qingcheng_java
              )
###################################################
###################################################
###################################################
newLineSlash="################################################################################################################"
echo $newLineSlash
echo "PRESS(a or A)SWITCH AUTO MODE:automatic execute all commands "
read auto
flag='a'
#2.删除镜像
for ((i=0; i<${#_module_names[@]}; i++)); do
    _module_name=${_module_names[i]}
    _docker_container_name=$_project_name-$_module_name
    if [ "$auto" != "a" -a "$auto" != "A" ] ; then
       echo $newLineSlash
       echo "PRESS(s or S)SKIP: docker rm $_docker_container_name"
       read flag
    fi
    msg="======1.skip: docker rm $_docker_container_name"
    if [ "$flag" = "s" -o "$flag" = "S" ] ; then
       echo $msg
    else
       echo $msg
       docker kill $_docker_container_name
       docker rm  $_docker_container_name
    fi
done

echo $newLineSlash
docker ps

#3.部署镜像
for ((i=0; i<${#_module_names[@]}; i++)); do
    _module_name=${_module_names[i]}
    _docker_image_name=$_project_name/$_module_name
    _docker_container_name=$_project_name-$_module_name
    _docker_image_full_name=$_private_docker_registry_uri/$_docker_image_name
    #4.下载镜像&运行容器
    if [ "$auto" != "a" -a "$auto" != "A" ] ; then
        echo $newLineSlash
        echo "PRESS(s or S)SKIP: docker run $_docker_image_full_name"
        read flag
    fi
    msg="======2.skip: docker run $_docker_image_full_name"
    if [ "$flag" = "s" -o "$flag" = "S" ] ; then
       echo $msg
    else
       echo $msg
       docker pull $_docker_image_full_name
       docker run -it -d --net host --name $_docker_container_name $_docker_image_full_name
    fi
    echo $newLineSlash
    docker ps
done
echo "=======project deploy finished!======="
