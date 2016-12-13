package algorithms;

import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/24/16.
 */
public class InsertSort {
    public static Timer timer = MetricsSingelton.getRegistry().timer(InsertSort.class.getSimpleName());

    /**
     * Sorts an array using quick sorth method. This is a helper that then calls the actual sort method
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void InsertSort(T[] ary) {
        //our benchmark
        final Timer.Context context = timer.time();

        for( int x = 1; x < ary.length; x++) {
            int y = x;
            while( y > 0 && Utils.compare(ary, y-1, y) > 0) {
                Utils.swap(ary, y-1, y);
                y--;
            }
        }
        context.stop();
    }
}
