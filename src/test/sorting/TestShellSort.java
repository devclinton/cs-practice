package sorting;

import algorithms.QuickSort;
import algorithms.ShellSort;
import utils.MetricsSingelton;

/**
 * Created by clinton on 11/23/16.
 */
public class TestShellSort extends BaseSortTest {

    private static Class sortClass = ShellSort.class;

    @Override
    public Class getSortClass() {
        return sortClass;
    };

    @Override
    public void resetMetrics() {
        super.resetMetrics();
        removeOtherTimers(new String[]{sortClass.getSimpleName()});
        MetricsSingelton.getRegistry().remove(sortClass.getSimpleName());
        ShellSort.timer = MetricsSingelton.getRegistry().timer(sortClass.getSimpleName());
    }
}
