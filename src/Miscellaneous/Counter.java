//David Goldstein 331010835
package Miscellaneous;
/**
 * The Util.Counter class is used for counting things.
 * It supports operations to increase, decrease, and get the current count.
 */
public class Counter {
    private int value;
    /**
     * Constructs a Util.Counter object with an initial value of 0.
     */
    public Counter() {
        value = 0;
    }

    /**
     * Gets the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.value;
    }
    /**
     * Adds the specified amount to the current count.
     *
     * @param amount the amount to add to the count
     */
    public void increase(int amount) {
        this.value += amount;
    }
    /**
     * Subtracts the specified amount from the current count.
     *
     * @param amount the amount to subtract from the count
     */
    public void decrease(int amount) {
        this.value -= amount;
    }
}
