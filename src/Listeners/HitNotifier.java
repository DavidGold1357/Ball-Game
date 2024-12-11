//David Goldstein 331010835
package Listeners;
/**
 * The Listeners.HitNotifier interface represents objects that can notify listeners about hit events.
 * It provides methods to add and remove HitListeners.
 */
public interface HitNotifier {
    /**
     * Removes a Listeners.HitListener from the list of listeners for hit events.
     *
     * @param hl the Listeners.HitListener to remove
     */
    void removeHitListener(HitListener hl);
    /**
     * Adds a Listeners.HitListener to the list of listeners for hit events.
     *
     * @param hl the Listeners.HitListener to add
     */
    void addHitListener(HitListener hl);
}
