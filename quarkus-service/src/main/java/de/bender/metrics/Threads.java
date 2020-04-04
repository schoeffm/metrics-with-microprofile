package de.bender.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Threads {

    private static final Logger LOG = LoggerFactory.getLogger(Threads.class);

    private Threads() { /* mustn't be instantiated */ }

    public static void pause(long periodToPause) {
        try {
            Thread.sleep(periodToPause);
        } catch (InterruptedException e) {
            LOG.info("Thread was interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
