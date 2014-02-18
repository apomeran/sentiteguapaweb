package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface SGImageRepo extends BaseRepo<SGImage> {

	/**
	 * Returns a List of all contacts
	 */
	public List<SGImage> getAll();

	/**
	 * Returns a single Image by its id
	 */
	public SGImage get(int sgImageid);

	/**
	 * Deletes a single Image by its id
	 */

	public void delete(SGImage img);

}
