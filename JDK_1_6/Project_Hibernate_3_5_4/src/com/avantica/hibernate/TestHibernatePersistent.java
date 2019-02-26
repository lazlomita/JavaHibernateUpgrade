package com.avantica.hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import com.avantica.model.GameSummary;

public class TestHibernatePersistent {
	
	public static void main(String args[]) {
		 AnnotationConfiguration config = new AnnotationConfiguration();
		 config.addAnnotatedClass(GameSummary.class);
		 config.configure();
		 new SchemaExport(config).create(true, true);
	}

}
