package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface ClotheSizeRepo extends BaseRepo<ClotheSize> {

	/**
	 * Returns a List of all Clothe Sizes
	 */
	public List<ClotheSize> getAll();

	/**
	 * Returns a single Clothe Size by its id
	 */
	public ClotheSize get(int catId);

	/**
	 * Deletes a single Clothe Size by its id
	 */
	public void delete(ClotheSize c);

}
