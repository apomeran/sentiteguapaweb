package ar.edu.itba.it.paw.domain.orders;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface OrderRepo extends BaseRepo<Order> {

	/**
     * Returns a List of all Orders
     */
	public List<Order> getAll();

	/**
     * Returns a single Order by its id
     */
    public Order get(int orderId);

    /**
     * Deletes a single Order by its id
     */
	public void delete(Order order);
	
}
