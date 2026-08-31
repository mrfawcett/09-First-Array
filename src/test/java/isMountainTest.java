import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class IsMountainTest {

    @DisplayName("isMountain: {1, 2, 3, 2, 1} -> true")
    @Test
    void isMountain_Test01() {
        int[] mtn = {1, 2, 3, 2, 1};
        assertEquals(true, Mountain.isMountain(mtn), "up to 3, then down: a mountain");
    }

    @DisplayName("isMountain: {1, 2, 1, 2, 1} -> false (climbs again after the peak)")
    @Test
    void isMountain_Test02() {
        int[] mtn = {1, 2, 1, 2, 1};
        assertEquals(false, Mountain.isMountain(mtn), "after the first peak the values must only decrease");
    }

    @DisplayName("isMountain: {1, 2, 3, 1, 5} -> false")
    @Test
    void isMountain_Test03() {
        int[] mtn = {1, 2, 3, 1, 5};
        assertEquals(false, Mountain.isMountain(mtn), "1 -> 5 at the end is not decreasing");
    }

    @DisplayName("isMountain: {1, 4, 2, 1, 0} -> true (peak near the front)")
    @Test
    void isMountain_Test04() {
        int[] mtn = {1, 4, 2, 1, 0};
        assertEquals(true, Mountain.isMountain(mtn), "one step up, three steps down: still a mountain");
    }

    @DisplayName("isMountain: {9, 3, 5, 7, 5} -> false (falls before the peak)")
    @Test
    void isMountain_Test05() {
        int[] mtn = {9, 3, 5, 7, 5};
        assertEquals(false, Mountain.isMountain(mtn), "9 -> 3 before the peak is not increasing");
    }

    @DisplayName("isMountain: {3, 2, 1} -> false (no peak at all)")
    @Test
    void isMountain_Test06() {
        int[] mtn = {3, 2, 1};
        assertEquals(false, Mountain.isMountain(mtn), "no peak means no mountain - getPeakIndex returns -1");
    }

    @DisplayName("isMountain: {3, 4, 3, 5, 6, 5} -> false (two peaks)")
    @Test
    void isMountain_Test07() {
        int[] mtn = {3, 4, 3, 5, 6, 5};
        assertEquals(false, Mountain.isMountain(mtn), "the first peak is at 1 and the array rises again after it");
    }

    @DisplayName("isMountain: {1, 3, 4, 8, 7, 5, 2} -> true (longer slopes)")
    @Test
    void isMountain_Test08() {
        int[] mtn = {1, 3, 4, 8, 7, 5, 2};
        assertEquals(true, Mountain.isMountain(mtn), "strictly up to 8 at index 3, strictly down after");
    }

    @DisplayName("isMountain: {1, 2, 1} -> true (smallest mountain)")
    @Test
    void isMountain_Test09() {
        int[] mtn = {1, 2, 1};
        assertEquals(true, Mountain.isMountain(mtn), "three elements, peak in the middle");
    }

    @DisplayName("isMountain: {1, 3, 8, 8, 2} plateau -> false")
    @Test
    void isMountain_Test10() {
        int[] mtn = {1, 3, 8, 8, 2};
        assertEquals(false, Mountain.isMountain(mtn), "8, 8 is a plateau, not a peak, so there is no mountain");
    }

    @DisplayName("isMountain: {5} single element -> false (no exception)")
    @Test
    void isMountain_Test11() {
        int[] mtn = {5};
        assertEquals(false, Mountain.isMountain(mtn), "one element cannot be a mountain");
    }
}
