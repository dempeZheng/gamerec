package cn.mob.gamerec.analystic.analysis.event;

import cn.mob.gamerec.analystic.store.LikeStoreMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class LikeAction {
    private static LikeStoreMap storeMap = LikeStoreMap.getInstance();

    public static void handler(String key) {
        storeMap.incr(key);

    }

}
