package cn.mob.analysis.util;

import com.lamfire.utils.Threads;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dempe on 14-10-20.
 */
public class QPSMonitor {

    private final static Logger LOGGER = Logger.getLogger(QPSMonitor.class);

    private AtomicInteger iops = new AtomicInteger(0);

    private static QPSMonitor instants;

    private QPSMonitor() {

        Runnable statusThread = new Runnable() {
            int preIops = 0;

            @Override
            public void run() {
                int iopsCount = iops.get();
                LOGGER.debug("[total:] = " + iopsCount + " [iops/s] = " + (iopsCount - preIops) + ",[bdb-read/s]");
                preIops = iopsCount;

            }
        };


        Threads.scheduleWithFixedDelay(statusThread, 1, 1, TimeUnit.SECONDS);
    }

    public static QPSMonitor getInstants() {
        if (instants == null)
            instants = new QPSMonitor();
        return instants;
    }


    public void increment() {
        iops.incrementAndGet();
    }

}
