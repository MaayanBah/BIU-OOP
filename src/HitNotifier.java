//Maayan Bahar 315112672

/**
 * A class for notifiers.
 */
public interface HitNotifier {
    /**
     * @param hl The listener to be registered to the HitNotifier notifies.
     */
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * @param hl The listener to be removed from the HitNotifier notifies.
     */
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}