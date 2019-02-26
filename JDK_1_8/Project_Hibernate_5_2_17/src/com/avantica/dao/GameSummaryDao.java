package com.avantica.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avantica.hibernate.HibernateHelper;
import com.avantica.model.GameSummary;

public class GameSummaryDao implements DaoInterface<GameSummary, Long>  {
	
	private Session currentSession;
	
	private Transaction currentTransaction;
	
	public GameSummaryDao() {
	}
	
	public Session openCurrentSession() {
		currentSession = HibernateHelper.getSessionFactoryAnnotation().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = HibernateHelper.getSessionFactoryAnnotation().openSession();
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
