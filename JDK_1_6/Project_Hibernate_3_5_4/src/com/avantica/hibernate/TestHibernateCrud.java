package com.avantica.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.avantica.model.GameSummary;

public class TestHibernateCrud {
	
	public static void main(String args[]) {
		 
		 AnnotationConfiguration config = new AnnotationConfiguration();
		 config.addAnnotatedClass(GameSummary.class);
		 config.configure();
		 //new SchemaExport(config).create(true, true);

		 SessionFactory sessionFactory = config.buildSessionFactory();
		 Session session = sessionFactory.getCurrentSession();
		 

		 // Adding Records to the Database
		 GameSummary gs = new GameSummary();
		 gs.setResult("win");

		 session.beginTransaction();
		 session.save(gs);
		 session.getTransaction().commit();
		 
		 
		 // Retrieving and Updating Records with Hibernate and JPA
		 Session sessionUpdate = sessionFactory.getCurrentSession();
		 sessionUpdate.beginTransaction();

		 GameSummary gsUpdated = (GameSummary)sessionUpdate.get(GameSummary.class, new Long(1));
		 gsUpdated.setResult("loss");
		 
		 sessionUpdate.getTransaction().commit();
		 
		 
		 // A Hibernate Query with Multiple Results
		 Session sessionMultiple = sessionFactory.getCurrentSession();
		 sessionMultiple.beginTransaction();

		 Query queryResult = sessionMultiple.createQuery("from GameSummary");
		 @SuppressWarnings("rawtypes")
		 java.util.List allSummaries = queryResult.list();

		 for (int i=0;i<allSummaries.size();i++) {
			 GameSummary gsCurrent = (GameSummary) allSummaries.get(i);
			 System.out.println(gsCurrent.toString());
		 }

		 sessionMultiple.getTransaction().commit();
		 
		 
		 // Deleting Records with Hibernate 3.5 and JPA

		 Session sessionDelete = sessionFactory.getCurrentSession();
		 sessionDelete.beginTransaction();

		 GameSummary gsDelete = (GameSummary)sessionDelete.get(GameSummary.class, new Long(2));
		 sessionDelete.delete(gsDelete);

		 sessionDelete.getTransaction().commit();

	}

}
