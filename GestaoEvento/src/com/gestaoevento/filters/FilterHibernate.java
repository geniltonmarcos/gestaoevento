package com.gestaoevento.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import com.gestaoevento.util.HibernateUtil;

public class FilterHibernate implements Filter{
	  
	 public static SessionFactory sf;  
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException{
		
		 sf.getCurrentSession().beginTransaction();  

         // Call the next filter (continue request processing)  
         chain.doFilter(request, response);  

         // Commit and cleanup  
         System.out.println("Committing the database transaction");  
         sf.getCurrentSession().getTransaction().commit(); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Initializing filter...");  
		System.out.println("Obtaining SessionFactory from static HibernateUtil singleton");  
        sf = HibernateUtil.getSessionFactory();
	}

}
