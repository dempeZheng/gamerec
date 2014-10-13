package cn.mob.gamerec.analystics.store;

import cn.mob.gamerec.analystics.sync.MongoSyncDao;
import cn.mob.gamerec.analystics.sync.SyncDao;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class ShareStoreMap extends StoreMap {

    public static ShareStoreMap instance;
    private final static SyncDao syncDao = new MongoSyncDao();

    private ShareStoreMap() {
        super();
    }

    @Override
    public void sync(Map<String, Integer> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sycn4Map(storeMap);
    }

    public static ShareStoreMap getInstance() {
        if (instance == null) {
            instance = new ShareStoreMap();
        }
        return instance;
    }


}
