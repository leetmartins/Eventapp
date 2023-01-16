package com.eventapp.models;

import javax.validation.constraints.NotEmpty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Guests {
	@Id
	@NotEmpty
	private String rg;
	
	@NotEmpty
	private String guestName;
	
	@ManyToOne
	private Event event;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
