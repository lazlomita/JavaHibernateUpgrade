package com.avantica.test;

import java.text.MessageFormat;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avantica.hibernate.HibernateHelper;
import com.avantica.model.Book;

public class TestHibernatePersistHbm {
	
	public static void main(String[] args) {
		
		HibernateHelper.cleanSchemaHbm();
		
		final SessionFactory sessionFactory = HibernateHelper.getSessionFactoryHbm();
		final Session session = sessionFactory.openSession();
		
		final Book book1 = new Book("1788624068 ", "Software Architect's Handbook", "Joseph Ingeno");
		final Book book2 = new Book("1484240774 ", "Java Design Patterns", "Vaskaran Sarcar");
		final Book book3 = new Book("1260117383 ", "OCP Java SE 8 Programmer II Exam Guide", "Kathy Sierra");
		
		session.beginTransaction();
		session.save(book1);
		session.save(book2);
		session.save(book3);
		session.getTransaction().commit();
		
        // create Criteria
        CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder().createQuery(Book.class);
        criteriaQuery.from(Book.class);

        List<Book> books = session.createQuery(criteriaQuery).getResultList();
        session.close();

        System.out.println("\n----\n");
		System.out.println(MessageFormat.format("Storing {0} books in the database", books.size()));
		for (final Book b : books) {
			System.out.println(b);
		}
		System.out.println("\n----\n");
		
		session.close();
		sessionFactory.close();
		
		System.out.println("********** END OF TEST **********");
		System.exit(0);
	}
}