package cn.mob.gamerec.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
public class EhCache extends Cache {
    public EhCache(String cacheName, int maxElementsInMemory, long timeToIdleSeconds, long timeToLiveSeconds) {
        super(cacheName, maxElementsInMemory, false, false, timeToIdleSeconds, timeToLiveSeconds);
    }

    public void put(String key, Object value) {
        super.put(new Element(key, value));
    }

    public Object get(String key) {
        Element element = super.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public void remove(String key) {
        super.remove(key);
    }
}
