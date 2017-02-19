package com.gestaoevento.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.gestaoevento.dao.ReservaDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Reserva;

public class ReservaDaoImp implements ReservaDao {

	public Reserva getReserva(Long id) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		
		try {
			String queryString = "from Reserva where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			return (Reserva) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<Reserva> list(Reserva reserva, Long idUsuario, String tipoUsuario) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		List<Reserva> lista = null;
		try {
			//t = session.beginTransaction();
			if("".equals(reserva.getStatus()) || null == reserva.getStatus()){
				Query query = null;
				if(tipoUsuario.equals("P")){
					//professor
					query = session.createQuery("from Reserva Where id_usuario = :id_usuario ");
					query.setLong("id_usuario", idUsuario);
				}else{
					//admin
					query = session.createQuery("from Reserva");
				}
					
				lista = query.list();
			}else {
				if(tipoUsuario.equals("P")){
					lista = session.createQuery("from Reserva Where id_usuario = :id_usuario and lower(status) like lower(:status) ")
							.setParameter("status", "%"+reserva.getStatus()+"%") 
							.setLong("id_usuario", idUsuario)
							.list();
				}else{
					//admin
					lista = session.createQuery("from Reserva Where lower(status) like lower(:status) ")
							.setParameter("status", "%"+reserva.getStatus()+"%")
							.list();
				}
			}
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		return lista;
	}

	public void delete(Reserva reserva) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			session.delete(reserva);
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}

	public void update(Reserva reserva) {

		// System.out.println("Titulo: "+reserva.getTitulo());
		// System.out.println("Id: "+reserva.getId());

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			session.update(reserva);
			// session.merge(reserva);
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}

	public void save(Reserva reserva) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {

			//t = session.beginTransaction();
			session.save(reserva);
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}
}

