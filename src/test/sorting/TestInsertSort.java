package sorting;

import algorithms.InsertSort;
import algorithms.SelectionSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestInsertSort extends BaseSortTest {

    private static Class sortClass = InsertSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    }

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        InsertSort.timer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
