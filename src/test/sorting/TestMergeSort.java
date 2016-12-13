package sorting;

import algorithms.MergeSort;
import algorithms.SelectionSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestMergeSort extends BaseSortTest {

    private static Class sortClass = MergeSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        MergeSort.timer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
