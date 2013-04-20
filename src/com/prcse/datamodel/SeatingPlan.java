package com.prcse.datamodel;

import java.util.ArrayList;


public class SeatingPlan extends PersistantObject {
	
	private Venue venue;
	private String name;
	private ArrayList<Event> events;
	
	public SeatingPlan(long id, String name, Venue venue)
	{
		this.venue = venue;
		this.name = name;
		events = new ArrayList<Event>();
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

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
	public void removeEvent(Event event) {
		this.events.remove(event);
	}

	@Override
	public String toString() {
		return "SeatingPlan [venue=" + venue + ", name=" + name + "]";
	}
}
