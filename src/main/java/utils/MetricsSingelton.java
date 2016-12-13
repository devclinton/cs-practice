package utils;

import com.codahale.metrics.MetricRegistry;

/**
 * Created by clinton on 11/24/16.
 */
public class MetricsSingelton {
    protected static MetricRegistry metrics = new MetricRegistry();

    public static MetricRegistry getRegistry() {
        return metrics;
    }

    public static void resetMetrics() {
        //TODO
    }
}
