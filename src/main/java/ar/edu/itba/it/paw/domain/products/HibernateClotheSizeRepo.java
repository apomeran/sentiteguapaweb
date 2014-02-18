package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateClotheSizeRepo extends AbstractHibernateRepo implements
		ClotheSizeRepo {
	@Autowired
	public HibernateClotheSizeRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ClotheSize get(int id) {
		return this.get(ClotheSize.class, id);

	}

	@Override
	public void add(ClotheSize entity) {
		this.save(entity);
	}

	@Override
	public List<ClotheSize> list() {
		return find("from ClotheSize");
	}

	@Override
	public List<ClotheSize> getAll() {
		return this.list();
	}

	@Override
	public void delete(ClotheSize c) {
		super.delete(c);
	}
}
