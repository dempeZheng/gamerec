package cn.mob.gamerec.analystics.store;

import cn.mob.gamerec.analystics.sync.MongoSyncDao;
import cn.mob.gamerec.analystics.sync.SyncDao;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class PlayStoreMap extends StoreMap {

    public static PlayStoreMap instance;
    private final static  SyncDao syncDao = new MongoSyncDao();

    private PlayStoreMap() {
        super();
    }


    @Override
    public void sync(Map<String, Integer> storeMap) {
        syncDao.sycn4Map(storeMap);
    }


    public static PlayStoreMap getInstance() {
        if (instance == null) {
            instance = new PlayStoreMap();
        }
        return instance;
    }


}
