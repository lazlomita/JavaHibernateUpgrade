package com.avantica.hibernate;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import com.avantica.model.GameSummary;

public class HibernateHelper {
	
	private static SessionFactory sessionFactory;
	private static Metadata metadata;
	
	private static SchemaExport getSchemaExport() {
		setMetadataEntities();
		// Command-line tool for exporting (create and/or drop) a database schema. 
		// The export can be sent directly to the database, written to script or both.
		SchemaExport export = new SchemaExport();
		// Script file.
		File outputFile = new File(HibernateConstants.HIBERNATE_SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();
		System.out.println("Export file: " + outputFilePath);
		
		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);
		
		// No Stop if Error
		export.setHaltOnError(false);
		return export;
	}
	
	private static void setMetadataHbmEntities() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(HibernateConstants.HIBERNATE_CONFIG_FILE) // configures settings from hibernate.cfg.xml
                .build();        
        MetadataSources sources = new MetadataSources( registry );
        // Adds the named hbm.xml resource as a source: which performs the
        // classpath lookup and parses the XML
        sources.addResource( "com/avantica/mapping/Book.hbm.xml" );
		// Create Metadata
		metadata = sources.getMetadataBuilder().build();
	}
	
	private static void setMetadataAnnotationEntities() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(HibernateConstants.HIBERNATE_CONFIG_FILE) // configures settings from hibernate.cfg.xml
                .build();        
        MetadataSources sources = new MetadataSources( registry );
    	// add a class using JPA/Hibernate annotations for mapping
        sources.addAnnotatedClass( GameSummary.class );
		// Create Metadata
		metadata = sources.getMetadataBuilder().build();		
	}
	
	private static void setMetadataEntities() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(HibernateConstants.HIBERNATE_CONFIG_FILE) // configures settings from hibernate.cfg.xml
                .build();
        
        MetadataSources sources = new MetadataSources( registry );
        // alternatively, we can build the MetadataSources without passing
        // a service registry, in which case it will build a default
        // BootstrapServiceRegistry to use
        // MetadataSources sources = new MetadataSources();

    	// add a class using JPA/Hibernate annotations for mapping
        sources.addAnnotatedClass( GameSummary.class );

        // Adds the named hbm.xml resource as a source: which performs the
        // classpath lookup and parses the XML
        sources.addResource( "com/avantica/mapping/Book.hbm.xml" );

		// Create Metadata
		metadata = sources.getMetadataBuilder().build();		
	}
	
	public static SessionFactory getSessionFactoryAnnotation() {		
		setMetadataAnnotationEntities();
		// Create SessionFactory
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactoryHbm() {
		setMetadataHbmEntities();
		// Create SessionFactory
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		return sessionFactory;
	}

	public static void createTablesOnDatabaseHbm() {
        SchemaExport schemaExport = getSchemaExport();  
		setMetadataHbmEntities();      
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);        
        schemaExport.create(targetTypes, metadata);
	}

	public static void dropTablesOnDatabaseHbm() {
        SchemaExport schemaExport = getSchemaExport();
		setMetadataHbmEntities();        
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);        
        schemaExport.drop(targetTypes, metadata);
	}
	
	public static void createTablesOnDatabaseAnnotation() {
        SchemaExport schemaExport = getSchemaExport();
		setMetadataAnnotationEntities();
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);        
        schemaExport.create(targetTypes, metadata);
	}

	public static void dropTablesOnDatabaseAnnotation() {
        SchemaExport schemaExport = getSchemaExport();
		setMetadataAnnotationEntities();
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);        
        schemaExport.drop(targetTypes, metadata);
	}
	
	public static void createTablesOnDatabase() {
		setMetadataEntities();
        SchemaExport schemaExport = getSchemaExport();        
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        
        schemaExport.create(targetTypes, metadata);
	}

	public static void dropTablesOnDatabase() {
		setMetadataEntities();
        SchemaExport schemaExport = getSchemaExport();
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        schemaExport.drop(targetTypes, metadata);
	}

	public static void cleanSchema() {
		// Drop Database
		HibernateHelper.dropTablesOnDatabase();		
		// Create tables
		HibernateHelper.createTablesOnDatabase();			
	}

	public static void cleanSchemaHbm() {
		// Drop Database
		HibernateHelper.dropTablesOnDatabaseHbm();		
		// Create tables
		HibernateHelper.createTablesOnDatabaseHbm();			
	}

	public static void cleanSchemaAnnotation() {
		// Drop Database
		HibernateHelper.dropTablesOnDatabaseAnnotation();		
		// Create tables
		HibernateHelper.createTablesOnDatabaseAnnotation();			
	}
}
