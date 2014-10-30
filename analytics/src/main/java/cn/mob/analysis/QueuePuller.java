package cn.mob.analysis;

import com.lamfire.hydra.reply.ReplySnake;
import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lamfire
 * Date: 14-10-20
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */

public class QueuePuller implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(QueuePuller.class);
    private ReplySnake snake;

    private BlockingQueue<String> blockingQueue;

    public QueuePuller(String host, int port, BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        snake = new ReplySnake(host, port);
        snake.setAutoConnectRetry(true);
        try {
            snake.connect();
        } catch (Exception e) {
            LOGGER.error("[QUEUE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
        }

    }


    public byte[] pull() {
        byte[] bytes = null;
        if (!snake.hasConnections()) {
            LOGGER.error("[QUEUE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            return bytes;
        }
        JSON json = new JSON();
        json.put("CMD", "POLL");
        try {
            bytes = snake.send(json.toBytes());
        } catch (Exception e) {
            LOGGER.error("[QUEUE NOT CONNECT ERROR] : HOST = " + snake.getHost() + ", PORT = " + snake.getPort());
            LOGGER.error(e.getMessage());
        }
        if (bytes != null && bytes.length == 0) {
            bytes = null;
        }
        return bytes;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            while (true) {
                byte[] data = pull();
                if (data == null) {
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                //LOGGER.debug("[data] = "+data);
                blockingQueue.put(new String(data));
                //localQueue.add(data);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static void main(String args[]) {

    }
}
