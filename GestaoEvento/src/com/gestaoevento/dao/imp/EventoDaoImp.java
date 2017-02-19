package com.gestaoevento.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.gestaoevento.dao.EventoDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Evento;

public class EventoDaoImp implements EventoDao {

	public Evento getEvento(Long id) {
		Session session = FilterHibernate.sf.getCurrentSession();
		
		try {
			String queryString = "from Evento where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			 Evento evt = (Evento) query.uniqueResult();
			 return evt;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Evento> list(Evento evento) {
		Session session = FilterHibernate.sf.getCurrentSession();
		List<Evento> lista = null;
		try {
				if(evento.getStatus() != null && !"".equals(evento.getStatus())){
					lista = session.createQuery("from Evento Where lower(status) like lower(:status) ")
							.setParameter("status", "%"+evento.getStatus()+"%").list();
				}else{				
					lista = session.createQuery("from Evento").list();
				}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listRelatorio(Evento evento) {
		Session session = FilterHibernate.sf.getCurrentSession();
		List<Evento> lista = null;
		try {
			
			if(evento.getStatus() != null && !"".equals(evento.getStatus()))
				lista = session.createQuery("from Evento Where lower(status) like lower(:status) ")
				.setParameter("status", "%"+evento.getStatus()+"%").list();
									
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return lista;
	}
	
	public void delete(Evento evento) {
		Session session = FilterHibernate.sf.getCurrentSession();

		try {
			session.delete(evento);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void update(Evento evento) {

		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			session.update(evento);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void save(Evento evento) {
		Session session = FilterHibernate.sf.getCurrentSession();

		try {

			session.save(evento);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
	}
}

