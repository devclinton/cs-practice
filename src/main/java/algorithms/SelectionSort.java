package algorithms;

import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/24/16.
 */
public class SelectionSort {
    public static Timer timer = MetricsSingelton.getRegistry().timer(SelectionSort.class.getSimpleName());

    /**
     * Sorts an array using quick sorth method. This is a helper that then calls the actual sort method
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void SelectionSort(T[] ary) {
        //our benchmark
        final Timer.Context context = timer.time();

        for( int x = 0; x < ary.length; x++) {
            int minIndex = x;
            int y = x + 1;
            for ( ; y < ary.length; y++) {
                if (Utils.compare(ary, y, minIndex) < 0) {
                    minIndex = y;
                }
            }
            if (minIndex != x) {
                Utils.swap(ary,x, minIndex);
            }
        }
        context.stop();
    }
}
