package memoization;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Timer;
import utils.MetricsSingelton;

import java.util.Hashtable;
import java.util.stream.IntStream;

/**
 * Created by clinton on 11/28/16.
 */
public class CoinChange {
    public static Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
    public static Timer timer = MetricsSingelton.getRegistry().timer(CoinChange.class.getSimpleName());
    public static Counter recurseCounter = MetricsSingelton.getRegistry().counter(CoinChange.class.getSimpleName() + " counter");


    public static int minimalCoins(int amount, int[] coins) {
        final Timer.Context context = timer.time();

        int anwser = minimalCoinsHelper(amount, coins);
        context.stop();
        return anwser;
    }

    private static int minimalCoinsHelper(int amount, int[] coins) {
        recurseCounter.inc();
        int minCoins = amount;
        int numCoins = 0;
        if (IntStream.of(coins).anyMatch(x -> x == amount)) {
            return 1;
        } else {
            for (int x : coins) {
                if ( x < amount) {
                    numCoins = 1 + minimalCoinsHelper(amount - x, coins);
                    if (numCoins < minCoins) {
                        minCoins = numCoins;
                    }
                }
            }
        }
        return minCoins;

    }

    public static int minimalCoinsMemoization(int amount, int[] coins) {
        final Timer.Context context = timer.time();

        int anwser = minimalCoinsMemoizationHelper(amount, coins);
        context.stop();
        return anwser;
    }

    private static int minimalCoinsMemoizationHelper(int amount, int[] coins) {
        recurseCounter.inc();
        int minCoins = amount;
        int numCoins = 0;

        if (IntStream.of(coins).anyMatch(x -> x == amount)) {
            return 1;
        } else {
            for (int x : coins) {
                if ( x < amount) {
                    if (hashTable.containsKey(amount - x)) {
                        numCoins = 1 + hashTable.get(amount - x);
                    } else {
                        numCoins = 1 + minimalCoinsMemoizationHelper(amount - x, coins);
                    }
                    if (numCoins < minCoins) {
                        minCoins = numCoins;
                    }
                }
            }
        }
        hashTable.put(amount, minCoins);
        return minCoins;

    }


}
