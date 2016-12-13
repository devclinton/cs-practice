package memoization;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.MetricsSingelton;
import com.codahale.metrics.Timer;

import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by clinton on 11/25/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFibonacci {

    final static ConsoleReporter reporter = ConsoleReporter.forRegistry(MetricsSingelton.getRegistry())
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();

    @Before
    public void resetStats() {
        Fibonacci.hashTable.clear(); // clear previous cache
        resetMetrics();
    }

    @After
    public void reportTest() {
        reporter.report();
    }

    public void resetMetrics() {
        for( String name: MetricsSingelton.getRegistry().getCounters().keySet()) {
            MetricsSingelton.getRegistry().remove(name);
        }

        for( String name: MetricsSingelton.getRegistry().getTimers().keySet()) {
            MetricsSingelton.getRegistry().remove(name);
            if (name.equals(Fibonacci.class.getSimpleName())) {
                Fibonacci.timer = MetricsSingelton.getRegistry().timer(name);
            }
        }
    }

    @Test
    public void testFibonnaciSmall() {
        assertEquals((long)Fibonacci.Fibonacci(3L), 2);
    }

    @Test
    public void testFibonnaciSmallMemoization() {
        assertEquals((long)Fibonacci.FibonacciMemoization(3L), 2);
    }

    @Test
    public void testFibonnaciSmallOptimizedMemoization() {
        assertEquals((long)Fibonacci.FibonacciMemoizationOptimized(3L), 2);
    }

    @Test
    public void testFibonnaciLarge() {
        assertEquals((long)Fibonacci.Fibonacci(46L), 1836311903);
    }

    @Test
    public void testFibonnaciLargeMemoization() {
        assertEquals((long)Fibonacci.FibonacciMemoization(46L), 1836311903);
    }

    @Test
    public void testFibonnaciLargeOptimizedMemoization() {
        assertEquals((long)Fibonacci.FibonacciMemoizationOptimized(46L), 1836311903);
    }

}
