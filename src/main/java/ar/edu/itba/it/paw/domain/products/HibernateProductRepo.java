package ar.edu.itba.it.paw.domain.products;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.common.AbstractHibernateRepo;

@Repository
public class HibernateProductRepo extends AbstractHibernateRepo implements
		ProductRepo {

	@Autowired
	public HibernateProductRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Product get(int id) {
		return this.get(Product.class, id);
	}

	@Override
	public void add(Product entity) {
		this.save(entity);

	}

	@Override
	public List<Product> list() {
		return this.find("from Product where visible = 1");
	}

	@Override
	public List<Product> getProductsByCategory(Category category) {
		List<Product> lstProducts = list();
		Set<Product> toReturn = new HashSet<Product>();
		for (Product product : lstProducts) {
			for (Category categorytoCheck : product.getCategories()) {
				if (categorytoCheck.equals(category))
					toReturn.add(product);
			}
		}
		return new ArrayList<Product>(toReturn);
	}

	@Override
	public List<Product> getAll() {
		return this.list();
	}

	private List<Product> getProductsByName(String name) {
		return find("from Product where visible = 1 AND lower(name) like ? ",
				name);
	}

	@Override
	public List<Product> getProductsByQuery(String query) {
		query = "%" + query.toLowerCase() + "%";

		List<Product> result = new ArrayList<Product>();
		Set<Product> s = new HashSet<Product>();

		List<Product> byName = getProductsByName(query);

		for (Product product : byName) {
			if (s.add(product)) {
				result.add(product);
			}
		}
		return result;
	}

	@Override
	public void delete(Product p) {
		p.setVisible(0);
	}

	@Override
	public List<Product> getTop15() {
		return this.find("from Product WHERE visible = 1 LIMIT 15");
	}

	@Override
	public List<Product> getTopN(int n) {
		return this.Limitedfind("from Product WHERE visible = 1 ", n);
	}

}
