package com.avantica.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.avantica.hibernate.HibernateConstants;
import com.avantica.model.GameSummary;

public class GameSummaryDao implements DaoInterface<GameSummary, Long>  {
	
	private Session currentSession;
	
	private Transaction currentTransaction;
	
	public GameSummaryDao() {
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		
		/* Initialize the Hibernate Environment */
		Configuration config = new Configuration();
		config.addAnnotatedClass(GameSummary.class);
		// Add more annotation classes of the model
		config.configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory sessionFactory = config.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	
	

	@Override
	public void persist(GameSummary entity) {
		getCurrentSession().save(entity);
	}

	@Override
	public void update(GameSummary entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public GameSummary findById(Long id) {
		GameSummary gameSummary = (GameSummary) getCurrentSession().get(GameSummary.class, id);
		return gameSummary;
	}

	@Override
	public void delete(GameSummary entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameSummary> findAll() {
		List<GameSummary> gameSummaries = (List<GameSummary>) getCurrentSession().createQuery("from GameSummary").list();
		return gameSummaries;
	}

	@Override
	public void deleteAll() {
		List<GameSummary> entityList = findAll();
		for (GameSummary entity : entityList) {
			delete(entity);
		}
	}

}
