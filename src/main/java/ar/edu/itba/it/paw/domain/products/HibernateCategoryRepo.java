package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateCategoryRepo extends AbstractHibernateRepo implements
		CategoryRepo {
	@Autowired
	public HibernateCategoryRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Category get(int id) {
		return this.get(Category.class, id);

	}

	@Override
	public void add(Category entity) {
		this.save(entity);
	}

	@Override
	public List<Category> list() {
		return find("from Category");
	}

	@Override
	public List<Category> getAll() {
		return this.list();
	}

	@Override
	public void delete(Category c) {
		super.delete(c);
	}
}
