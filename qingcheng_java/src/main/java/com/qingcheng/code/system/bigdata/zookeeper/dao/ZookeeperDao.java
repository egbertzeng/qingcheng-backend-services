/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.dao;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.*;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liguohua on 16/8/27.
 */

public class ZookeeperDao {
    public static final String ZNODE_SEQUENTIAL_SPERERATER = "_";
    public static final String ZNODE_TYPE_EPHEMERAL_SEQUENTIAL = "EPHEMERAL_SEQUENTIAL";
    public static final String ZNODE_TYPE_EPHEMERAL = "EPHEMERAL";
    public static final String ZNODE_TYPE_PERSISTENT_SEQUENTIAL = "PERSISTENT_SEQUENTIAL";
    public static final String ZNODE_TYPE_PERSISTENT = "PERSISTENT";
    public static final CuratorFramework zkClient;

    /**
     * zookeeper集群的客户端创建器
     */
    static {

        zkClient = CuratorFrameworkFactory.newClient(AppStatic.getZooKeeperClusterNodeAddress(), new RetryNTimes(AppStatic.getZookeeperClusterClientRetryTimes(), AppStatic.getZookeeperClusterClientTimeout()));
        zkClient.start();
    }

    /**
     * 创建ESznode节点,不指定ACL
     *
     * @param znodePath    znodePath
     * @param znodeContent znodeContent
     * @return 真正创建出来的的znode节点
     */
    public static String createPSznodeWtihOutACL(String znodePath, String znodeContent) {
        return createZnode(znodePath, ZNODE_TYPE_PERSISTENT_SEQUENTIAL, znodeContent, null);
    }

    /**
     * 创建ESznode节点,不指定ACL
     *
     * @param znodePath    znodePath
     * @param znodeContent znodeContent
     * @return 真正创建出来的的znode节点
     */
    public static String createPznodeWtihOutACL(String znodePath, String znodeContent) {
        return createZnode(znodePath, ZNODE_TYPE_PERSISTENT, znodeContent, null);
    }

    /**
     * 创建ESznode节点,不指定ACL
     *
     * @param znodePath    znodePath
     * @param znodeContent znodeContent
     * @return 真正创建出来的的znode节点
     */
    public static String createESznodeWtihOutACL(String znodePath, String znodeContent) {
        return createZnode(znodePath, ZNODE_TYPE_EPHEMERAL_SEQUENTIAL, znodeContent, null);
    }

    /**
     * 创建Eznode节点,不指定ACL
     *
     * @param znodePath    znodePath
     * @param znodeContent znodeContent
     * @return 真正创建出来的的znode节点
     */
    public static String createEznodeWtihOutACL(String znodePath, String znodeContent) {
        return createZnode(znodePath, ZNODE_TYPE_EPHEMERAL, znodeContent, null);
    }

    /**
     * 创建znode节点,不指定ACL
     *
     * @param znodePath    znodePath
     * @param znodeType    znodeType
     * @param znodeContent znodeContent
     * @return 真正创建出来的的znode节点
     */
    public static String createZnodeWtihOutACL(String znodePath, String znodeType, String znodeContent) {
        return createZnode(znodePath, znodeType, znodeContent, null);
    }

