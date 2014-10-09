package cn.mob.gamerec.analystics.analysis;

import cn.mob.gamerec.analystics.analysis.event.DownLoadAction;
import cn.mob.gamerec.analystics.analysis.event.LikeAction;
import cn.mob.gamerec.analystics.analysis.event.PlayAction;
import cn.mob.gamerec.analystics.analysis.event.ShareAction;
import com.lamfire.utils.JSON;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class Analysis {

    public static void streaming(String message) {

        JSON json = JSON.parseFromJSONString(message);
        int ea = (Integer) json.get("EventAction");

        switch (ea) {
            case 1:
                DownLoadAction.handler((String) json.get("message"));
            case 2:
                LikeAction.handler((String) json.get("message"));
            case 3:
                PlayAction.handler((String) json.get("message"));
            case 4:
                ShareAction.handler((String) json.get("message"));
        }

    }
}
