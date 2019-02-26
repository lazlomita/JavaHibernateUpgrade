package com.avantica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameSummary {
	
	@Id
	@GeneratedValue
	private Long id;
	private String result, clientGesture, serverGesture;
	private Date date;
	
	public String getClientGesture() { return clientGesture; }
	public void setClientGesture(String clientGesture) {
		this.clientGesture = clientGesture;
	}
	
	public String getServerGesture() { return serverGesture; }
	public void setServerGesture(String serverGesture) {
		this.serverGesture = serverGesture;
	}
	
	public Date getDate() { return date; }
	public void setDate(Date date) { 
		this.date = date;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getResult() { return result; }
	public void setResult(String result) {
		this.result = result;
	}
	
	public String toString() {
		return "id: " + id + " result: " + result ;
	}
}
