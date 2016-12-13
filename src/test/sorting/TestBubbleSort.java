package sorting;

import algorithms.BubbleSort;
import algorithms.QuickSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestBubbleSort extends BaseSortTest {

    private static Class sortClass = BubbleSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        BubbleSort.bubblesortTimer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
