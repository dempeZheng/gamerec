package cn.mob.gamerec.analystics.sync;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */

import cn.mob.gamerec.analystics.Config;
import cn.mob.gamerec.analystics.R;
import com.mongodb.*;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class MongoSyncDao implements SyncDao {

    public static final Logger LOGGER = Logger.getLogger(MongoSyncDao.class);

    private final static String DEF_MONGO_URL = "127.0.0.1:27017";
    private final static String DEF_MONGOD_DB = "game_rec";


    private DB db;

    private Mongo mongo;


    public MongoSyncDao() {
        this(Config.getString(R.MONGO_URL, DEF_MONGO_URL));
    }

    public MongoSyncDao(String url) {
        try {
            mongo = new Mongo(url);
            db = mongo.getDB(DEF_MONGOD_DB);
        } catch (UnknownHostException e) {
            LOGGER.error(url + " unknown host error", e);
        }
    }

    public DBCollection getCollection(String name) {
        return db.getCollection(name);
    }

    @Override
    public void sycn4Map(Map<String, Integer> storeMap) {
        LOGGER.debug("sync map to mongodb now");
        LOGGER.info("sync todo");

    }

    public void incrBy(String name, DBObject query, DBObject update) {
        DBCollection collection = getCollection(name);
        collection.update(query, new BasicDBObject("$inc", update), true, false);
    }
}
