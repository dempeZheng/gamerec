package cn.mob.gamerec.queue.server;

import com.lamfire.logger.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;


class ServerHandler extends SimpleChannelUpstreamHandler {
    private static final Logger LOGGER = Logger.getLogger("ACCESS_LOG");
    private ActionRegistry registry;
    private ExecutorService worker;
    private AtomicInteger counter = new AtomicInteger();

    public ServerHandler(ActionRegistry registry, ExecutorService worker) {
        this.registry = registry;
        this.worker = worker;
    }

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        HttpRequest request = (HttpRequest) e.getMessage();
        String uri = request.getUri();

        // 处理get请求时 get请求会在action名后面带有一些参数，让其匹配到例如：/callback这样的action
        if (uri.contains("?")) {
            uri = uri.substring(0, uri.indexOf("?"));
        }

        Action action = registry.lookup(uri);
        if (action == null) {
            ActionWriter.writeError(ctx.getChannel(), HttpResponseStatus.BAD_REQUEST);
            return;
        }

        try {
            ChannelBuffer reqBuffer = request.getContent();
            byte[] message = reqBuffer.array();
            ActionContext context = new ActionContext(ctx, request);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("[access] uri : %s ,req : %s ", request.getUri(), request.getHeaders().toString()));
            }
            if (this.worker != null) {
                ActionTask task = new ActionTask(context, action, message);
                this.worker.submit(task);
                return;
            }
            byte[] result = action.execute(context, message);
            ActionWriter.writeResponse(ctx.getChannel(), context.getHttpResponse(), result);
        } catch (Throwable t) {
            ActionWriter.writeError(ctx.getChannel(), HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Throwable cause = e.getCause();
        try {
            if (cause instanceof TooLongFrameException) {
                ActionWriter.writeError(ctx.getChannel(), HttpResponseStatus.BAD_REQUEST);
                return;
            }
            cause.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        this.counter.decrementAndGet();
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelOpen(ctx, e);
        this.counter.incrementAndGet();
    }


}
