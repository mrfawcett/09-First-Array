/** PROVIDED - do not change
 * A Horse has a name and a weight in pounds. HorseBarn stores Horse objects
 * in its array of spaces; the tests build Horse objects to fill a barn.
 */
public class Horse {
    private String name;
    private int weight;

    public Horse(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
