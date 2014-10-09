package cn.mob.gamerec.analystic.analysis.event;

import cn.mob.gamerec.analystic.store.PlayStoreMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class PlayAction {
    private static PlayStoreMap storeMap = PlayStoreMap.getInstance();

    public static void handler(String key) {
        storeMap.incr(key);

    }
}
