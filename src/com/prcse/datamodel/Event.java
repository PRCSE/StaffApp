package com.prcse.datamodel;

import java.util.ArrayList;
import java.util.Date;

public class Event extends PersistantObject {
	
	private ArrayList<Billing> billings;
	private String name;
	private Date startTime;
	private Date endTime;
	private SeatingPlan seatingPlan;
        
        private Venue venue;
        
        public Venue getVenue(){
            return this.venue;
        }
        
        public void setVenue(Venue venue){
             this.venue = venue ;
        }
	
	
	public Event(long id, String name, Date startTime, Date endTime)
	{
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.billings = new ArrayList<Billing>();
		this.seatingPlan = null;
	}

	public ArrayList<Billing> getBillings() 
	{
		return billings;
	}

	public void setBillings(ArrayList<Billing> billings) 
	{
		this.billings = billings;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Date getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public SeatingPlan getSeatingPlan() {
		return seatingPlan;
	}

	public void setSeatingPlan(SeatingPlan seatingPlan) {
		this.seatingPlan = seatingPlan;
	}
	
    public void addBilling(Billing billing) {
    	this.billings.add(billing);
    }
    
    public void removeBilling(Billing billing) {
    	this.billings.remove(billing);
    }

	@Override
	public String toString() {
		return "Event [name=" + name + ", startTime=" + startTime + "]";
	}
}
