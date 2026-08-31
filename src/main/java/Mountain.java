/** READ FIRST
 * The Mountain class has four static methods that examine an int array to
 * decide whether it contains a peak and whether it is shaped like a mountain.
 * Two of the methods (isIncreasing and isDecreasing) are written for you.
 * You write getPeakIndex and isMountain.
 *
 * Adapted from the College Board's 2014 AP Computer Science A Practice Exam
 * (free-response section).
 *
 * ---- getPeakIndex ----
 * A PEAK is an element whose value is strictly greater than the element
 * immediately before it AND strictly greater than the element immediately
 * after it. The first and last elements can never be peaks, because they
 * have only one neighbor. getPeakIndex returns the index of the FIRST peak in
 * the array, or -1 if there is no peak.
 *
 *      arr              getPeakIndex(arr)
 * {1, 2, 3, 2, 1}             2
 * {1, 2, 1, 2, 1}             1        (first peak wins)
 * {1, 2, 3, 5, 7}            -1        (always climbing)
 * {9, 3, 5, 7, 12}           -1
 * {9, 3, 5, 7, 5}             3
 * {3, 2, 1}                  -1        (always falling)
 * {1, 3, 8, 8, 2}            -1        (a plateau is not a peak: 8 is not > 8)
 *
 * ---- isMountain ----
 * An array is a MOUNTAIN when all three of the following hold:
 *   - it has a peak (use getPeakIndex)
 *   - the elements before the peak are in strictly increasing order
 *     (use isIncreasing)
 *   - the elements after the peak are in strictly decreasing order
 *     (use isDecreasing)
 *
 *      arr             isMountain(arr)
 * {1, 2, 3, 2, 1}          true
 * {1, 2, 1, 2, 1}          false       (goes back up after the peak)
 * {1, 2, 3, 1, 5}          false
 * {1, 4, 2, 1, 0}          true
 * {9, 3, 5, 7, 5}          false       (goes down before the peak)
 * {3, 2, 1}                false       (no peak)
 */
public class Mountain {
    /** PROVIDED - do not change
     * @param array an array of positive integer values
     * @param stop the last index to check
     *          Precondition: 0 <= stop < array.length
     * @return true if for each j such that 0 <= j < stop,
     *      array[j] < array[j + 1]; false otherwise
     */
    public static boolean isIncreasing(int[] array, int stop) {
        if (stop < 0)
            return false;

        for (int j = 0; j < stop; j++) {
            if (array[j] >= array[j + 1])
                return false;
        }
        return true;
    }

    /** PROVIDED - do not change
     * @param array an array of positive integer values
     * @param start the first index to check
     *          Precondition: 0 <= start < array.length - 1
     * @return true if for each j such that start <= j < array.length - 1,
     *      array[j] > array[j + 1]; false otherwise
     */
    public static boolean isDecreasing(int[] array, int start) {
        if (start < 0)
            return false;

        int end = array.length - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= array[j + 1])
                return false;
        }
        return true;
    }

    /** COMPLETE THIS METHOD
     * Precondition: array.length > 0
     * Returns the index of the first peak (local maximum) in the array, or -1
     * if there is none. A peak is strictly greater than BOTH neighbors.
     * Example: getPeakIndex({9, 3, 5, 7, 5}) returns 3.
     *          getPeakIndex({1, 3, 8, 8, 2}) returns -1.
     * Hint: only indexes 1 through array.length - 2 can be peaks. Watch your
     *       loop bounds so you never read array[-1] or array[array.length].
     */
    public static int getPeakIndex(int[] array) {
        // Insert your code below

        return 0;
    }

    /** COMPLETE THIS METHOD
     * Precondition: array.length > 0
     * Returns true if the values in the array are ordered as a mountain:
     * a peak exists, everything before it is strictly increasing, and
     * everything after it is strictly decreasing. Returns false otherwise.
     * Example: isMountain({1, 4, 2, 1, 0}) returns true.
     *          isMountain({1, 2, 1, 2, 1}) returns false.
     * Hint: call the three helper methods; the peak index is the argument for
     *       both isIncreasing and isDecreasing.
     */
    public static boolean isMountain(int[] array) {
        // Insert your code below

        return false;
    }
}