    /**
     * 创建znode节点
     *
     * @param znodePath    znodePath
     * @param znodeType    znodeType
     * @param znodeContent znodeContent
     * @param aclList      aclList
     * @return 真正创建出来的的znode节点
     */
    public static String createZnode(String znodePath, String znodeType, String znodeContent, List<ACL> aclList) {
        CreateBuilder builder = zkClient.create();
        builder.creatingParentsIfNeeded();
        if (ZNODE_TYPE_EPHEMERAL_SEQUENTIAL.equalsIgnoreCase(znodeType)) {
            builder.withMode(CreateMode.EPHEMERAL_SEQUENTIAL);
        } else if (ZNODE_TYPE_EPHEMERAL.equalsIgnoreCase(znodeType)) {
            builder.withMode(CreateMode.EPHEMERAL);
        } else if (ZNODE_TYPE_PERSISTENT_SEQUENTIAL.equalsIgnoreCase(znodeType)) {
            builder.withMode(CreateMode.PERSISTENT_SEQUENTIAL);
        } else if (ZNODE_TYPE_PERSISTENT.equalsIgnoreCase(znodeType)) {
            builder.withMode(CreateMode.PERSISTENT);
        }
        builder.withACL(aclList);
        String znodeFullPathName = null;
        try {
            znodeFullPathName = builder.forPath(znodePath, znodeContent.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return znodeFullPathName;
    }

    /**
     * 删除znode节点
     *
     * @param znodePaht znodePaht
     */
    public static void deleteZnode(String znodePaht) {
        DeleteBuilder delteBuilder = zkClient.delete();
        //保证节点必须删除，如果删除出现错误，则后台程序会不断去尝试删除。
        delteBuilder.guaranteed();
        //如果存在子节点，先删除子节点
        delteBuilder.deletingChildrenIfNeeded();
        //指定版本号
        delteBuilder.withVersion(-1);
        //指定路径
        try {
            delteBuilder.forPath(znodePaht);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param parentZNode parentZNode
     * @return 子节点的短名称列表
     */
    public static List<String> getChildZnodesForShortName(String parentZNode) {
        return getChildZnodes(parentZNode).get(1);
    }

    /**
     * @param parentZNode parentZNode
     * @return 子节点的全名称列表
     */
    public static List<String> getChildZnodesForFullName(String parentZNode) {
        return getChildZnodes(parentZNode).get(0);
    }

    /**
     * @param parentZNode parentZNode
     * @return 子节点的名称列表和全名称列表
     */
    public static List<List<String>> getChildZnodes(String parentZNode) {
        List<List<String>> all = new ArrayList<>();
        GetChildrenBuilder getChildBuilder = zkClient.getChildren();
        List<String> znodeNames = null;
        try {
            znodeNames = getChildBuilder.forPath(znodePathCreator(parentZNode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> znodeFullNames = new LinkedList<>();
        for (String znodeName : znodeNames) {
            String znodeFullName = parentZNode + znodeName;
            znodeFullNames.add(znodeFullName);
        }
        all.add(znodeFullNames);
        all.add(znodeNames);
        return all;
    }

    /**
     * 获取znode中的内容
     *
     * @param znode znode
     * @return znode中的内容
     */
    public static String getZnodeContent(String znode) {
        GetDataBuilder dataBuilder = zkClient.getData();
        dataBuilder.storingStatIn(new Stat());
        byte[] bytes = new byte[0];
        try {
            bytes = dataBuilder.forPath(znodePathCreator(znode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    /**
     * 判断一个节点是否存在
     *
     * @param znode znode
     * @throws Exception
     */
    public static boolean isExistsZNode(String znode) {
        ExistsBuilder existsBuilder = zkClient.checkExists();
        try {
            Stat stat = existsBuilder.forPath(znodePathCreator(znode));
            if (stat != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 处理znode路径的问题
     *
     * @param znodePath 待处理的路径
     * @return 处理好的路径
     */
    private static String znodePathCreator(String znodePath) {
        if (znodePath.endsWith(AppStrUtils.ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = znodePath.substring(0, znodePath.length() - 1);
        }
        return znodePath;
    }

    /**
     * 获取具有最大序列的znode,全名
     *
     * @param znodeNames 节点列表
     * @return 具有最大序列的znode
     */
    public static String getMaxZnodeFullnameFromZnodeList(List<String> znodeNames) {
        return AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath() + getMaxZnodeFromZnodeList(znodeNames);
    }

    /**
     * 获取具有最大序列的znode,名称
     *
     * @param znodeNames 节点列表
     * @return 具有最大序列的znode
     */
    public static String getMaxZnodeFromZnodeList(List<String> znodeNames) {
        return getMaxAndMinZnodeFromZnodeList(znodeNames).get(0);
    }

    /**
     * 获取具有最小序列的znode,全名
     *
     * @param znodeNames 节点列表
     * @return 具有最小序列的znode
     */
    public static String getMinZnodeFullnameFromZnodeList(List<String> znodeNames) {
        return AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath() + getMinZnodeFromZnodeList(znodeNames);
    }

    /**
     * 获取具有最小序列的znode,名称
     *
     * @param znodeNames 节点列表
     * @return 具有最小序列的znode
     */
    public static String getMinZnodeFromZnodeList(List<String> znodeNames) {
        return getMaxAndMinZnodeFromZnodeList(znodeNames).get(1);
    }

    /**
     * 获取具有最大和最小序列的znode
     *
     * @param znodeNames 节点列表
     * @return 具有最大和最小序列的znode
     */
    public static List<String> getMaxAndMinZnodeFromZnodeList(List<String> znodeNames) {
        List<String> maxAndMinZnode = new ArrayList<>();
        String minSquentailZnode = null;
        long minSquentail = Long.MAX_VALUE;
        String maxSquentailZnode = null;
        long maxSquentail = Long.MIN_VALUE;
        //遍历所有znode节点名称
        for (String znodeName : znodeNames) {
            //切分znode节点名称
            String[] hostname_squence = znodeName.split(ZookeeperDao.ZNODE_SEQUENTIAL_SPERERATER);
            if (hostname_squence.length == 2) {
                //拿出序列号
                String firstNotZeroStr = AppStrUtils.getFirstNotZeroStr(hostname_squence[1]);
                //将序列号转为数值
                long suquentailNum = Long.parseLong(firstNotZeroStr);
                //比较最小
                if (suquentailNum < minSquentail) {
                    minSquentail = suquentailNum;
                    minSquentailZnode = znodeName;
                }
                //比较最大
                if (suquentailNum > maxSquentail) {
                    maxSquentail = suquentailNum;
                    maxSquentailZnode = znodeName;
                }
            }
        }
        //加入集合并返回
        maxAndMinZnode.add(maxSquentailZnode);
        maxAndMinZnode.add(minSquentailZnode);
        return maxAndMinZnode;
    }

    /**
     * 监听节点的子节点事件注册
     *
     * @param znodPath                要监控的节点
     * @param zooKeeperWatcherBizDoer 业务逻辑适配器
     */
    public static void watchForChildZnodesChange(String znodPath, ZooKeeperWatcherBizDoer zooKeeperWatcherBizDoer) {
        //注册监听器
        //第三个参数：系统在监听到子节点列表发生变化时同时获取子节点的数据内容
        final PathChildrenCache cache = new PathChildrenCache(zkClient, znodePathCreator(znodPath), true);
        //开启监听
        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册监听器
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
                    throws Exception {
                try {
                    Method method = null;

                    switch (event.getType()) {
                        case CHILD_ADDED://添加子节点
                            method = zooKeeperWatcherBizDoer.getClass().getMethod("childAdded", PathChildrenCacheEvent.class);
                            method.invoke(zooKeeperWatcherBizDoer, event);
                            //System.out.println("新添加子节点中存储的值==>" + new String(event.getData().getData()));
                            break;
                        case CHILD_UPDATED://子节点被修改
                            method = zooKeeperWatcherBizDoer.getClass().getMethod("childUpdated", PathChildrenCacheEvent.class);
                            method.invoke(zooKeeperWatcherBizDoer, event);
                            //System.out.println("被修改的子节点中存储的值==>" + new String(event.getData().getData()));
                            break;
                        case CHILD_REMOVED://子节点被删除
                            method = zooKeeperWatcherBizDoer.getClass().getMethod("childRemoved", PathChildrenCacheEvent.class);
                            method.invoke(zooKeeperWatcherBizDoer, event);
                            // System.out.println("被删除的字节点的路径==>" + event.getInitialData());
                            break;
                        default:
                            break;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    /**
     * 节点事件监听(节点被创建，节点中存储的数据被修改)
     *
     * @param znodPath
     */
    public static String watchForZnodeChangeGetCurrentData(String znodPath) {
        final String[] data = {getZnodeContent(znodPath)};
        //注册监听
        final NodeCache nodeCache = new NodeCache(zkClient, znodPath);
        //开启监听
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加监听器(实现指定接口的实例对象)
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            @Override
            public void nodeChanged() throws Exception {
                byte[] bytes = nodeCache.getCurrentData().getData();
                data[0] = new String(bytes);
            }
        });
        return data[0];
    }

    /**
     * 节点事件监听(节点被创建，节点中存储的数据被修改)
     *
     * @param znodPath
     * @param zooKeeperWatcherBizDoer
     */
    public static void watchForZnodeChange(String znodPath, ZooKeeperWatcherBizDoer zooKeeperWatcherBizDoer) {
        //注册监听
        final NodeCache nodeCache = new NodeCache(zkClient, znodPath);
        //开启监听
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加监听器(实现指定接口的实例对象)
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                Method method = zooKeeperWatcherBizDoer.getClass().getMethod("znodeChange", NodeCache.class);
                method.invoke(zooKeeperWatcherBizDoer, nodeCache);
            }
        });
    }
}


