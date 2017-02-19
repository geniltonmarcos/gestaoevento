package com.gestaoevento.util;


import java.sql.SQLException;

import javax.transaction.Transaction;

import org.hibernate.Session;

import com.gestaoevento.dao.UsuarioDao;
import com.gestaoevento.dao.imp.UsuarioDaoImp;
import com.gestaoevento.model.Usuario;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class HibernateTest {

    public static void main(String[] args) throws SQLException {

        //Livro livro = new Livro();

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        session.save(user);
        //t.commit();
//        System.out.println("ID do Pessoa: " + user.getId());
        
//        user = (Usuario) session.load(Usuario.class, 1L);
//        System.out.println(user.getNome());

        //session.close();
//    	
    	UsuarioDao cd = new UsuarioDaoImp();
    	Usuario c = new Usuario();
    	c.setId(Long.parseLong("1"));
    	for(Usuario ca : cd.list(c)){
    		System.out.println(ca.getNome());
    	}

    }
}
