package cn.mob.gamerec.analystics.sync;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class TestSyncDao implements SyncDao {
    public static final Logger LOGGER = Logger.getLogger(TestSyncDao.class);

    @Override
    public void sycn4Map(Map<String, Integer> storeMap) {
        LOGGER.info("sync map to mongodb now");
        LOGGER.info("sync todo");
        System.out.println("---------todo------");
        Iterator<String> keys = storeMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println("key:" + key + "  value:" + storeMap.get(key));
        }
    }
}
