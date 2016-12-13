package algorithms;

import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/24/16.
 */
public class BubbleSort {

    public static Timer bubblesortTimer = MetricsSingelton.getRegistry().timer(BubbleSort.class.getSimpleName());
    public static Timer bubblesortOptimzedTimer = MetricsSingelton.getRegistry().timer(BubbleSort.class.getSimpleName() + " optimized");

    /**
     * Sorts an array using bubble sort
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void BubbleSort(T[] ary) {
        //our benchmark
        final Timer.Context context = bubblesortTimer.time();
        // call actually sort from 0 to length - 1
        boolean swap = false;
        do {
            swap = false;
            for (int x = 1; x < ary.length; x++) {
                if (Utils.compare(ary,x-1,x) > 0) {
                    Utils.swap(ary,x-1,x);
                    swap = true;
                }
            }
        } while (swap);
        context.stop();
    }

    /**
     * Sorts an array using bubble sort
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void BubbleSortOptimized(T[] ary) {
        //our benchmark
        final Timer.Context context = bubblesortOptimzedTimer.time();
        // call actually sort from 0 to length - 1
        boolean swap = false;
        int right = ary.length;
        do {
            swap = false;
            int newRight = 0;
            for (int x = 1; x < right; x++) {
                if (Utils.compare(ary,x-1,x) > 0) {
                    Utils.swap(ary,x-1,x);
                    swap = true;
                    newRight = x;
                }
            }
            right = newRight;
        } while (swap);
        context.stop();
    }

}
