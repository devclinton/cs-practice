package algorithms;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Timer;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import utils.MetricsSingelton;

import java.util.concurrent.TimeUnit;

/**
 * Created by clinton on 11/23/16.
 */
public class QuickSort {

    public static Timer timer = MetricsSingelton.getRegistry().timer(QuickSort.class.getSimpleName());

    /**
     * Sorts an array using quick sorth method. This is a helper that then calls the actual sort method
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void QuickSort(T[] ary) {
        //our benchmark
        final Timer.Context context = timer.time();
        // call actually sort from 0 to length - 1
        sort(ary, 0, ary.length - 1);
        context.stop();
    }

    private static <T extends Comparable<T>> void sort(T[] ary, int start, int end) {

        if (start < end) {
            // Counter to track as we move from left to right through array
            int j = start;
            //Counter to track as we move from right to left through array
            int k = end;

            //Calculate mid point
            int mid = start +  (end - start) / 2;

            // our pivot value
            T pivot = ary[mid];

            // While j is left than k, continue
            while (j <= k) {
                // while the item at array J is less than pivot, increment j
                while (Utils.compare(ary[j], pivot) < 0) {
                    j++;
                }
                // while the item at array K is great than pivot, decrement k
                while (Utils.compare(ary[k], pivot) > 0) {
                    k--;
                }

                // if j is less than k
                if (j <= k) {
                    // set a temporary value
                   Utils.swap(ary,j,k);
                    // increment J(move right)
                    j++;
                    // decrement K(move left)
                    k--;
                }
            }
            // sort from first half of sub array
            sort(ary, start, k);
            // sort second half of array
            sort(ary, j, end);
        }

    }
}
