package com.czq.module_common.network.retrofitrxok.cache;

import com.czq.module_common.zhujie.User;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

public interface UserCacheProviders {

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     * @param user
     * @param userName 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey   可以明确地清理指定的数据 DynamicKey.
     * @return
     */
    @LifeCache(duration = 1,timeUnit = TimeUnit.MINUTES)
    Observable<JSONObject> getUser(Observable<JSONObject> user, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}

