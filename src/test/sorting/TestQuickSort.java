package sorting;

import algorithms.QuickSort;
import org.junit.Test;
import utils.MetricsSingelton;


import static org.junit.Assert.assertArrayEquals;

/**
 * Created by clinton on 11/23/16.
 */
public class TestQuickSort extends BaseSortTest {

    private static Class sortClass = QuickSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        QuickSort.timer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
