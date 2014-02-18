package ar.edu.itba.it.paw.domain.images;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface SGColorImageRepo extends BaseRepo<SGColorImage> {

	/**
	 * Returns a List of all colors
	 */
	public List<SGColorImage> getAll();

	/**
	 * Returns a single Image by its id
	 */
	public SGColorImage get(int sgImageid);

	/**
	 * Deletes a single Image by its id
	 */

	public void delete(SGColorImage img);

}
