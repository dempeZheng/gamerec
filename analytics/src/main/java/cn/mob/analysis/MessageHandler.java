package cn.mob.analysis;

import cn.mob.analysis.event.CounterEvent;
import cn.mob.analysis.event.RankerEvent;
import cn.mob.analysis.event.UniqueEvent;
import cn.mob.analysis.util.QPSMonitor;
import com.alibaba.fastjson.JSONObject;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class MessageHandler {

    private final static QPSMonitor qpsMonitor = QPSMonitor.getInstants();

    public static void streaming(String message) {
        JSONObject json = JSONObject.parseObject(message);
        int et = json.getInteger(R.TYPE);
        qpsMonitor.increment();

        switch (et) {
            case R.COUNTER_TYPE_CODE:
                CounterEvent.handler(message);
                break;
            case R.RANKER_TYPE_CODE:
                RankerEvent.handler(message);
                break;
            case R.UNIQUE_RANKER_TYPE_CODE:
                RankerEvent.handler(message);
                break;
            case R.UNIQUE_TYPE_CODE:
                UniqueEvent.handler(message);
                break;
        }

    }
}
