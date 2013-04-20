package com.prcse.observer;

/**
 * Subject interface for the <a href="http://en.wikipedia.org/wiki/Observer_pattern">
 * Observer Pattern</a> to be implemented by other classes.
 * @author James Stidard
 */
public interface ISubject {
    
    /**
     * Adds an observer to the an instance, that will be notified when the
     * instances state changes.
     * @param o IObserver an instance that registers itself as an observer.
     * @return Boolean that indicates if the registration was successful.
     */
    Boolean registerObserver(IObserver o);
    /**
     * Removes an observer from the an instance, so it is no longer
     * notified of changes to the instances state.
     * @param o IObserver an instance that wishes to remove itself as an observer.
     * @return Boolean that indicates if the removal as an observer was successful.
     */
    Boolean removeObserver(IObserver o);
    /**
     * Goes through all registered observers and notifies them of a state change.
     * This is done by calling their .update() method.
     */
    void notifyObservers();
}
