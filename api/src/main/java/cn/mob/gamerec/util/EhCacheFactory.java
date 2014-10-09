package cn.mob.gamerec.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;


/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
public class EhCacheFactory {

    private static final CacheManager cacheManager;

    static {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setMaxElementsInMemory(10000);
        cacheConfiguration.setMaxElementsOnDisk(0);
        cacheConfiguration.setEternal(false);
        cacheConfiguration.setOverflowToDisk(false);
        cacheConfiguration.setTimeToIdleSeconds(30);
        cacheConfiguration.setTimeToLiveSeconds(30);
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        cacheConfiguration.setDiskPersistent(false);

        DiskStoreConfiguration diskStoreConfiguration = new DiskStoreConfiguration();
        String path = System.getProperty("java.io.tmpdir");
        diskStoreConfiguration.setPath(path);

        Configuration configuration = new Configuration();
        configuration.addDefaultCache(cacheConfiguration);
        configuration.addDiskStore(diskStoreConfiguration);

        cacheManager = new CacheManager(configuration);
    }

    public static EhCache newCache(String cacheName, int maxElementsInMemory, int timeToIdleSeconds, int timeToLiveSeconds) {
        EhCache ehcache = new EhCache(cacheName, maxElementsInMemory, timeToIdleSeconds, timeToLiveSeconds);
        cacheManager.addCache(ehcache);
        return ehcache;
    }

    public static EhCache getCache(String cacheName) {
        return (EhCache) cacheManager.getCache(cacheName);
    }
}
