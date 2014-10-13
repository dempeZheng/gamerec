package cn.mob.gamerec.analystics.sync;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */

import com.mongodb.*;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by Administrator on 2014/8/12.
 */
public class MongoSyncDao implements SyncDao {

    public static final Logger LOGGER = Logger.getLogger(MongoSyncDao.class);

    private final static String DEF_MONGO_HOST = "127.0.0.1";
    private final static int DEF_MONGO_PORT = 27017;
    private final static String DEF_MONGOD_DB = "report";


    private DB db;

    private Mongo mongo;


    public MongoSyncDao() {
         this(DEF_MONGO_HOST, DEF_MONGO_PORT);
    }

    public MongoSyncDao(String host, int port) {
        try {
            mongo = new Mongo(host, port);
            db = mongo.getDB(DEF_MONGOD_DB);
        } catch (UnknownHostException e) {
            LOGGER.error(host + ":" + port + " unknown host error", e);
        }
    }

    public DBCollection getCollection(String name) {
        return db.getCollection(name);
    }

    @Override
    public void sycn4Map(Map<String, Integer> storeMap) {

        LOGGER.info("sync map to mongodb now");
        LOGGER.info("sync todo");

    }

    public void incrBy(String name, DBObject query, DBObject update) {
        DBCollection collection = getCollection(name);
        collection.update(query, new BasicDBObject("$inc", update), true, false);
    }
}
