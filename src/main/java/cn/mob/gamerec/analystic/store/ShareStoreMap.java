package cn.mob.gamerec.analystic.store;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class ShareStoreMap extends StoreMap implements Sync {
    public static ShareStoreMap instance;

    private ShareStoreMap() {
        System.out.println("start sync.freq ");
        sync();
    }

    public static ShareStoreMap getInstance() {
        if (instance == null) {
            instance = new ShareStoreMap();
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
