package cn.mob.gamerec.util;

import com.lamfire.utils.JSON;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
public class JSONResult extends JSON {

    public static JSONResult getResult() {
        JSONResult result = new JSONResult();
        result.put("status", 200);
        return result;
    }

    public JSONResult putErrorStatus(int status) {
        put("status", status);
        put("message", ErrorMsg.get(status));
        return this;
    }

    public JSONResult putResult(JSON result) {
        put("result", result);
        return this;
    }

    public JSONResult putTotal(long total) {
        put("total", total);
        return this;
    }
}
