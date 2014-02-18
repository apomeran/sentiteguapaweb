package ar.edu.itba.it.paw.domain.contact;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateContactRepo extends AbstractHibernateRepo implements
		ContactRepo {
	@Autowired
	public HibernateContactRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Contact get(int id) {
		return this.get(Contact.class, id);

	}

	@Override
	public void add(Contact entity) {
		this.save(entity);
	}

	@Override
	public List<Contact> list() {
		return find("from Contact");
	}

	@Override
	public List<Contact> getAll() {
		return this.list();
	}

	@Override
	public void delete(Contact contact) {
		super.delete(contact);
	}
}
