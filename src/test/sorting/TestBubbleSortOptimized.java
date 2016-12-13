package sorting;

import algorithms.BubbleSort;
import algorithms.QuickSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestBubbleSortOptimized extends BaseSortTest {

    private static Class sortClass = BubbleSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public String getSortMethodName() {
        return sortClass.getSimpleName() + "Optimized";
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName() + " optimized"});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName() + " optimized");
        BubbleSort.bubblesortOptimzedTimer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName() + " optimized");
    }
}
