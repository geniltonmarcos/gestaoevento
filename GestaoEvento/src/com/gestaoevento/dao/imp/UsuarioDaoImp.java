package com.gestaoevento.dao.imp;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import com.gestaoevento.dao.UsuarioDao;
import com.gestaoevento.filters.FilterHibernate;
import com.gestaoevento.model.Reserva;
import com.gestaoevento.model.Usuario;

public class UsuarioDaoImp implements UsuarioDao {

	public Usuario getUsuario(Long id) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		
		try {
			String queryString = "from Usuario where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			return (Usuario) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> list(Usuario usuario) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		List<Usuario> lista = null;
		try {
			//t = session.beginTransaction();
			if("".equals(usuario.getNome()) || null == usuario.getNome())
				lista = session.createQuery("from Usuario").list();
			else {
				
				lista = session.createQuery("from Usuario Where upper(nome) like upper(:nome) ")
						.setParameter("nome", "%"+usuario.getNome()+"%").list();
			}
			//t.commit();
		} catch (Exception e) {
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
	public Boolean delete(Usuario usuario) {
		Boolean result = false;
		
		Session session = FilterHibernate.sf.getCurrentSession();
		//Transaction t = null;
		try {
			//t = session.beginTransaction();
			Query query = session.createQuery("from Reserva Where id_usuario = :id_usuario ");
			query.setLong("id_usuario", usuario.getId());
			
			List<Reserva> reservas =  query.list();
			if(reservas.size() == 0){
				session.delete(usuario);
				result = true;
			}else{
				usuario.setStatus("I");
				session.merge(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void update(Usuario usuario) {
		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			session.update(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void save(Usuario usuario) {
		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			session.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Usuario login(String senha, String login) {
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();//.openSession();
		Session session = FilterHibernate.sf.getCurrentSession();
		try {
			String queryString = "from Usuario where status = :status "
					+ " and login = :login "
					+ " and senha = :senha";
			
			Query query = session.createQuery(queryString);
			
			query.setString("senha", senha);
			query.setString("login", login);
			query.setString("status", "A");
			return (Usuario) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			session.flush();
//			session.close();
		}
		return null;
	}
}

