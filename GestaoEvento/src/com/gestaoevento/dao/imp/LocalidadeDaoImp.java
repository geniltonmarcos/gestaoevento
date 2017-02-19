package com.gestaoevento.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gestaoevento.dao.LocalidadeDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Localidade;
import com.gestaoevento.model.Pessoa;

public class LocalidadeDaoImp implements LocalidadeDao {

	public Localidade getLocalidade(Long id) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		
		try {
			String queryString = "from Localidade where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			return (Localidade) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Localidade> list(Localidade localidade) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		List<Localidade> lista = null;
		try {
			//t = session.beginTransaction();
			if("".equals(localidade.getLocal()) || null == localidade.getLocal())
				lista = session.createQuery("from Localidade").list();
			else {
				
				lista = session.createQuery("from Localidade Where lower(localidade) like lower(:localidade) ")
						.setParameter("localidade", "%"+localidade.getLocal()+"%").list();
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

	@SuppressWarnings("unchecked")
	public void delete(Localidade localidade) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			Query query = session.createQuery("from Evento Where id_local = :id_local ");
			query.setLong("id_pessoa", localidade.getId());
			List<Localidade> pessoas = query.list();
			if(pessoas.size() == 0)
				session.delete(localidade);
			else{
				localidade.setAtivo(false);
				session.merge(localidade);
			}
		} catch (HibernateException e) {
			//if (t != null)
				//t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}

	public void update(Localidade localidade) {

		// System.out.println("Titulo: "+localidade.getTitulo());
		// System.out.println("Id: "+localidade.getId());
		Session session = FilterHibernate.sf.getCurrentSession();
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			session.update(localidade);
			// session.merge(localidade);
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

	public void save(Localidade localidade) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {

			//t = session.beginTransaction();
			session.save(localidade);
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//	t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}
}

