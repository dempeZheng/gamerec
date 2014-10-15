package cn.mob.gamerec.queue.server;

import cn.mob.gamerec.queue.R;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by dempe on 14-9-29.
 */
public class APIServerBuilder {

    private static final Logger LOGGER = Logger.getLogger(APIServerBuilder.class);

    public String hostname = "0.0.0.0";
    public int port = 8080;
    public boolean chidTcpNoDelay = true;
    public boolean reuserAddress = true;
    public boolean childKeepAlive = true;
    public int nThreads;
    public ActionRegistry actionRegistry = new ActionRegistry();

    public APIServerBuilder() {
        try {
            actionRegistry.mappingPackage(R.base_package);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getHostname() {
        return hostname;
    }

    public APIServerBuilder setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public int getPort() {
        return port;
    }

    public APIServerBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public boolean isChidTcpNoDelay() {
        return chidTcpNoDelay;
    }

    public APIServerBuilder setChidTcpNoDelay(boolean chidTcpNoDelay) {
        this.chidTcpNoDelay = chidTcpNoDelay;
        return this;
    }

    public boolean isReuserAddRess() {
        return reuserAddress;
    }

    public APIServerBuilder setReuserAddress(boolean reuserAddress) {
        this.reuserAddress = reuserAddress;
        return this;
    }

    public boolean isChildKeepAlive() {
        return childKeepAlive;
    }

    public APIServerBuilder setChildKeepAlive(boolean childKeepAlive) {
        this.childKeepAlive = childKeepAlive;
        return this;
    }

    public ActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    public APIServerBuilder setActionRegistry(ActionRegistry actionRegistry) {
        this.actionRegistry = actionRegistry;
        return this;
    }

    public APIServerBuilder setNThreads(int nThreads) {
        this.nThreads = nThreads;
        return this;
    }
}
