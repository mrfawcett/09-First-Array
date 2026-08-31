/** READ FIRST
 * A horse barn consists of N numbered spaces. Each space can hold at most one
 * horse. The spaces are indexed starting from 0; the index of the last space
 * is N-1. No two horses in the barn have the same name.
 *
 * The barn is stored as an array of Horse references. A null element means
 * that space is empty.
 *
 * Adapted from the 2012 AP Computer Science A free-response question
 * "HorseBarn" (Question 3).
 *
 * ---- findHorseSpace ----
 * Returns the index of the space that holds the horse with the given name,
 * or -1 if no horse in the barn has that name. Names are case-sensitive:
 * "patches" does not match "Patches".
 *
 * Assume a HorseBarn object called sweetHome has horses in the following spaces.
 * Index:       0        1         2          3        4          5          6
 * Objects: "Trigger"   null    "Silver"    "Lady"    null    "Patches"    "Duke"
 *           1340                1210        1575              1350         1410
 *
 *      Method Call                         Value Returned
 * sweetHome.findHorseSpace("Trigger")          0
 * sweetHome.findHorseSpace("Silver")           2
 * sweetHome.findHorseSpace("Coco")            -1
 *
 * ---- consolidate ----
 * Moves all the horses so that they occupy adjacent spaces starting at index 0,
 * with no empty spaces between any two horses. After the barn has been
 * consolidated the horses are in the same order they were in before, and every
 * space after the last horse is null.
 *
 * Assume a HorseBarn object called sweetHome has horses in the following spaces.
 * Index:       0        1         2          3        4          5          6
 * Objects: "Trigger"   null    "Silver"     null     null    "Patches"    "Duke"
 *           1340                1210                          1350         1410
 *
 * After sweetHome.consolidate() the barn looks like this:
 * Index:       0          1            2          3       4        5        6
 * Objects: "Trigger"   "Silver"    "Patches"   "Duke"    null     null     null
 *           1340        1210        1350        1410
 */
public class HorseBarn {
    /** The spaces in the barn. Each element holds a reference to the horse
     *  that currently occupies the space. A null value indicates an empty space.
     */
    private Horse[] spaces;

    /** PROVIDED - do not change
     * Builds a barn around an existing array. The tests use this constructor.
     */
    public HorseBarn(Horse[] stable) {
        spaces = stable;
    }

    /** PROVIDED - do not change
     * Returns the array of spaces so the tests can inspect it.
     */
    public Horse[] getSpaces() {
        return spaces;
    }

    /** COMPLETE THIS METHOD
     * Precondition: No two horses in the barn have the same name.
     * Returns the index of the space that contains the horse with the given
     * name, or -1 if no horse in the barn has that name.
     * Example: with the barn above, findHorseSpace("Silver") returns 2 and
     *          findHorseSpace("Coco") returns -1.
     * Hint: empty spaces are null - check for null BEFORE calling getName().
     */
    public int findHorseSpace(String name) {
        // Insert your code below

        return 0;
    }

    /** COMPLETE THIS METHOD
     * Consolidates the barn by moving horses so that they occupy adjacent
     * spaces starting at index 0, with no empty spaces between any two horses.
     * Postcondition: the horses are in the same order as before; every space
     *                after the last horse is null.
     * Example: [Trigger, null, Silver, null, null, Patches, Duke]
     *       -> [Trigger, Silver, Patches, Duke, null, null, null]
     * Hint: keep a "next free slot" index. Walk the array once; each time you
     *       meet a horse, put it in the next free slot and advance that index.
     *       Then set every space from that index to the end to null.
     */
    public void consolidate() {
        // Insert your code below

    }
}
