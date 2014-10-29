package cn.mob.analysis.util;

import cn.mob.analysis.DeadNodeException;
import com.alibaba.fastjson.JSONArray;
import com.lamfire.hydra.reply.ReplySnake;
import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;
import com.lamfire.utils.Bytes;

/**
 * Created by dempe on 14-10-27.
 */
public class AnalysisClient {
    private final static Logger LOGGER = Logger.getLogger(AnalysisClient.class);

    public static final String CMD_PUSH = "PUSH";
    private ReplySnake snake;

    public AnalysisClient(String host, int port) {
        snake = new ReplySnake(host, port);
        snake.setAutoConnectRetry(true);
        try {
            snake.connect();
        } catch (Exception e) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            LOGGER.info("[AUTO CONNECT] : TRUE");
        }

    }

    public long size() {
        JSON js = new JSON();
        js.put("CMD", "SIZE");
        byte[] result = snake.send(js.toBytes());
        return Bytes.toLong(result);
    }


    public void sendCountData(String key, JSONArray date) throws DeadNodeException {
        if (!snake.hasConnections()) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            snake.connect();

        }
        JSON add = new JSON();
        add.put("type", 1);
        add.put("key", key);
        add.put("date", date);

        JSON js = new JSON();
        js.put("CMD", CMD_PUSH);
        js.put("ARGS", add.toBytes());
        try {
            snake.sendOnly(js.toBytes()).awaitUninterruptibly();
        } catch (Exception e) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            throw new DeadNodeException();
        }
    }

    public void sendUniqueData(String uniqueId, String key, JSONArray date) throws DeadNodeException {
        if (!snake.hasConnections()) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            snake.connect();

        }
        JSON add = new JSON();
        add.put("type", 2);
        add.put("key", key);
        add.put("id", uniqueId);
        add.put("date", date);

        JSON js = new JSON();
        js.put("CMD", CMD_PUSH);
        js.put("ARGS", add.toBytes());
        try {
            snake.sendOnly(js.toBytes()).awaitUninterruptibly();
        } catch (Exception e) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            throw new DeadNodeException();
        }
    }

    public void sendRankData(String rank, String key, JSONArray date) throws DeadNodeException {
        if (!snake.hasConnections()) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            throw new DeadNodeException();
        }
        JSON add = new JSON();
        add.put("rank", rank);
        add.put("key", key);
        add.put("type", 3);
        add.put("date", date);

        JSON js = new JSON();
        js.put("CMD", CMD_PUSH);
        js.put("ARGS", add.toBytes());
        try {
            snake.sendOnly(js.toBytes()).awaitUninterruptibly();
        } catch (Exception e) {
            LOGGER.error("[NODE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            throw new DeadNodeException();
        }
    }


}
