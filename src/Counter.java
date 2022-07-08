//Maayan Bahar 315112672

/**
 * A class for counting things.
 */
public class Counter {
    private int count;

    /**
     * @param count The beginning value of count.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * @param number A number to be added to the current count.
     */
    void increase(int number) {
        count += number;
    }

    /**
     * @param number The number to be subtracted from the current count.
     */
    void decrease(int number) {
        count -= number;
    }

    /**
     * @return Current count.
     */
    int getValue() {
        return count;
    }
}
