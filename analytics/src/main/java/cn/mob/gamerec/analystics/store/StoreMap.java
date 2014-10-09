package cn.mob.gamerec.analystics.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class StoreMap implements Command {

    private static Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    @Override
    public void incr(String key, int step) {
        Integer value = map.get(key);
        map.put(key, value == null ? step : step + value);
    }

    @Override
    public void incr(String key) {
        incr(key, 1);

    }
}
