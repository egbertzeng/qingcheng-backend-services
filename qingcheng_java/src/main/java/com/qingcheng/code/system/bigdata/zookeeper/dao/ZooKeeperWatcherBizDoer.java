/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.dao;

import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;

/**
 * Created by liguohua on 16/8/28.
 */
public interface ZooKeeperWatcherBizDoer {
    void childAdded(PathChildrenCacheEvent event);

    void childUpdated(PathChildrenCacheEvent event);

    void childRemoved(PathChildrenCacheEvent event);

    void znodeChange(NodeCache nodeCache);
}
