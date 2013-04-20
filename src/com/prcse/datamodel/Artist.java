package com.prcse.datamodel;

import java.util.ArrayList;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

public class Artist implements ISubject, IObserver{

	ArrayList<Genre> genres;
	String name;
	String description;
        
        //added by phill
        long artistID;
        
        public void setArtistID(long artistID) 
	{
		this.artistID = artistID;
		 
	}
	
	public long getArtistID() {
		return artistID;
	}
        //end added by phill
	
	private ArrayList<IObserver> observers = null;
	
	public Artist(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String groupName) 
	{
		this.name = groupName;
		notifyObservers();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public void addGenre(Genre genre)
    {
    	if (this.genres == null)
    	{
    		this.genres = new ArrayList<Genre>();
    	}
    	genre.addArtist(this);
    	this.genres.add(genre);
    }
    
    public void removeGenre(Genre genre)
    {
    	genre.removeArtist(this);
    	this.genres.remove(genre);
    }
	
	@Override
	public String toString()
	{
		return name;
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
