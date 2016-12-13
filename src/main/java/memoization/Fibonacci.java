package memoization;

import algorithms.BubbleSort;
import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

import java.util.Hashtable;

/**
 * Created by clinton on 11/25/16.
 */
public class Fibonacci {
    //fib hash table
    public static Hashtable<Long, Long> hashTable = new Hashtable<Long, Long>();
    public static Timer timer = MetricsSingelton.getRegistry().timer(Fibonacci.class.getSimpleName());

    /**
     * Wrapper for actual call so we can benchmark
     * @param i
     * @return
     */
    public static Long Fibonacci(Long i) {
        final Timer.Context context = timer.time();
        if (i <= 1) {
            return i;
        }

        Long x = FibonacciH(i);
        context.stop();
        return x;
    }

    private static Long FibonacciH(Long i) {
        if (i <= 1) {
            return i;
        }

        return FibonacciH(i-1) + FibonacciH(i - 2);
    }

    public static Long FibonacciMemoization(Long i) {
        final Timer.Context context = timer.time();
        if (i <= 1) {
            return i;
        }

        Long x = FibonacciMemoizationH(i);
        context.stop();
        return x;
    }

    private static Long FibonacciMemoizationH(Long i) {
        if (i <= 1) {
            return i;
        } else if ( hashTable.containsKey(i)) {
            return hashTable.get(i);
        }
        Long x = FibonacciMemoizationH(i-1) + FibonacciMemoizationH(i - 2);
        hashTable.put(i, x);
        return x;
    }

    public static Long FibonacciMemoizationOptimized(Long i) {
        final Timer.Context context = timer.time();
        if (i <= 1) {
            return i;
        }

        Long x = FibonacciMemoizationOptimizedH(i);
        context.stop();
        return x;
    }

    private static Long FibonacciMemoizationOptimizedH(Long i) {
        if (i <= 1) {
            return i;
        } else if ( i > 3 && hashTable.containsKey(i)) {
            return hashTable.get(i);
        }
        Long x = FibonacciMemoizationOptimizedH(i-1) + FibonacciMemoizationOptimizedH(i - 2);
        if ( i > 3 ) {
            hashTable.put(i, x);
        }
        return x;
    }
}
