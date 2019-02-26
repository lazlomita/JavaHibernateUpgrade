package com.avantica.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.avantica.model.GameSummary;

public class HibernateHelper {
	
	public static final SessionFactory getSessionFactory(){
		/* Initialize the Hibernate Environment */
		Configuration config = new Configuration();
		config.addAnnotatedClass(GameSummary.class);
		// Add more annotation classes of the model
		config.configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory sessionFactory = config.buildSessionFactory(builder.build());
		return sessionFactory; 		
	}
	
	public static final SessionFactory getSessionFactoryHbm(){
		/* Initialize the Hibernate Environment based on hbm mapping files*/
	    Configuration configuration = new Configuration().configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	    SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory; 		
	}
	
	public static void cleanSchema(){
		/* Initialize the Hibernate Environment */
		Configuration config = new Configuration();
		config.addAnnotatedClass(GameSummary.class);
		// Add more annotation classes of the model
		config.configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
		new SchemaExport(config).create(true, true);		
	}

    public static void updateSchema() {
		/* Initialize the Hibernate Environment */
		Configuration config = new Configuration();
		config.addAnnotatedClass(GameSummary.class);
		// Add more annotation classes of the model
		config.configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
		SchemaUpdate update = new SchemaUpdate(config); 
        update.execute(true,true);
    }
	
	public static void cleanSchemaHbm(){
		/* Initialize the Hibernate Environment based on hbm mapping files*/
        Configuration config = new Configuration().configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
        SchemaExport export = new SchemaExport(config); 
        export.create(true,true);		
	}

    public static void updateSchemaHbm() {
    	/* Initialize the Hibernate Environment based on hbm mapping files*/
        Configuration config = new Configuration().configure(HibernateConstants.HIBERNATE_CONFIG_FILE);
        SchemaUpdate update = new SchemaUpdate(config); 
        update.execute(true,true);
    }
}
