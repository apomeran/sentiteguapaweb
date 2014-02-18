package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateSGCategoryImageRepo extends AbstractHibernateRepo
		implements SGCategoryImageRepo {
	@Autowired
	public HibernateSGCategoryImageRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public SGCategoryImage get(int id) {
		return this.get(SGCategoryImage.class, id);

	}

	@Override
	public void add(SGCategoryImage entity) {
		this.save(entity);
	}

	@Override
	public List<SGCategoryImage> list() {
		return find("from SGCategoryImage");
	}

	@Override
	public List<SGCategoryImage> getAll() {
		return this.list();
	}

	@Override
	public void delete(SGCategoryImage image) {
		super.delete(image);
	}
}
