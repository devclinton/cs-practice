package algorithms;

import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/24/16.
 */
public class ShellSort {

    public static Timer timer = MetricsSingelton.getRegistry().timer(ShellSort.class.getSimpleName());

    /**
     * Sorts an array using quick sorth method. This is a helper that then calls the actual sort method
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void ShellSort(T[] ary) {
        //our benchmark
        final Timer.Context context = timer.time();
        // set our gaps to start comparing with
        int gap = ary.length/2;
        if (gap  < 2) {
            gap = 1;
        }
        // while the gap is greather than 0
        while( gap > 0) {
            // loop from gap to end of array
            for( int x = gap; x < ary.length; x++) {
                T temp = ary[x];
                int y;
                // loop x to when y > gap, decreasing y as we go
                for( y = x; y >= gap && Utils.compare(ary[y - gap],temp) > 0; y-=gap) {
                    Utils.swap(ary, y, y - gap);
                }
                ary[y] = temp;
            }
            gap = gap / 2;
        }
        context.stop();
    }
}
