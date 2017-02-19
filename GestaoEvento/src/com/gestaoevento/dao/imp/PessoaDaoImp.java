package com.gestaoevento.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.gestaoevento.dao.PessoaDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Pessoa;

public class PessoaDaoImp implements PessoaDao {

	public Pessoa getPessoa(Long id) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			String queryString = "from Pessoa where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			return (Pessoa) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> list(Pessoa pessoa) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		List<Pessoa> lista = null;
		try {
			//t = session.beginTransaction();
			if("".equals(pessoa.getNome()) || null == pessoa.getNome())
				lista = session.createQuery("from Pessoa").list();
			else {
				
				lista = session.createQuery("from Pessoa Where lower(nome) like lower(:nome) ")
						.setParameter("nome", "%"+pessoa.getNome()+"%").list();
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
	public void delete(Pessoa pessoa) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			
			Query query = session.createQuery("from Evento Where id_pessoa = :id_pessoa ");
			query.setLong("id_pessoa", pessoa.getId());
			List<Pessoa> pessoas = query.list();
			if(pessoas.size() == 0)
				session.delete(pessoa);
			else{
				pessoa.setAtivo(false);
				session.merge(pessoa);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void update(Pessoa pessoa) {

		// System.out.println("Titulo: "+pessoa.getTitulo());
		// System.out.println("Id: "+pessoa.getId());

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			session.update(pessoa);
			// session.merge(pessoa);
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

	public void save(Pessoa pessoa) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {

			//t = session.beginTransaction();
			session.save(pessoa);
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

