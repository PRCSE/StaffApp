package com.prcse.datamodel;


import java.util.ArrayList;
import java.util.Date;
import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

/* Dev Notes
 * 
 * created, confirmed, cancelRequest, cancelled, cancledConfirmed
 * 
 */

public class Booking implements ISubject, IObserver {
    
    private ArrayList<SeatingArea> seats;
	private Event event = null;
    private Date created;
    private Date confirmed;
    private Date cancelRequest;
    private Date cancelled;
    private Date cancelConfirmed;
    
    private ArrayList<IObserver> observers = null;

    public Booking(Event event)
    {
    	this.event = event;
    	this.created = new Date();
    	this.confirmed = null;
        this.cancelRequest = null;
        this.cancelled = null;
        this.cancelConfirmed = null;
    }
    
    public Booking(ArrayList<SeatingArea> seats, Event event)
    {
    	this.seats = seats;
    	this.event = event;
    }
    
    public void confirm()
    {
    	this.confirmed = new Date();
    }
    
    public void requestCancelation()
    {
    	this.cancelRequest = new Date();
    }
    
    public void cancel()
    {
    	this.cancelled = new Date();
    }
    
    public void confirmCancelation()
    {
    	this.cancelConfirmed = new Date();
    }
    
    public void addSeat(SeatingArea seat)
    {
    	seat.addBooking(this);
    	this.seats.add(seat);
    }
    
    public void removeSeat(SeatingArea seat)
    {
    	seat.removeBooking(this);
    	this.seats.remove(seat);
    }
    
	public ArrayList<SeatingArea> getSeats() 
	{
		return seats;
	}

	public void setSeats(ArrayList<SeatingArea> seats) 
	{
		this.seats = seats;
	}

	public Event getEvent() 
	{
		return event;
	}

	public void setEvent(Event event) 
	{
		this.event = event;
		notifyObservers();
	}

	public Date getCreated() 
	{
		return created;
	}

	public void setCreated(Date created) 
	{
		this.created = created;
		notifyObservers();
	}

	public Date getConfirmed() 
	{
		return confirmed;
	}

	public void setConfirmed(Date confirmed) 
	{
		this.confirmed = confirmed;
		notifyObservers();
	}

	public Date getCancelRequest() 
	{
		return cancelRequest;
	}

	public void setCancelRequest(Date cancelRequest) 
	{
		this.cancelRequest = cancelRequest;
		notifyObservers();
	}

	public Date getCancelled() 
	{
		return cancelled;
	}

	public void setCancelled(Date cancelled) 
	{
		this.cancelled = cancelled;
		notifyObservers();
	}

	public Date getCancelConfirmed() 
	{
		return cancelConfirmed;
	}

	public void setCancelConfirmed(Date cancelConfirmed) 
	{
		this.cancelConfirmed = cancelConfirmed;
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
