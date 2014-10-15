package cn.mob.gamerec.queue.server;

import org.apache.log4j.Logger;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

public class ActionTask implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ActionTask.class);
    private Action action;
    private ActionContext context;
    private byte[] message;

    public ActionTask(ActionContext context, Action action, byte[] message) {
        this.context = context;
        this.action = action;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            byte[] result = action.execute(context, message);
            ActionWriter.writeResponse(context.getChannel(), context.getHttpResponse(), result);
        } catch (Throwable t) {
            LOGGER.error(t);
            ActionWriter.writeError(context.getChannel(), HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
