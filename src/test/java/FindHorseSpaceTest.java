import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FindHorseSpaceTest {

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

    @DisplayName("findHorseSpace: \"Trigger\" is in space 0 -> 0")
    @Test
    void findHorseSpace_Test01() {
        HorseBarn barn = createStable();
        assertEquals(0, barn.findHorseSpace("Trigger"), "Trigger is at index 0");
    }

    @DisplayName("findHorseSpace: \"Silver\" sits after an empty space -> 2")
    @Test
    void findHorseSpace_Test02() {
        HorseBarn barn = createStable();
        assertEquals(2, barn.findHorseSpace("Silver"), "Silver is at index 2 (index 1 is null)");
    }

    @DisplayName("findHorseSpace: \"Lady\" -> 3")
    @Test
    void findHorseSpace_Test03() {
        HorseBarn barn = createStable();
        assertEquals(3, barn.findHorseSpace("Lady"), "Lady is at index 3");
    }

    @DisplayName("findHorseSpace: \"Coco\" is not in the barn -> -1")
    @Test
    void findHorseSpace_Test04() {
        HorseBarn barn = createStable();
        assertEquals(-1, barn.findHorseSpace("Coco"), "no horse named Coco, expected -1");
    }

    @DisplayName("findHorseSpace: \"Epona\" is not in the barn -> -1")
    @Test
    void findHorseSpace_Test05() {
        HorseBarn barn = createStable();
        assertEquals(-1, barn.findHorseSpace("Epona"), "no horse named Epona, expected -1");
    }

    @DisplayName("findHorseSpace: \"Duke\" is in the LAST space -> 6")
    @Test
    void findHorseSpace_Test06() {
        HorseBarn barn = createStable();
        assertEquals(6, barn.findHorseSpace("Duke"), "Duke is at the last index, 6 - check your loop bound");
    }

    @DisplayName("findHorseSpace: names are case-sensitive, \"patches\" does not match \"Patches\" -> -1")
    @Test
    void findHorseSpace_Test07() {
        HorseBarn barn = createStable();
        assertEquals(-1, barn.findHorseSpace("patches"), "\"patches\" (lowercase) is not \"Patches\"");
    }

    @DisplayName("findHorseSpace: \"Patches\" -> 5")
    @Test
    void findHorseSpace_Test08() {
        HorseBarn barn = createStable();
        assertEquals(5, barn.findHorseSpace("Patches"), "Patches is at index 5");
    }

    @DisplayName("findHorseSpace: search a barn that is completely empty -> -1 (no NullPointerException)")
    @Test
    void findHorseSpace_Test09() {
        HorseBarn barn = new HorseBarn(new Horse[4]);
        assertEquals(-1, barn.findHorseSpace("Trigger"), "empty barn: every space is null, expected -1");
    }

    @DisplayName("findHorseSpace: the barn must not be modified by a search")
    @Test
    void findHorseSpace_Test10() {
        HorseBarn barn = createStable();
        barn.findHorseSpace("Lady");
        barn.findHorseSpace("Nobody");
        Horse[] spaces = barn.getSpaces();
        assertEquals(7, spaces.length, "array length should be unchanged");
        assertNull(spaces[1], "index 1 should still be empty");
        assertNull(spaces[4], "index 4 should still be empty");
        assertEquals("Trigger", spaces[0].getName(), "index 0 should still be Trigger");
        assertEquals("Duke", spaces[6].getName(), "index 6 should still be Duke");
    }
}
