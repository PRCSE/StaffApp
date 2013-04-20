package com.prcse.datamodel;

public class Favourite extends PersistantObject {
	private Long customerId;
	private Long artistId;
	private Long venueId;
	private Long genreId;
	private Long eventId;
	
	// default constructor
	public Favourite() {
		super();
	}
	
	public Favourite(Long id, Long customerId, Long artistId, Long venueId, Long genreId, Long eventId) {
		super();
		this.customerId = customerId;
		this.artistId = artistId;
		this.venueId = venueId;
		this.genreId = genreId;
		this.eventId = eventId;
		
		this.setId(id);
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getArtistId() {
		return artistId;
	}
	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}
	public Long getVenueId() {
		return venueId;
	}
	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	public Long getGenreId() {
		return genreId;
	}
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Favourite [customerId=" + customerId + ", artistId=" + artistId
				+ ", venueId=" + venueId + ", genreId=" + genreId
				+ ", eventId=" + eventId + "]";
	}
}
