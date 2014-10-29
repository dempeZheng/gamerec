package cn.mob.analysis.store;

import cn.mob.analysis.store.sync.MongoSyncDao;
import cn.mob.analysis.store.sync.SyncDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class CounterStoreMap extends TimeSyncStoreMap {

    public static final Logger LOGGER = Logger.getLogger(CounterStoreMap.class);

    public static CounterStoreMap instance;

    private final static SyncDao syncDao = new MongoSyncDao();

    private CounterStoreMap() {
        super();
    }

    @Override
    protected void sync(Map<String, Integer> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sycn4Map(storeMap);
    }

    public static CounterStoreMap getInstance() {
        if (instance == null) {
            instance = new CounterStoreMap();
        }
        return instance;
    }

}
