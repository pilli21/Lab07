package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class PowerOutage {
	
	
	public int id;
	public int nercId;
	public LocalDateTime date_event_began;
	public LocalDateTime date_event_finished;
	public int customers_affected;
	public int oreOutage;
	
	public PowerOutage(int id, int nercId, LocalDateTime date_event_began, LocalDateTime date_event_finished,
			int customers_affected) {
		super();
		this.id = id;
		this.nercId = nercId;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.customers_affected = customers_affected;
		this.oreOutage = (int) (Duration.between(date_event_began, date_event_finished).getSeconds())/3600;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}

	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}

	public int getOreOutage() {
		return oreOutage;
	}

	public void setOreOutage(int oreOutage) {
		this.oreOutage = oreOutage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return date_event_began.getYear() +" "+date_event_began+" "+ date_event_finished +" "+ oreOutage +" "+ customers_affected;
	}
	
	

}
