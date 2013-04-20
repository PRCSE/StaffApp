package com.prcse.datamodel;

import java.util.ArrayList;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

/*
 * use decorator pattern?
 */
public class Tour implements ISubject, IObserver{

	ArrayList<Billing> bills;
	String name;
	Artist artist;
	
        
        //added by phill
        long tourID;
        	
	public long getTourID() 
	{
		return tourID;
	}
	
	public void setTourID(long tourID) 
	{
		this.tourID= tourID;
	 
	}
        
        	public Artist getArtist() 
	{
		return artist;
	}
	
	public void setArtist(Artist artist) 
	{
		this.artist= artist;
	 
	}
        
        //added by phill
	private ArrayList<IObserver> observers = null;
	
	public Tour(String name)
	{
		bills = new ArrayList<Billing>();
		this.name = name;
	}
	
	public Tour(String name, Artist artist)
	{
		bills = new ArrayList<Billing>();
		this.name = name;
		this.artist = artist;
	}
	
	public void addBill(Billing bill)
	{
		this.bills.add(bill);
		notifyObservers();
	}
	
	public void removeBillAt(int index)
	{
		this.bills.remove(index);
		notifyObservers();
	}
	
	public void removeBill(Billing bill)
	{
		this.bills.remove(bill);
		notifyObservers();
	}
	
	public ArrayList<Billing> getBills() 
	{
		return bills;
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
