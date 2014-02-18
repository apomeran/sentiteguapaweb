package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface CategoryRepo extends BaseRepo<Category> {

	/**
     * Returns a List of all categories
     */
	public List<Category> getAll();

	/**
     * Returns a single Category by its id
     */
    public Category get(int catId);

    /**
     * Deletes a single Category by its id
     */
	public void delete(Category c);
	
}
