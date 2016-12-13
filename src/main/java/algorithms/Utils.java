package algorithms;

import com.codahale.metrics.Counter;
import utils.MetricsSingelton;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by clinton on 11/24/16.
 */
public class Utils {

    public static Counter swaps = MetricsSingelton.getRegistry().counter("array-swaps");
    public static Counter comparisons = MetricsSingelton.getRegistry().counter("array-comparisons");

    public static final <T> void swap (T[] a, int i, int j) {
        // Track our metrics
        swaps.inc();
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static final <T extends Comparable<T>> int compare (T[] a, int i, int j) {
        // Track our metrics
        comparisons.inc();
        return a[i].compareTo(a[j]);
    }

    public static final <T extends Comparable<T>> int compare (T a, T b) {
        // Track our metrics
        comparisons.inc();
        return a.compareTo(b);
    }
}
