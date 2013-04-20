package com.prcse.datamodel;

public class SeatingPlan {
	
	private Venue venue;
	private String name;
        
        //added by phill
        private long seatingPlanID;
        public long getSeatingPlanID() {
		return seatingPlanID;
	}

	public void setSeatingPlanID(long seatingPlanID) {
		this.seatingPlanID = seatingPlanID;
	}
        
        //end added by phill
	
	public SeatingPlan(Venue venue, String name)
	{
		this.venue = venue;
		this.name = name;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
