package cn.mob.gamerec.analystics.analysis;

import cn.mob.gamerec.analystics.R;
import cn.mob.gamerec.analystics.analysis.event.DownLoadAction;
import cn.mob.gamerec.analystics.analysis.event.LikeAction;
import cn.mob.gamerec.analystics.analysis.event.PlayAction;
import cn.mob.gamerec.analystics.analysis.event.ShareAction;
import com.alibaba.fastjson.JSONObject;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class Analysis {

    public static void streaming(String message) {
        JSONObject json = JSONObject.parseObject(message);

        //JSON json = JSON.parseFromJSONString(message);
        int et = (Integer) json.get(R.EVENT_TYPE);
        String eventMessage = (String) json.get(R.EVENT_MESSAGE);

        switch (et) {
            case R.DOWNLOAD_TYPE_CODE:
                DownLoadAction.handler(eventMessage);
                break;
            case R.LIKE_TYPE_CODE:
                LikeAction.handler(eventMessage);
                break;
            case R.PLAY_TYPE_CODE:
                PlayAction.handler(eventMessage);
                break;
            case R.SHARE_TYPE_CODE:
                ShareAction.handler(eventMessage);
                break;
        }

    }
}
