package cn.mob.gamerec.analystics.store;

import cn.mob.gamerec.analystics.sync.MongoSyncDao;
import cn.mob.gamerec.analystics.sync.SyncDao;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class DownLoadStoreMap extends StoreMap {
    public static DownLoadStoreMap instance;
    private final static  SyncDao syncDao = new MongoSyncDao();

    private DownLoadStoreMap() {
        super();
        System.out.println("start sync.freq ");
    }

    @Override
    public void sync(Map<String, Integer> storeMap) {
        syncDao.sycn4Map(storeMap);
    }

    public static DownLoadStoreMap getInstance() {
        if (instance == null) {
            instance = new DownLoadStoreMap();
        }
        return instance;
    }


}
