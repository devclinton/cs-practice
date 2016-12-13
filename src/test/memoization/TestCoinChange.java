package memoization;

import com.codahale.metrics.ConsoleReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.MetricsSingelton;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by clinton on 11/28/16.
 */
public class TestCoinChange {

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
            if (name.contains(CoinChange.class.getSimpleName())) {
                CoinChange.recurseCounter = MetricsSingelton.getRegistry().counter(name);
            }
        }

        for( String name: MetricsSingelton.getRegistry().getTimers().keySet()) {
            MetricsSingelton.getRegistry().remove(name);
            if (name.equals(CoinChange.class.getSimpleName())) {
                CoinChange.timer = MetricsSingelton.getRegistry().timer(name);
            }
        }
    }

    @Test
    public void testCoinChange() {
        int[] coins = {1,2,3};
        int miniumCoins = CoinChange.minimalCoins(5, coins);
        assertEquals(2, miniumCoins);
    }

    @Test
    public void testCoinChangeLarge() {
        int[] coins = {25,10,5,1};
        int miniumCoins = CoinChange.minimalCoins(59, coins);
        assertEquals(7, miniumCoins);
    }

    @Test
    public void testCoinChangeVeryLarge() {
        int[] coins = {25,10,5,1};
        int miniumCoins = CoinChange.minimalCoins(109, coins);
        assertEquals(9, miniumCoins);
    }

    @Test
    public void testCoinChangeMemoization() {
        int[] coins = {1,2,3};
        int miniumCoins = CoinChange.minimalCoinsMemoization(5, coins);
        assertEquals(2, miniumCoins);
    }

    @Test
    public void testCoinChangeLargeMemoization() {
        int[] coins = {25,10,5,1};
        int miniumCoins = CoinChange.minimalCoinsMemoization(59, coins);
        assertEquals(9, miniumCoins);
    }
    @Test
    public void testCoinChangeVeryLargeMemoization() {
        int[] coins = {25,10,5,1};
        int miniumCoins = CoinChange.minimalCoinsMemoization(109, coins);
        assertEquals(9, miniumCoins);
    }
}
