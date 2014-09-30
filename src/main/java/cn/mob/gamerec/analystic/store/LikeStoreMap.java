package cn.mob.gamerec.analystic.store;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class LikeStoreMap extends StoreMap implements Sync {
    public static LikeStoreMap instance;

    private LikeStoreMap() {
        sync();
    }

    public static LikeStoreMap getInstance() {
        if (instance == null) {
            instance = new LikeStoreMap();
        }
        return instance;
    }

    @Override
    public void freq() {

    }

    @Override
    public void sync() {

        //启动一个线程同步

    }
}
