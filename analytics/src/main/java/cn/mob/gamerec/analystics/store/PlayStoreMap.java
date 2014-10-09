package cn.mob.gamerec.analystics.store;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class PlayStoreMap extends StoreMap implements Sync {
    public static PlayStoreMap instance;

    private PlayStoreMap() {
        System.out.println("start sync.freq ");
        sync();
    }

    public static PlayStoreMap getInstance() {
        if (instance == null) {
            instance = new PlayStoreMap();
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
