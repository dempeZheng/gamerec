package cn.mob.gamerec.analystic.analysis;

import cn.mob.gamerec.analystic.analysis.event.DownLoadAction;
import cn.mob.gamerec.analystic.analysis.event.LikeAction;
import cn.mob.gamerec.analystic.analysis.event.PlayAction;
import cn.mob.gamerec.analystic.analysis.event.ShareAction;
import com.lamfire.utils.JSON;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class Analysis {

    public void streaming(String message) {

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
