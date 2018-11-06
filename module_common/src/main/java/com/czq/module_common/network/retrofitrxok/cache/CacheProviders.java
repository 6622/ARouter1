package com.czq.module_common.network.retrofitrxok.cache;

import com.czq.module_common.tool.rx.RxFileTool;
import com.czq.module_common.tool.rx.RxTool;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.JacksonSpeaker;

public class CacheProviders {

    private static UserCacheProviders userCacheProviders;

    public synchronized static UserCacheProviders getUserCache() {
        if (userCacheProviders == null) {
            userCacheProviders = new RxCache.Builder()
            .persistence(RxFileTool.getCacheFolder(), new JacksonSpeaker())//缓存文件的配置、数据的解析配置
            .using(UserCacheProviders.class);//这些配置对应的缓存接口
        }
        return userCacheProviders;
    }
}
