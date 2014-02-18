package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface ProductRepo extends BaseRepo<Product> {

	public List<Product> getProductsByCategory(Category category);
	
	public List<Product> getAll();

	public void add(Product p);

	public List<Product> getProductsByQuery(String query);

	public void delete(Product p);

}
