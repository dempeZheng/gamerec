package cn.mob.gamerec.analystics.analysis.event;

import cn.mob.gamerec.analystics.store.DownLoadStoreMap;

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
