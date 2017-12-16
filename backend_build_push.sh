#!/bin/bash
#########################################
#author: liguohua
#email : liguohua@cloud-star.com.cn
#date  : 2017.08.22
#########################################

#一、定义相关变量
#1.需要改动的变量
_private_docker_registry_uri=qingcheng11:5000
_project_name=qingcheng
_module_names=(
                qingcheng_java
               )
###################################################
###################################################
###################################################
#2.无需改动的变量
_pwd=$PWD
_project_root_path=$_pwd
_docker_compose_root_path=$_pwd/docker

#二、进行项目maven编译
newLineSlash="################################################################################################################"
echo $newLineSlash
echo "PRESS(a or A)SWITCH AUTO MODE:automatic execute all commands "
read auto
flag='a'
if [ "$auto" != "a" -a "$auto" != "A" ] ; then
    echo $newLineSlash
    echo "PRESS(s or S)SKIP: mvn package $_project_name"
    read flag
fi
msg="======1.skip: mvn package $_project_name"
if [ "$flag" = "s" -o "$flag" = "S" ] ; then
   echo $msg
else
   echo $msg
   mvn clean;
   mvn package -Dmaven.test.skip=true;
fi

#三、进行项目docker化
for _module_name in ${_module_names[@]};do
    _module_root_path=$_pwd/$_module_name
    _docker_image_name=$_project_name/$_module_name
    _docker_image_full_name=$_private_docker_registry_uri/$_docker_image_name
    #1.打包docker镜像
    if [ "$auto" != "a" -a "$auto" != "A" ] ; then
        echo $newLineSlash
        echo "PRESS(s or S)SKIP: docker build $_docker_image_full_name"
        read flag
    fi
    msg="======2.skip: docker build $_docker_image_full_name"
    if [ "$flag" = "s" -o "$flag" = "S" ] ; then
        echo $msg
    else
        echo $msg
        cd $_module_root_path
        docker build -t $_docker_image_full_name .
    fi
    #2.上传docker镜像
    if [ "$auto" != "a" -a "$auto" != "A" ] ; then
        echo $newLineSlash
        echo "PRESS(s or S)SKIP: docker push $_docker_image_full_name"
        read flag
    fi
    msg="======3.skip: docker push $_docker_image_full_name"
    if [ "$flag" = "s" -o "$flag" = "S" ] ; then
        echo $msg
    else
        echo $msg
        docker push $_docker_image_full_name
    fi
    echo $newLineSlash
    echo "# $_docker_image_full_name end"
done
echo "=======project push finished!======="