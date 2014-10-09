package cn.mob.gamerec.analystic.analysis.event;

import cn.mob.gamerec.analystic.store.DownLoadStoreMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class DownLoadAction {
    private static DownLoadStoreMap storeMap = DownLoadStoreMap.getInstance();

    public static void handler(String key) {
        storeMap.incr(key);
    }
}
