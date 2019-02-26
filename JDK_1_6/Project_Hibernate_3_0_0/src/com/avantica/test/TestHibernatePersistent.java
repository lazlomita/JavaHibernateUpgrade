package com.avantica.test;

import java.util.*;

import org.hibernate.*;

import com.avantica.hibernate.HibernateHelper;
import com.avantica.model.Message;
 
public class TestHibernatePersistent {
	
    public static void main(String[] args) {
        // First unit of work
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Message message = new Message("Hello World");
        //Long msgId = (Long) session.save(message);
 
        Message message2 = new Message("next message");
        message2.setNextMessage(message);
        session.save(message2);
        tx.commit();
        session.close();
 
        // Second unit of work
        Session newSession = 
            HibernateHelper.getSessionFactory().openSession();
        Transaction newTransaction = newSession.beginTransaction();

        @SuppressWarnings("rawtypes")
		List messages =
            newSession.createQuery("from Message m order by m.text asc").list();
        System.out.println( messages.size() + " message(s) found:" );
 
        for ( @SuppressWarnings("rawtypes")
		Iterator iter = messages.iterator(); iter.hasNext(); ) {
            Message loadedMsg = (Message) iter.next();
            System.out.println( loadedMsg.getText() );
        }
        newTransaction.commit();
        newSession.close();
 
        // Shutting down the application
        HibernateHelper.shutdown();
    }
}
