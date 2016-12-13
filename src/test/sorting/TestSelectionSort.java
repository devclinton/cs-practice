package sorting;

import algorithms.SelectionSort;
import algorithms.ShellSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestSelectionSort extends BaseSortTest {

    private static Class sortClass = SelectionSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        SelectionSort.timer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
