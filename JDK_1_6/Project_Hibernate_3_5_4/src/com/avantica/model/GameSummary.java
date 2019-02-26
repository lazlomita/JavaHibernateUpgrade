package com.avantica.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.tool.hbm2ddl.SchemaExport;

@Entity
public class GameSummary {
	
	@Id
	@GeneratedValue
	private Long id;
	private String result, clientGesture, serverGesture;
	private Date date;
	
	public static void main(String args[]) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(GameSummary.class);
		config.configure();
		
		//new SchemaExport(config).create(true, true);
		
		GameSummary gs = new GameSummary();
		gs.setClientGesture("rock");
		gs.setServerGesture("paper");
		gs.setResult("win");
		gs.setDate(new java.util.Date());
		
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(gs);
		session.getTransaction().commit();
	}
	
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