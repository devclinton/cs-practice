package algorithms;

import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by clinton on 11/24/16.
 */
public class MergeSort {
    public static Timer timer = MetricsSingelton.getRegistry().timer(MergeSort.class.getSimpleName());

    /**
     * Sorts an array using quick sorth method. This is a helper that then calls the actual sort method
     * @param ary: Array to be sorted
     * @param <T> Type of object
     */
    public static <T extends Comparable<T>> void MergeSort(T[] ary) {
        //our benchmark
        final Timer.Context context = timer.time();
        // because merge sort is very recursive, we are going to call another method that does all the recursion
        MergeSortH(ary, 0, ary.length-1);
        context.stop();
    }

    public static <T extends Comparable<T>> void MergeSortH(T[] ary, int start, int end) {
        if ( start < end ) {
            int mid = start + (end - start) / 2;
            MergeSortH(ary,start,mid);
            MergeSortH(ary,mid+1, end);
            Merge(ary, start, mid, end);
        }
    }

    private static <T extends Comparable<T>> void Merge(T[] ary, int start, int mid, int end) {
        @SuppressWarnings("unchecked")
        T[] helper = ary.clone();

        int i = start;
        int j = mid + 1;
        int k = start;

        //Copy the smallest values from either left or the right side
        while(i <= mid && j <= end) {
            if (Utils.compare(helper,i,j) <= 0) {
                ary[k] = helper[i];
                i++;
            } else {
                ary[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of left right to array
        while(i <= mid) {
            ary[k] = helper[i];
            i++;
            k++;
        }
    }
}
