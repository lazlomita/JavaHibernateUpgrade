package com.avantica.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avantica.hibernate.HibernateHelper;
import com.avantica.model.GameSummary;

public class TestHibernatePersist {
	
	public static void main(String args[]) {
		
		HibernateHelper.cleanSchemaAnnotation();

		GameSummary gameSummary1 = new GameSummary();
		gameSummary1.setClientGesture("rock");
		gameSummary1.setServerGesture("rock");
		gameSummary1.setResult("tie");
		gameSummary1.setDate(new java.util.Date());

		GameSummary gameSummary2 = new GameSummary();
		gameSummary2.setClientGesture("paper");
		gameSummary2.setServerGesture("paper");
		gameSummary2.setResult("tie");
		gameSummary2.setDate(new java.util.Date());

		GameSummary gameSummary3 = new GameSummary();
		gameSummary3.setClientGesture("scissors");
		gameSummary3.setServerGesture("scissors");
		gameSummary3.setResult("tie");
		gameSummary3.setDate(new java.util.Date());
		
		SessionFactory sessionFactory = HibernateHelper.getSessionFactoryAnnotation();		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(gameSummary1);
		session.save(gameSummary2);
		session.save(gameSummary3);
		session.getTransaction().commit();
		
		sessionFactory.close();

		System.out.println("********** END OF TEST **********");
		System.exit(0);
	}
}