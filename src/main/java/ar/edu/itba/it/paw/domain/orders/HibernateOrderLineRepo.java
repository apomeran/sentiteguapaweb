package ar.edu.itba.it.paw.domain.orders;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateOrderLineRepo extends AbstractHibernateRepo implements
		OrderLineRepo {
	@Autowired
	public HibernateOrderLineRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public OrderLine get(int id) {
		return this.get(OrderLine.class, id);

	}

	@Override
	public void add(OrderLine entity) {
		this.save(entity);
	}

	@Override
	public List<OrderLine> list() {
		return find("from OrderLine");
	}

	@Override
	public List<OrderLine> getAll() {
		return this.list();
	}

	@Override
	public void delete(OrderLine orderLine) {
		super.delete(orderLine);
	}
}
