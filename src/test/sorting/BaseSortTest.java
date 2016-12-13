package sorting;

import com.codahale.metrics.*;
import com.codahale.metrics.Timer;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestRule;
import algorithms.Utils;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.Description;
import utils.MetricsSingelton;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertArrayEquals;

/**
 * Created by clinton on 11/23/16.
 */
@Ignore
public class BaseSortTest {
    private static int RANDOM_ARRAYS = 10000;
    private static final Integer[][] randomArrays= getRandomArrays(RANDOM_ARRAYS, 400);
    private Description currentDescription;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            currentDescription = description;
        }
    };

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    /*
    Our base test for testing our tests.sorting.sorting methods. This uses reflection to get the actual method, and then calls it
     */

    public Class getSortClass() {
        return Arrays.class;
    }
    public String getSortMethodName() {
        return getSortClass().getSimpleName();
    }

    private CsvReporter csvReporter = null;

    final static ConsoleReporter reporter = ConsoleReporter.forRegistry(MetricsSingelton.getRegistry())
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();

    public BaseSortTest() {
        reporter.start(30,TimeUnit.SECONDS);
        resetMetrics();
    }

    @Before
    public void csvReporter() {
        csvReporter = CsvReporter.forRegistry(MetricsSingelton.getRegistry())
                .formatFor(Locale.US)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File(currentDescription.getTestClass().getSimpleName()));
    }

    @After
    public void reportTest() {
        reporter.report();
        resetMetrics();
    }
    
    public void removeOtherTimers(String[] timer) {
        for( String name: MetricsSingelton.getRegistry().getTimers().keySet()) {
            if (!Arrays.asList(timer).contains(name)) {
                MetricsSingelton.getRegistry().remove(name);
            }
        }
    }

    public Method getSortMethod(Class t) throws NoSuchMethodException, ClassNotFoundException {
        Class <?> type = null;
        for(Method i:getSortClass().getMethods()) {
            if ( i.getName().equals(getSortMethodName()) ) {
                type = i.getParameterTypes()[0];
                break;
            }
        }
        // public static void QuickSort.quicksort(java.lang.Comparable[])
        return getSortClass().getDeclaredMethod(getSortMethodName(), type);
    }

    @Test
    public void testSortNumbers() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Integer[] toSort = {245, 123, 15, 1, 36, 2, 256, 44, 56, 99, 4, 10, 9, 6, 5, 20, 15, 200, 199, 500, 501};
        Integer[] copy = toSort.clone();
        Arrays.sort(copy);

        Method sortMethod = getSortMethod(Integer[].class);

        // wrap our argument in object array.
        sortMethod.invoke(null, new Object[] { toSort });
        assertArrayEquals(copy, toSort);
    }

    @Test
    public void testReversedSortedArray() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Integer[] toSort = new Integer[1000];
        for(int x = 0; x < 1000; x++) {
            toSort[x] = 1000 - x;
        }
        Integer[] copy = toSort.clone();
        Arrays.sort(copy);

        Method sortMethod = getSortMethod(Integer[].class);

        // wrap our argument in object array.
        sortMethod.invoke(null, new Object[] { toSort });
        assertArrayEquals(copy, toSort);
    }

    @Test
    public void testSortStrings() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String[] toSort = {"Ender", "Bean", "Petra", "Achilles", "Valentine", "Peter", "Graff"};
        String[] copy = toSort.clone();
        Arrays.sort(copy);

        Method sortMethod = getSortMethod(String.class);

        // wrap our argument in object array.
        sortMethod.invoke(null, new Object[] { toSort });
        assertArrayEquals(copy, toSort);
    }

    private static Integer[][] getRandomArrays(int arrays, int size) {
        Integer[][] randomArrays = new Integer[arrays][size];
        for (int x = 0; x < arrays; x++) {
            for (int y = 0; y < size; y++) {
                randomArrays[x][y] = ThreadLocalRandom.current().nextInt(1, 1000);
            }
        }
        return randomArrays;
    }

    @Test
    public void testLargeArray() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Method sortMethod = getSortMethod(Integer[].class);
        Integer[] toSort = getRandomArrays(1,10000)[0].clone();
        Integer[] copy = toSort.clone();
        Arrays.sort(copy);
        sortMethod.invoke(null, new Object[] { toSort });
        assertArrayEquals(copy, toSort);
    }

    @Test
    public void test10000Iterations() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method sortMethod = getSortMethod(Integer[].class);

        for(int x = 0; x < RANDOM_ARRAYS; x++) {
            Integer[] toSort = randomArrays[x].clone();
            Integer[] copy = toSort.clone();
            Arrays.sort(copy);
            sortMethod.invoke(null, new Object[] { toSort });
            assertArrayEquals(copy, toSort);
        }
    }

    public void resetMetrics() {
        for( String name: MetricsSingelton.getRegistry().getCounters().keySet()) {
            Counter swaps = MetricsSingelton.getRegistry().counter(name);
            swaps.dec(swaps.getCount());
        }
    }
}
