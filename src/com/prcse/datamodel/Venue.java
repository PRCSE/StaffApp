package com.prcse.datamodel;

import java.util.ArrayList;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;


public class Venue implements ISubject, IObserver{
	
	//ArrayList<SeatingPlan> seatingPlan;
	ArrayList<Event> events;
	String name;
	double geoLat;
	double geoLong;
        
        //added by phill
        String description;
        String addr1;
        String postcode;
        long venueID;
        
          public void setVenueID(long venueID){
            this.venueID = venueID;
        }
        
        public long getVenueID(){
            return this.venueID;
        }
        
        public void setDescription(String description){
            this.description = description;
        }
        
        public String getDescription(){
            return this.description;
        }
        
          public void setAddr1(String addr1){
            this.addr1 = addr1;
        }
        
        public String getAddr1(){
            return this.addr1;
        }
        
             public void setPostcode(String postcode){
            this.postcode = postcode;
        }
        
        public String getPostcode(){
            return this.postcode;
        }
        //end added by phill
	
	private ArrayList<IObserver> observers = null;

	public Venue(String name)
	{
		this.events = new ArrayList<Event>();
		this.name = name;
		this.geoLat = 0;
		this.geoLong = 0;
	}
	
	public Venue(String name, double geoLat, double geoLong)
	{
		this.events = new ArrayList<Event>();
		this.name = name;
		this.geoLat = geoLat;
		this.geoLong = geoLong;
	}
	
	public void addEvent(Event newEvent)
	{
		if (this.events == null)
		{
			this.events = new ArrayList<Event>();
		}
		this.events.add(newEvent);
		
		newEvent.registerObserver(this);
		this.notifyObservers();
	}
	
	public void removeEventAt(int index)
	{
		this.events.remove(index);
		this.notifyObservers();
	}
	
	public void removeEvent(Event event)
	{
		this.events.remove(event);
		this.notifyObservers();
	}

	public ArrayList<Event> getEvents() 
	{
		return events;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
		this.notifyObservers();
	}

	public double getGeoLat() 
	{
		return geoLat;
	}

	public void setGeoLat(double geoLat) 
	{
		this.geoLat = geoLat;
		this.notifyObservers();
	}

	public double getGeoLong() 
	{
		return geoLong;
	}

	public void setGeoLong(double geoLong) 
	{
		this.geoLong = geoLong;
		this.notifyObservers();
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