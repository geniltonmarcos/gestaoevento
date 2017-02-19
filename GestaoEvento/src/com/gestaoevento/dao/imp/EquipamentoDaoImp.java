package com.gestaoevento.dao.imp;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.gestaoevento.dao.EquipamentoDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Equipamento;
import com.gestaoevento.model.Localidade;

public class EquipamentoDaoImp implements EquipamentoDao {

	public Equipamento getEquipamento(Long id) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			String queryString = "from Equipamento where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			Equipamento eq = (Equipamento)query.uniqueResult();
			return eq;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> list(Equipamento equipamento) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		List<Equipamento> lista = null;
		try {
			//t = session.beginTransaction();
			if("".equals(equipamento.getNome()) || null == equipamento.getNome())
				lista = session.createQuery("from Equipamento").list();
			else {
				
				lista = session.createQuery("from Equipamento Where lower(nome) like lower(:nome) ")
						.setParameter("nome", "%"+equipamento.getNome()+"%").list();
			}
			//t.commit();
		} catch (HibernateException e) {
			//if (t != null)
				//	t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		return lista;
	}

	public void delete(Equipamento equipamento) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			Query query = session.createSQLQuery("select count(0) from equipamento_evento where id_equipamento = :id");
			query.setLong("id", equipamento.getId());
			BigInteger i = (BigInteger) query.uniqueResult();
			if(i.intValue() == 0)
				session.delete(equipamento);
			else{
				equipamento.setStatus(false);
				session.merge(equipamento);
			}
		} catch (HibernateException e) {
			//if (t != null)
				//	t.rollback();
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
	}

	public void update(Equipamento equipamento) {

		// System.out.println("Titulo: "+equipamento.getTitulo());
		// System.out.println("Id: "+equipamento.getId());

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			session.update(equipamento);
			// session.merge(equipamento);
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

	public void save(Equipamento equipamento) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
//		Transaction t = null;
		try {

//			t = session.beginTransaction();
			session.save(equipamento);
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

