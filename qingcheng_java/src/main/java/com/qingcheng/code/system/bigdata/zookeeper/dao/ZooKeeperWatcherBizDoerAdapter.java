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
public class ZooKeeperWatcherBizDoerAdapter implements ZooKeeperWatcherBizDoer {
    @Override
    public void childAdded(PathChildrenCacheEvent event) {

    }

    @Override
    public void childUpdated(PathChildrenCacheEvent event) {

    }

    @Override
    public void childRemoved(PathChildrenCacheEvent event) {

    }

    @Override
    public void znodeChange(NodeCache nodeCache) {

    }
}
