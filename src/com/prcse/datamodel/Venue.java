package com.prcse.datamodel;

import java.util.ArrayList;



public class Venue extends PersistantObject {
	
	private ArrayList<SeatingPlan> seatingPlan;
	private String name;
	private double geoLat;
	private double geoLong;
	private String thumb;
	private String postcode;
        
        private String description;
        private String addr1;
        private ArrayList<Event> eventList = new ArrayList<Event>();
        
        public ArrayList<Event> getEventList(){
            return this.eventList;
        }
        
        public void setEventList(ArrayList<Event> eventList){
            this.eventList = eventList;
        }
        
       
        public String getDescription(){
            return this.description;
        }
        public void setDescription(String description){
            this.description = description;
        }
        
          public String getAddr1(){
            return this.addr1;
        }
        public void setAddr1(String addr1){
            this.addr1 = addr1;
        }
        

	public Venue(long id, String name)
	{
		this.seatingPlan = new ArrayList<SeatingPlan>();
		this.name = name;
		this.id = id;
		this.geoLat = 0;
		this.geoLong = 0;
	}
	
	public Venue(String name, double geoLat, double geoLong)
	{
		this.seatingPlan = new ArrayList<SeatingPlan>();
		this.name = name;
		this.geoLat = geoLat;
		this.geoLong = geoLong;
	}
	
	public void addSeatingPlan(SeatingPlan seatingPlan)
	{
		this.seatingPlan.add(seatingPlan);
	}
	
	public void removeSeatingPlan(SeatingPlan seatingPlan)
	{
		this.seatingPlan.remove(seatingPlan);
	}

	public ArrayList<SeatingPlan> getSeatingPlan() 
	{
		return seatingPlan;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getGeoLat() 
	{
		return geoLat;
	}

	public void setGeoLat(double geoLat) 
	{
		this.geoLat = geoLat;
	}

	public double getGeoLong() 
	{
		return geoLong;
	}

	public void setGeoLong(double geoLong) 
	{
		this.geoLong = geoLong;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "Venue [name=" + name + ", geoLat=" + geoLat + ", geoLong="
				+ geoLong + ", postcode=" + postcode + "]";
	}
}