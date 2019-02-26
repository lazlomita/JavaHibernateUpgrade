package com.avantica.test;
 
import com.avantica.hibernate.HibernateHelper;
 
// Hibernate 5.
public class TestSchemaGenerator {
	
	public static void main(String[] args) {
		
		System.out.println("Drop Database...");
		// Drop Database
		HibernateHelper.dropTablesOnDatabase();
		
		System.out.println("Create Database...");
		// Create tables
		HibernateHelper.createTablesOnDatabase();		

		System.out.println("********** END OF TEST **********");		
	}    
}
