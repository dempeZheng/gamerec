package cn.mob.gamerec.analystics.analysis.event;

import cn.mob.gamerec.analystics.store.ShareStoreMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class ShareAction {
    private static ShareStoreMap storeMap = ShareStoreMap.getInstance();

    public static void handler(String key) {
        storeMap.incr(key);

    }
}