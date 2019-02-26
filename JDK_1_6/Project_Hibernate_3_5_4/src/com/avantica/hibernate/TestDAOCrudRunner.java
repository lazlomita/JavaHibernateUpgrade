package com.avantica.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import com.avantica.dao.GenericHibernateDAO;
import com.avantica.dao.HibernateGameSummaryDAO;
import com.avantica.model.GameSummary;

public class TestDAOCrudRunner {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		/* Initialize the Hibernate Environment */
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(GameSummary.class);
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		/* Create the DAO, passing in the SessionFactory */
		@SuppressWarnings("rawtypes")
		GenericHibernateDAO dao = new HibernateGameSummaryDAO(sessionFactory);
		
		/* Create a record and keep track of its assigned id */
		Long id = null;
		{
			GameSummary gs = new GameSummary();
			gs.setResult("tie");
			System.out.println(gs);
			sessionFactory.getCurrentSession().beginTransaction();
			dao.save(gs);
			id = gs.getId();
			sessionFactory.getCurrentSession().getTransaction().commit();
			System.out.println(gs);
		}
		
		/* Find and update the created record that has gone out of scope */
		sessionFactory.getCurrentSession().beginTransaction();
		GameSummary gs = (GameSummary) dao.findByPrimaryKey(id);
		gs.setResult("win");
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
}