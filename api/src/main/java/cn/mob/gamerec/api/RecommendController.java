package cn.mob.gamerec.api;

import cn.mob.gamerec.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 视频推荐
 * 暂时实现简单推荐
 * TODO 基于storm || spark streaming 使用CF模型实时推荐
 * TODO 推荐策略可编辑 可认为添加手动推荐权重
 * TODO 显示结果为推荐结果+热门结果+人工编辑结果 若干推荐结果数量不够，显示热门+人工
 * * @author : Dempe
 *
 * @version 1.0 date : 2014/9/29
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

    /**
     * 推荐结果来源于：
     * 离线推荐结果(mongodb)，近线推荐结果(mongodb)，实时推荐结果(redis)
     * TODO
     * @param userid
     * @return
     */
    @RequestMapping("/getPersonalizeVideos ")
    @ResponseBody
    public String getPersonalizeVideos(@RequestParam String userid) {
        return JSONResult.getResult().toString();
    }
}
