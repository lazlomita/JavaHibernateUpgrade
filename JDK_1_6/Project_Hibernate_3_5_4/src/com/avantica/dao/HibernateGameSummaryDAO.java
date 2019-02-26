package com.avantica.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.avantica.dao.GenericHibernateDAO;
import com.avantica.model.GameSummary;

public class HibernateGameSummaryDAO implements GenericHibernateDAO <GameSummary, Long>{
	
	private SessionFactory sessionFactory = null;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public HibernateGameSummaryDAO(){}
	
	public HibernateGameSummaryDAO(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query queryResult = session.createQuery("from GameSummary");
		return queryResult.list();
	}
	
	@Override
	public GameSummary findByPrimaryKey(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Object o = session.load(GameSummary.class, id);
		return (GameSummary)o;
	}
	
	@Override
	public GameSummary save(GameSummary entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
		return entity;
	}
	
	@Override
	public void delete(GameSummary entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

}