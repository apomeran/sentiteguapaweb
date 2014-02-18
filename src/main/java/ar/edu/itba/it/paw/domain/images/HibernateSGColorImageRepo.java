package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateSGColorImageRepo extends AbstractHibernateRepo implements
SGColorImageRepo {
	@Autowired
	public HibernateSGColorImageRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public SGColorImage get(int id) {
		return this.get(SGColorImage.class, id);

	}

	@Override
	public void add(SGColorImage entity) {
		this.save(entity);
	}

	@Override
	public List<SGColorImage> list() {
		return find("from SGColorImage");
	}

	@Override
	public List<SGColorImage> getAll() {
		return this.list();
	}

	@Override
	public void delete(SGColorImage image) {
		super.delete(image);
	}
}
