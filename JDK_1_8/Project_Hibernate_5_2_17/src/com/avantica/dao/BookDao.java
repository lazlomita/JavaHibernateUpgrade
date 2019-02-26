package com.avantica.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avantica.hibernate.HibernateHelper;
import com.avantica.model.Book;

public class BookDao implements DaoInterface<Book, String> {

	private Session currentSession;
	
	private Transaction currentTransaction;

	public BookDao() {
	}

	public Session openCurrentSession() {
		currentSession = HibernateHelper.getSessionFactoryHbm().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = HibernateHelper.getSessionFactoryHbm().openSession();
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

	public void persist(Book entity) {
		getCurrentSession().save(entity);
	}

	public void update(Book entity) {
		getCurrentSession().update(entity);
	}

	public Book findById(String id) {
		Book book = (Book) getCurrentSession().get(Book.class, id);
		return book; 
	}

	public void delete(Book entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		List<Book> books = (List<Book>) getCurrentSession().createQuery("from Book").list();
		return books;
	}

	public void deleteAll() {
		List<Book> entityList = findAll();
		for (Book entity : entityList) {
			delete(entity);
		}
	}
}
