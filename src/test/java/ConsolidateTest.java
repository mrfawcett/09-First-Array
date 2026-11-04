import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsolidateTest {

    /** [Trigger, null, Silver, Lady, null, Patches, Duke] */
    private HorseBarn createStable() {
        Horse[] stable = new Horse[7];
        stable[0] = new Horse("Trigger", 1340);
        stable[2] = new Horse("Silver", 1210);
        stable[3] = new Horse("Lady", 1575);
        stable[5] = new Horse("Patches", 1350);
        stable[6] = new Horse("Duke", 1410);
        return new HorseBarn(stable);
    }

    /** All seven spaces full, no gaps. */
    private HorseBarn createStable2() {
        Horse[] stable = new Horse[7];
        stable[0] = new Horse("Trigger", 1340);
        stable[1] = new Horse("Spirit", 1020);
        stable[2] = new Horse("Silver", 1210);
        stable[3] = new Horse("Lady", 1575);
        stable[4] = new Horse("Epona", 1155);
        stable[5] = new Horse("Patches", 1350);
        stable[6] = new Horse("Duke", 1410);
        return new HorseBarn(stable);
    }

    /** Checks that the first expectedNames.length spaces hold horses with
     *  those names, in order, and that EVERY space after them is null. */
    private void assertBarn(String[] expectedNames, HorseBarn barn) {
        Horse[] result = barn.getSpaces();
        for (int i = 0; i < expectedNames.length; i++) {
            assertNotNull(result[i], "space " + i + " should hold a horse, not null");
            assertEquals(expectedNames[i], result[i].getName(),
                    "wrong horse in space " + i + " after consolidate");
        }
        for (int i = expectedNames.length; i < result.length; i++) {
            assertNull(result[i], "space " + i + " should be null after the last horse");
        }
    }

    @DisplayName("consolidate: [Trigger, _, Silver, Lady, _, Patches, Duke] -> five horses then two nulls")
    @Test
    void consolidate_Test01() {
        HorseBarn barn = createStable();
        barn.consolidate();
        assertBarn(new String[] {"Trigger", "Silver", "Lady", "Patches", "Duke"}, barn);
    }

    @DisplayName("consolidate: leading empty space -> first horse moves to index 0")
    @Test
    void consolidate_Test02() {
        HorseBarn barn = createStable();
        barn.getSpaces()[0] = null;
        barn.consolidate();
        assertBarn(new String[] {"Silver", "Lady", "Patches", "Duke"}, barn);
    }

    @DisplayName("consolidate: gap near the end -> Duke slides into index 3")
    @Test
    void consolidate_Test03() {
        HorseBarn barn = createStable();
        barn.getSpaces()[5] = null;
        barn.consolidate();
        assertBarn(new String[] {"Trigger", "Silver", "Lady", "Duke"}, barn);
    }

    @DisplayName("consolidate: barn with no gaps is left exactly as it was")
    @Test
    void consolidate_Test04() {
        HorseBarn barn = createStable2();
        Horse[] before = new Horse[7];
        for (int i = 0; i < 7; i++)
            before[i] = barn.getSpaces()[i];
        barn.consolidate();
        assertBarn(new String[] {"Trigger", "Spirit", "Silver", "Lady", "Epona", "Patches", "Duke"}, barn);
        for (int i = 0; i < 7; i++)
            assertSame(before[i], barn.getSpaces()[i],
                    "space " + i + " should still hold the same Horse object (move references, do not copy)");
    }

    @DisplayName("consolidate: single gap in the middle -> everything after it shifts down one")
    @Test
    void consolidate_Test05() {
        HorseBarn barn = createStable2();
        barn.getSpaces()[2] = null;
        barn.consolidate();
        assertBarn(new String[] {"Trigger", "Spirit", "Lady", "Epona", "Patches", "Duke"}, barn);
    }

    @DisplayName("consolidate: three gaps including the last space -> four horses then three nulls")
    @Test
    void consolidate_Test06() {
        HorseBarn barn = createStable2();
        barn.getSpaces()[1] = null;
        barn.getSpaces()[4] = null;
        barn.getSpaces()[6] = null;
        barn.consolidate();
        assertBarn(new String[] {"Trigger", "Silver", "Lady", "Patches"}, barn);
    }

    @DisplayName("consolidate: trailing spaces must become null, not keep a stale copy (catches copy-without-clear)")
    @Test
    void consolidate_Test07() {
        Horse[] stable = new Horse[4];
        stable[1] = new Horse("Lady", 1575);
        stable[3] = new Horse("Duke", 1410);
        HorseBarn barn = new HorseBarn(stable);
        barn.consolidate();
        Horse[] result = barn.getSpaces();
        assertEquals("Lady", result[0].getName(), "Lady should move to index 0");
        assertEquals("Duke", result[1].getName(), "Duke should move to index 1");
        assertNull(result[2], "space 2 should be null after consolidate");
        assertNull(result[3], "space 3 should be null after consolidate - Duke must not appear twice");
    }

    @DisplayName("consolidate: completely empty barn stays all null")
    @Test
    void consolidate_Test08() {
        HorseBarn barn = new HorseBarn(new Horse[5]);
        barn.consolidate();
        assertBarn(new String[0], barn);
    }

    @DisplayName("consolidate: only horse is in the last space -> it moves to index 0")
    @Test
    void consolidate_Test09() {
        Horse[] stable = new Horse[6];
        stable[5] = new Horse("Coco", 900);
        HorseBarn barn = new HorseBarn(stable);
        barn.consolidate();
        assertBarn(new String[] {"Coco"}, barn);
    }
}
