package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ThreeSumComplexity {

    // Constants for benchmarking messages and algorithm names
    private static final String BENCHMARK = "------------- Starting Benchmark for N = %s -------------";
    private static final String ALGORITHM_TEMPLATE = "Algorithm: %s, N = %s, Time = %s ms)";
    private static final String ALGORITHM_CUBIC = "Cubic (N^3)";
    private static final String ALGORITHM_QUADRATITHMIC = "Quadrithmic (N^2 logN)";
    private static final String ALGORITHM_QUADRATIC = "Quadratic (N^2)";
    private static final String ALGORITHM_QUADRATIC_CALLIPER = "Quadratic Calliper (N^2)";

    public static void main(String[] args) {

        // Stopwatch for measuring execution time
        Stopwatch stopwatch = null;

        try {
            // Generate a list of powers of 2 within a specified range
            List<Integer> powers = getPowers(7, 12, 2);

            // Iterate over different input sizes
            for (int size : powers) {
                // Print benchmark message for the current input size
                String message = String.format(BENCHMARK, size);
                System.out.println(message);

                // Generate random integer arrays for each algorithm
                Supplier<int[]> intsSupplier = new Source(size, size + 100, 1L).intsSupplier(10);

                int[] intsCubic = intsSupplier.get();
                int[] intsQuadrithmic = intsSupplier.get();
                int[] intsQuadratic = intsSupplier.get();
                int[] intsQuadraticCallipers = intsSupplier.get();

                // Instantiate different ThreeSum implementations for each algorithm
                ThreeSum threeSumCubic = new ThreeSumCubic(intsCubic);
                ThreeSum threeSumQuadrithmic = new ThreeSumQuadrithmic(intsQuadrithmic);
                ThreeSum threeSumQuadratic = new ThreeSumQuadratic(intsQuadratic);
                ThreeSum threeSumQuadraticCallipers = new ThreeSumQuadraticWithCalipers(intsQuadraticCallipers);

                // Measure the execution time for each algorithm
                stopwatch = new Stopwatch();

                threeSumCubic.getTriples();
                long nanoseconds = stopwatch.lap();
                logAlgorithmTime(ALGORITHM_CUBIC, size, nanoseconds);

                threeSumQuadrithmic.getTriples();
                nanoseconds = stopwatch.lap();
                logAlgorithmTime(ALGORITHM_QUADRATITHMIC, size, nanoseconds);

                threeSumQuadratic.getTriples();
                nanoseconds = stopwatch.lap();
                logAlgorithmTime(ALGORITHM_QUADRATIC, size, nanoseconds);

                threeSumQuadraticCallipers.getTriples();
                nanoseconds = stopwatch.lap();
                logAlgorithmTime(ALGORITHM_QUADRATIC_CALLIPER, size, nanoseconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stopwatch != null) {
                stopwatch.close();
            }
        }
    }
    // Log the algorithm execution time with a formatted message
    private static void logAlgorithmTime(String algorithm, int n, long nanoseconds) {
        String message = String.format(ALGORITHM_TEMPLATE, algorithm, n, nanoseconds);
        System.out.println(message);
    }

    // Generate a list of powers of a given base within a specified range
    private static List<Integer> getPowers(int low, int high, int base) {
        List<Integer> result = new ArrayList<>(high - low + 1);

        for (int i = low; i <= high; ++i) {
            result.add((int) Math.pow(base, i));
        }

        return result;
    }

}