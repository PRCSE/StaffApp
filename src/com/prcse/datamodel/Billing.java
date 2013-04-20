package com.prcse.datamodel;

import java.util.ArrayList;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

/*
 * Dev Note: Billing order (lineup_order) number for each artist in an event need to be exclusive?
 */
public class Billing implements ISubject, IObserver{
	
	Artist billing;
	int lineupOrder;
        
       
	
	private ArrayList<IObserver> observers = null;
	
	public Billing(Artist artist)
	{
		this.billing = artist;
	}
	
	public Artist getBilling() {
		return billing;
	}

	public void setBilling(Artist artist) {
		this.billing = artist;
	}

	public int getLineupOrder() {
		return lineupOrder;
	}

	public void setLineupOrder(int lineupOrder) {
		this.lineupOrder = lineupOrder;
	}

	@Override
	public void update() 
	{
		// notify all observers (domino effect caused)
		this.notifyObservers();
	}

	@Override
	public Boolean registerObserver(IObserver o) 
	{
		Boolean blnAdded = false;
        // Check observer exists
        if (o != null) 
        {
            // check if the ArrayList of observers has been initilised
            if (this.observers == null) 
            {
                this.observers = new ArrayList<>();
            }
            // Add the observer to the list and true the blnAdded variable
            blnAdded = this.observers.add(o);
        }
        return blnAdded;
	}

	@Override
	public Boolean removeObserver(IObserver o) 
	{
		Boolean blnRemoved = false;
        // Check observer exists
        if (o != null) 
        {
            // check the array has been initilised
            if (this.observers != null) 
            {
                // If it has try to remove observer passed
                blnRemoved = this.observers.remove(o);
            }
        }
        return blnRemoved;
	}

	@Override
	public void notifyObservers() 
	{
		// check the list of observables is valid
        if (this.observers != null && this.observers.size() > 0) 
        {
            // loop through each observer in ArrayList
            for (IObserver currentObserver : this.observers) 
            {
                // call update for current observer
                currentObserver.update();
            }
        }
	}
	
	
}
