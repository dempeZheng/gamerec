package cn.mob.analysis.store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class StoreMapManager {
    private static List<TimeSyncStoreMap> timeSyncStoreMapList = new ArrayList<TimeSyncStoreMap>();

    public static synchronized void addStoreMap(TimeSyncStoreMap storeMap) {
        timeSyncStoreMapList.add(storeMap);
    }

    public static synchronized void safetyClose() {
        for (TimeSyncStoreMap storeMap : timeSyncStoreMapList) {
            storeMap.saftyClose();
        }
    }


}
