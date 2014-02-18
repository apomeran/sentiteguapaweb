package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateSGImageRepo extends AbstractHibernateRepo implements
		SGImageRepo {
	@Autowired
	public HibernateSGImageRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public SGImage get(int id) {
		return this.get(SGImage.class, id);

	}

	@Override
	public void add(SGImage entity) {
		this.save(entity);
	}

	@Override
	public List<SGImage> list() {
		return find("from SGImage");
	}

	@Override
	public List<SGImage> getAll() {
		return this.list();
	}

	@Override
	public void delete(SGImage image) {
		super.delete(image);
	}
}
