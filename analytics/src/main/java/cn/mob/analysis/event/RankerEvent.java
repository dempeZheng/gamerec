package cn.mob.analysis.event;

import cn.mob.analysis.R;
import cn.mob.analysis.store.RankerStoreMap;
import cn.mob.analysis.store.TimeSyncStoreMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * TODO
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class RankerEvent {

    private final static Logger LOGGER = Logger.getLogger(CounterEvent.class);

    private final static String EVENT_TYPE = "rank_";

    private static TimeSyncStoreMap storeMap = RankerStoreMap.getInstance();

    public static void handler(String message) {
        JSONObject json = JSONObject.parseObject(message);
        JSONArray dateArray = json.getJSONArray(R.DATE_TIME);
        Iterator<Object> iterator = dateArray.iterator();

        String date;
        boolean flag = true;
        while (iterator.hasNext()) {
            StringBuffer keyBuffer = new StringBuffer(EVENT_TYPE).append(json.getString(R.RANK_FIELD)).append("_");
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
            //LOGGER.info(key);
            String total_key = new StringBuffer().append(EVENT_TYPE).append(R.ALL).append(json.getString(R.KEY)).toString();
            if (flag && !key.contains("null")) {
                storeMap.incr(total_key);
                storeMap.incr(key);

            }
        }


    }
}
