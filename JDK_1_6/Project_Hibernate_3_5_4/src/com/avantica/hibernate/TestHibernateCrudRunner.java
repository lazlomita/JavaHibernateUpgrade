package com.avantica.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.AnnotationConfiguration;

import com.avantica.model.GameSummary;

public class TestHibernateCrudRunner {
	
	public static void main(String[] args) {
		
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(GameSummary.class);
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Long entityId = null;
		{
			GameSummary gameSummary = new GameSummary();
			gameSummary.setResult("win");
			System.out.println(gameSummary);
			TestHibernateCrudRunner.save(sessionFactory, gameSummary);
			entityId = gameSummary.getId();
			System.out.println(gameSummary);
		}
		
		GameSummary gameSummary = TestHibernateCrudRunner.findByPrimaryKey(sessionFactory, entityId);
		gameSummary.setResult("tie");
		
		TestHibernateCrudRunner.update(sessionFactory, gameSummary);
		TestHibernateCrudRunner.findAll(sessionFactory);
		TestHibernateCrudRunner.delete(sessionFactory, gameSummary);
		TestHibernateCrudRunner.findAll(sessionFactory);
	}
	
	public static void save(SessionFactory sessionFactory, GameSummary gs) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(gs);
		session.getTransaction().commit();
	}
	
	public static void update(SessionFactory sessionFactory, GameSummary gs) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(gs);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("rawtypes")
	public static List findAll(SessionFactory sessionFactory) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from GameSummary");
		List allSummaries = queryResult.list();
		
		for (int i=0;i<allSummaries.size();i++) {
			GameSummary gs = (GameSummary) allSummaries.get(i);
			System.out.println(gs.toString());
		}
		
		session.getTransaction().commit();
		return allSummaries;
	}
	
	public static GameSummary findByPrimaryKey(SessionFactory sessionFactory, Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		GameSummary gs = (GameSummary) session.get(GameSummary.class, id);
		session.getTransaction().commit();
		return gs;
	}
	
	public static void delete(SessionFactory sessionFactory, GameSummary gs) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(gs);
		session.getTransaction().commit();
	}
}