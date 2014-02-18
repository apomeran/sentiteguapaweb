package ar.edu.itba.it.paw.domain.contact;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface ContactRepo extends BaseRepo<Contact> {

	/**
     * Returns a List of all contacts
     */
	public List<Contact> getAll();

	/**
     * Returns a single Contact by its id
     */
    public Contact get(int contactId);

    /**
     * Deletes a single Contact by its id
     */
    
	public void delete(Contact contact);
	
}
