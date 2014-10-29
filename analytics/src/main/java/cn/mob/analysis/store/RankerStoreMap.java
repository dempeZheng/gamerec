package cn.mob.analysis.store;

import cn.mob.analysis.store.sync.MongoSyncDao;
import cn.mob.analysis.store.sync.SyncDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * TODO
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class RankerStoreMap extends TimeSyncStoreMap {

    public static final Logger LOGGER = Logger.getLogger(RankerStoreMap.class);

    public static RankerStoreMap instance;

    private final static SyncDao syncDao = new MongoSyncDao();

    private RankerStoreMap() {
        super();
    }

    @Override
    public void sync(Map<String, Integer> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sycn4Map(storeMap);
    }

    public static RankerStoreMap getInstance() {
        if (instance == null) {
            instance = new RankerStoreMap();
        }
        return instance;
    }

}
