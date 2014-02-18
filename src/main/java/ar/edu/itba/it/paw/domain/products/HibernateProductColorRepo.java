package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateProductColorRepo extends AbstractHibernateRepo implements
		ProductColorRepo {
	@Autowired
	public HibernateProductColorRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ProductColor get(int id) {
		return this.get(ProductColor.class, id);

	}

	@Override
	public void add(ProductColor entity) {
		this.save(entity);
	}

	@Override
	public List<ProductColor> list() {
		return find("from ProductColor");
	}

	@Override
	public List<ProductColor> getAll() {
		return list();

	}

	@Override
	public void delete(ProductColor color) {
		super.delete(color);
	}
}
