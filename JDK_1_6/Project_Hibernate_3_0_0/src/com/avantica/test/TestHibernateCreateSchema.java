package com.avantica.test;

import org.hibernate.tool.hbm2ddl.*;
import org.hibernate.cfg.*;

public class TestHibernateCreateSchema {
 
    public static void main ( String [] args ) { 
        createSchema();
    }
 
    public static void createSchema() {
        Configuration cfg = new Configuration().configure();
        SchemaExport export = new SchemaExport(cfg); 
        export.create(false,true);
    }
    
    public static void updateSchema() {
        Configuration cfg = new Configuration().configure();
        SchemaUpdate update = new SchemaUpdate(cfg); 
        update.execute(false,true);
    }
}
