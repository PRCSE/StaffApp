package com.prcse.datamodel;

import java.util.ArrayList;
import java.util.Calendar;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

public class Event implements ISubject, IObserver{
	
	ArrayList<Billing> billings;
	String name;
	Calendar startTime;
	Calendar endTime;
        
        //added by phill
        private Venue venue;
        private long eventID;
        
           public long getEventID() 
	{
		return eventID;
	}

	public void setEventID(long eventID) 
	{
		this.eventID = eventID;
		 
	}
        public Venue getVenue() 
	{
		return venue;
	}

	public void setVenue(Venue venue) 
	{
		this.venue = venue;
		 
	}
        //end added by phill
	
	private ArrayList<IObserver> observers = null;
	
	public Event(String name, Calendar startTime, Calendar endTime)
	{
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Event(ArrayList<Billing> billings, String name, Calendar startTime, Calendar endTime)
	{
		this.billings = billings;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ArrayList<Billing> getBillings() 
	{
		return billings;
	}

	public void setBillings(ArrayList<Billing> billings) 
	{
		this.billings = billings;
		notifyObservers();
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
		notifyObservers();
	}

	public Calendar getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(Calendar startTime) 
	{
		this.startTime = startTime;
		notifyObservers();
	}

	public Calendar getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(Calendar endTime) 
	{
		this.endTime = endTime;
		notifyObservers();
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
