package cn.mob.gamerec.queue.server;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class APIServer {

    private static final Logger LOGGER = Logger.getLogger(APIServer.class);

    ExecutorService worker;
    private ActionRegistry registry;
    private String hostname;
    private int port;
    private boolean chidTcpNoDelay;
    private boolean reuserAddress;
    private boolean childKeepAlive;
    private int nThreads;

    public APIServer(ActionRegistry registry, String hostname, int port) {
        this.registry = registry;
        this.hostname = hostname;
        this.port = port;
    }

    public APIServer(APIServerBuilder build) {
        registry = build.actionRegistry;
        hostname = build.hostname;
        port = build.port;
        nThreads = build.nThreads;
        chidTcpNoDelay = build.chidTcpNoDelay;
        reuserAddress = build.reuserAddress;
        childKeepAlive = build.childKeepAlive;
    }

    public void startup() {
        this.worker = Executors.newFixedThreadPool(nThreads);
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newFixedThreadPool(4), this.worker));
        bootstrap.setPipelineFactory(new ServerPipelineFactory());
        bootstrap.setOption("child.tcpNoDelay", chidTcpNoDelay);
        LOGGER.debug("chid.tcpNoDelay : " + chidTcpNoDelay);
        bootstrap.setOption("reuserAddress", reuserAddress);
        LOGGER.debug("reuserAddress : " + reuserAddress);
        bootstrap.setOption("child.keepAlive", childKeepAlive);
        LOGGER.debug("chid.keepAlive : " + childKeepAlive);
        bootstrap.bind(new InetSocketAddress(hostname, port));
        LOGGER.info("Server start on /" + hostname + ":" + port);
    }

    private class ServerPipelineFactory implements ChannelPipelineFactory {
        public ChannelPipeline getPipeline() throws Exception {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("encoder", new HttpResponseEncoder());
            pipeline.addLast("handler", new ServerHandler(registry, worker));
            return pipeline;
        }
    }
}
