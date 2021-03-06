package cn.mob.analysis.event;

import cn.mob.analysis.R;
import cn.mob.analysis.store.TimeSyncStoreMap;
import cn.mob.analysis.store.UniqueStoreMap;
import cn.mob.analysis.store.bdb.BdbKeySet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class UniqueEvent {

    private final static Logger LOGGER = Logger.getLogger(CounterEvent.class);

    private static final TimeSyncStoreMap storeMap = UniqueStoreMap.getInstance();

    private static BdbKeySet bdbKeySet = BdbKeySet.getInstance();

    private final static String EVENT_TYPE = "unique_";

    public static void handler(String message) {
        JSONObject json = JSONObject.parseObject(message);

        JSONArray dateArray = json.getJSONArray(R.DATE_TIME);
        Iterator<Object> iterator = dateArray.iterator();
        String uniqueId = json.getString(R.ID);
        if (!bdbKeySet.exists(uniqueId)) {
            bdbKeySet.add(uniqueId);
            String date;
            boolean flag = true;
            while (iterator.hasNext()) {
                StringBuffer keyBuffer = new StringBuffer(EVENT_TYPE);
                date = (String) iterator.next();
                int length = date.length();
                if (length == 4) {
                    keyBuffer.append(R.YEARLY);
                } else if (length == 6) {
                    keyBuffer.append(R.MONTHLY);
                } else if (length == 8) {
                    keyBuffer.append(R.DAILY);
                } else {
                    flag = false;
                }
                String key = keyBuffer.append(json.getString(R.KEY)).append(R.KEY_SPLIT).append(R.DATE_TIME).append(R.KEY_SPACE).append(date).toString();
                String total_key = new StringBuffer().append(EVENT_TYPE).append(R.ALL).append(json.getString(R.KEY)).toString();
                if (flag && !key.contains("null")) {
                    storeMap.incr(total_key);
                    storeMap.incr(key);
                }

            }


        }


    }
}
