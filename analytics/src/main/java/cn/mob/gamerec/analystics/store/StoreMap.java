package cn.mob.gamerec.analystics.store;

import cn.mob.gamerec.analystics.Config;
import cn.mob.gamerec.analystics.R;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 每个storeMap享有一个后台同步线程，主要负责数据定时同步
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public abstract class StoreMap implements Command {

    public static final Logger LOGGER = Logger.getLogger(StoreMap.class);

    private final static String DEF_SLEEP_TIME = "5000";

    protected static Map<String, Integer> storeMap = new ConcurrentHashMap<String, Integer>();

    private Thread _syncer;

    private Map<String, Integer> syncMap;

    public StoreMap() {
        final int sleepTime = Integer.parseInt(Config.getString(R.SLEEP_TIME, DEF_SLEEP_TIME));
        _syncer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        syncMap = storeMap;
                        clearStoreDB();
                        sync(syncMap);
                        Thread.sleep(sleepTime);
                    }
                } catch (Exception e) {
                    LOGGER.error("data sync error", e);
                }
            }
        });
        _syncer.setDaemon(true);
        LOGGER.info(this.getClass().getName() + " syncThread start");
        _syncer.start();
    }

    @Override
    public void incr(String key, int step) {
        Integer value = storeMap.get(key);
        storeMap.put(key, value == null ? step : step + value);
    }

    @Override
    public void incr(String key) {
        incr(key, 1);
    }

    public void clearStoreDB() {
        storeMap = new ConcurrentHashMap<String, Integer>();
    }

    public abstract void sync(Map<String, Integer> storeMap);

}
