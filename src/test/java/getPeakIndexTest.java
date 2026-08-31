import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetPeakIndexTest {

    @DisplayName("getPeakIndex: {11, 22, 33, 22, 11} -> 2 (peak in the middle)")
    @Test
    void getPeakIndex_Test01() {
        int[] mtn = {11, 22, 33, 22, 11};
        assertEquals(2, Mountain.getPeakIndex(mtn), "33 at index 2 is greater than both neighbors");
    }

    @DisplayName("getPeakIndex: {11, 22, 11, 22, 11} -> 1 (FIRST peak wins, not the second at 3)")
    @Test
    void getPeakIndex_Test02() {
        int[] mtn = {11, 22, 11, 22, 11};
        assertEquals(1, Mountain.getPeakIndex(mtn), "two peaks exist; return the first one at index 1");
    }

    @DisplayName("getPeakIndex: {1, 2, 3, 5, 7} strictly increasing -> -1")
    @Test
    void getPeakIndex_Test03() {
        int[] mtn = {1, 2, 3, 5, 7};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "the last element is never a peak - it has no right neighbor");
    }

    @DisplayName("getPeakIndex: {9, 3, 5, 7, 12} dips then climbs -> -1")
    @Test
    void getPeakIndex_Test04() {
        int[] mtn = {9, 3, 5, 7, 12};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "the first element is never a peak - it has no left neighbor");
    }

    @DisplayName("getPeakIndex: {9, 3, 5, 7, 5} -> 3 (peak that is not the largest value)")
    @Test
    void getPeakIndex_Test05() {
        int[] mtn = {9, 3, 5, 7, 5};
        assertEquals(3, Mountain.getPeakIndex(mtn), "7 at index 3 is greater than 5 and 5; 9 at index 0 is not a peak");
    }

    @DisplayName("getPeakIndex: {3, 2, 1} strictly decreasing -> -1")
    @Test
    void getPeakIndex_Test06() {
        int[] mtn = {3, 2, 1};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "no element has a smaller neighbor on both sides");
    }

    @DisplayName("getPeakIndex: {3, 4, 3, 5, 6, 5} -> 1")
    @Test
    void getPeakIndex_Test07() {
        int[] mtn = {3, 4, 3, 5, 6, 5};
        assertEquals(1, Mountain.getPeakIndex(mtn), "4 at index 1 is the first peak");
    }

    @DisplayName("getPeakIndex: {1, 3, 4, 6, 7, 9, 2} -> 5 (peak next to the last element)")
    @Test
    void getPeakIndex_Test08() {
        int[] mtn = {1, 3, 4, 6, 7, 9, 2};
        assertEquals(5, Mountain.getPeakIndex(mtn), "9 at index 5 is a peak; make sure your loop reaches length - 2");
    }

    @DisplayName("getPeakIndex: {1, 3, 4, 8, 8, 7, 2} plateau -> -1 (8 is not > 8)")
    @Test
    void getPeakIndex_Test09() {
        int[] mtn = {1, 3, 4, 8, 8, 7, 2};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "a peak must be STRICTLY greater than both neighbors; use > not >=");
    }

    @DisplayName("getPeakIndex: one element {5} -> -1 (no ArrayIndexOutOfBounds)")
    @Test
    void getPeakIndex_Test10() {
        int[] mtn = {5};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "a single element has no neighbors");
    }

    @DisplayName("getPeakIndex: two elements {1, 2} -> -1")
    @Test
    void getPeakIndex_Test11() {
        int[] mtn = {1, 2};
        assertEquals(-1, Mountain.getPeakIndex(mtn), "with two elements nothing has a neighbor on both sides");
    }

    @DisplayName("getPeakIndex: three elements {1, 2, 1} -> 1 (smallest possible peak)")
    @Test
    void getPeakIndex_Test12() {
        int[] mtn = {1, 2, 1};
        assertEquals(1, Mountain.getPeakIndex(mtn), "index 1 is the only candidate and it is a peak");
    }
}
