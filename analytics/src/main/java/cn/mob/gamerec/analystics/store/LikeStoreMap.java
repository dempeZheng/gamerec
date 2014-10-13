package cn.mob.gamerec.analystics.store;

import cn.mob.gamerec.analystics.sync.SyncDao;
import cn.mob.gamerec.analystics.sync.TestSyncDao;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class LikeStoreMap extends StoreMap {

    public static final Logger LOGGER = Logger.getLogger(LikeStoreMap.class);
    public static LikeStoreMap instance;

    private final static SyncDao syncDao = new TestSyncDao();

    private LikeStoreMap() {
        super();
    }

    public static LikeStoreMap getInstance() {
        if (instance == null) {
            instance = new LikeStoreMap();
        }
        return instance;
    }

    public void clearStoreDB() {
        storeMap = new ConcurrentHashMap<String, Integer>();
    }


    @Override
    public void sync(Map<String, Integer> storeMap) {
         syncDao.sycn4Map(storeMap);
    }

}
