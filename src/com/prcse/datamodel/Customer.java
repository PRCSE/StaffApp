package com.prcse.datamodel;

import java.util.ArrayList;
import java.util.Date;

import com.prcse.observer.IObserver;
import com.prcse.observer.ISubject;

/*
 * DEV NOTES: It may be better to use a decorator for customer
 */
public class Customer implements ISubject, IObserver{
    
    private Account account;
	private ArrayList<Booking> bookings = null;
    private String title;
    private String forename;
    private String surname;
    private String telephone;
    private String mobile;
    private String addr1;
    private String addr2;
    private String town;
    private String county;
    private Date created;
    
   //added by phill
    private String country;
    private String postcode;
    private long customerID;
    
    
       public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
       
        
    
     public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
        
         public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
           public void setCreated(Date created) {
		this.created = created;
	}
    //end added by phill
    
    private ArrayList<IObserver> observers = null;
    
    public Customer()
    {
        super();
        this.title = null;
        this.forename = "";
        this.surname = "";
        this.telephone = "";
        this.mobile = "";
        this.addr1 = "";
        this.addr2 = "";
        this.town = "";
        this.county = "";
        this.created = null;
    }

    public Customer(String email, String password, String title, String forename, String surname, String telephone, String mobile, String addr1, String addr2, String town, String county) 
    {
        this.account = new Account(email, password);
        this.title = title;
    	this.forename = forename;
        this.surname = surname;
        this.telephone = telephone;
        this.mobile = mobile;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.town = town;
        this.county = county;
        this.bookings = new ArrayList<Booking>();
        this.created = new Date();
    }
    
    @Override
    public String toString()
    {
    	return 		"\n username: " + this.account.getEmail()
    			+	"\n password: " + this.account.getPassword()
    			+	"\n forename: " + this.getForename()
    			+	"\n surname: " 	+ this.getSurname();
    }
    
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) 
    {
        this.bookings = bookings;
        //commented by phill
//        ((ISubject) this.bookings).registerObserver(this);
        notifyObservers();
    }
    
    
    public Account getAccount() {
		return account;
	}

	public String getFullName()
    {
    	return title + " " + forename + " " + surname;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForename() 
    {
        return forename;
    }

    public void setForename(String forename) 
    {
        this.forename = forename;
        notifyObservers();
    }

    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
        notifyObservers();
    }

    public String getTelephone() 
    {
        return telephone;
    }

    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
        notifyObservers();
    }

    public String getMobile() 
    {
        return mobile;
    }

    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
        notifyObservers();
    }

    public String getAddr1() 
    {
        return addr1;
    }

    public void setAddr1(String addr1) 
    {
        this.addr1 = addr1;
        notifyObservers();
    }

    public String getAddr2() 
    {
        return addr2;
    }

    public void setAddr2(String addr2) 
    {
        this.addr2 = addr2;
        notifyObservers();
    }

    public String getTown() 
    {
        return town;
    }

    public void setTown(String town) 
    {
        this.town = town;
        notifyObservers();
    }

    public String getCounty() 
    {
        return county;
    }

    public void setCounty(String county) 
    {
        this.county = county;
        notifyObservers();
    }
    

    public Date getCreated() {
		return created;
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
