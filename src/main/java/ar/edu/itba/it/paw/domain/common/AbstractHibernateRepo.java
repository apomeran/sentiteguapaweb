package ar.edu.itba.it.paw.domain.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.exception.ConstraintViolationException;

public abstract class AbstractHibernateRepo {

	private final SessionFactory sessionFactory;

	public AbstractHibernateRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type, Serializable id) {
		return (T) getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object... params) {
		Session session = getSession();

		Query query = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findMax(String hql, int maxResult, Object... params) {
		Session session = getSession();

		Query query = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setMaxResults(maxResult);
		List<T> list = query.list();
		return list;
	}

	public int delete(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

	protected org.hibernate.classic.Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(Object o) {
		return getSession().save(o);
	}

	public void delete(Object o) throws ConstraintViolationException {
		getSession().delete(o);
	}
}
