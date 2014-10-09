package cn.mob.gamerec.analystic.store;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class DownLoadStoreMap extends StoreMap implements Sync {
    public static DownLoadStoreMap instance;

    private DownLoadStoreMap() {
        System.out.println("start sync.freq ");
        sync();
    }

    public static DownLoadStoreMap getInstance() {
        if (instance == null) {
            instance = new DownLoadStoreMap();
        }
        return instance;
    }

    @Override
    public void freq() {

    }

    @Override
    public void sync() {

        System.out.println("sync to mongodb now");
        //启动一个线程同步

    }
}
