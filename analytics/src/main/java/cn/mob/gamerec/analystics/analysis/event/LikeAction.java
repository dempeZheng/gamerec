package cn.mob.gamerec.analystics.analysis.event;

import cn.mob.gamerec.analystics.store.LikeStoreMap;

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
