package com.prcse.observer;

/**
 * Observer interface for the <a href="http://en.wikipedia.org/wiki/Observer_pattern">
 * Observer Pattern</a> to be implemented by other classes.
 * @author James
 */
public interface IObserver {
    
    /**
     * List of actions to run once this observer is notified of an update by one
     * of its subjects.
     */
    void update();
}
