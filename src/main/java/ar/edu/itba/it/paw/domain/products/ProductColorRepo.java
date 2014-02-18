package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface ProductColorRepo extends BaseRepo<ProductColor> {

	public List<ProductColor> getAll();

	public void delete(ProductColor color);

	// Nothing for the moment
}
