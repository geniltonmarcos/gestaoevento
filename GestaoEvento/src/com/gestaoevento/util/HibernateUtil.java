/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestaoevento.util;


import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        
    }
    public static org.hibernate.Transaction getCurrentTransaction(){
    	return  sessionFactory.getCurrentSession().getTransaction();
    }
    
	public static SessionFactory getSessionFactory() {
    	/*
        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Categoria.class);
                sessionFactory = ac.configure().buildSessionFactory();
                //SchemaExport se = new SchemaExport(ac);
                //se.create(true, true);
                

            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

            return sessionFactory;

        } else {
            return sessionFactory;
        }
        */
		SessionFactory factory = null;
		if(sessionFactory == null || sessionFactory.isClosed()){
		 AnnotationConfiguration configuration = new AnnotationConfiguration();
		  configuration.configure();
		  
		    factory = configuration.buildSessionFactory();
		   
			   factory.openSession();
		   
		}else
			factory = sessionFactory;
		
		return factory;
    }

    //public static void main(String[] args) {
    //    HibernateUtil.getSessionFactory();
    //}

}
