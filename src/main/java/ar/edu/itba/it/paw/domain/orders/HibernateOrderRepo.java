package ar.edu.itba.it.paw.domain.orders;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateOrderRepo extends AbstractHibernateRepo implements
		OrderRepo {
	@Autowired
	public HibernateOrderRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Order get(int id) {
		return this.get(Order.class, id);

	}

	@Override
	public void add(Order entity) {
		this.save(entity);
	}

	@Override
	public List<Order> list() {
		return find("from Order");
	}

	@Override
	public List<Order> getAll() {
		return this.list();
	}

	@Override
	public void delete(Order order) {
		super.delete(order);
	}
}
