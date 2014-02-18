package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface SGCategoryImageRepo extends BaseRepo<SGCategoryImage> {

	/**
	 * Returns a List of all contacts
	 */
	public List<SGCategoryImage> getAll();

	/**
	 * Returns a single Image by its id
	 */
	public SGCategoryImage get(int sgImageid);

	/**
	 * Deletes a single Image by its id
	 */

	public void delete(SGCategoryImage img);

}
