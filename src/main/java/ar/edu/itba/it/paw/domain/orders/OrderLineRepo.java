package ar.edu.itba.it.paw.domain.orders;

import java.util.List;

import ar.edu.itba.it.paw.domain.common.BaseRepo;

public interface OrderLineRepo extends BaseRepo<OrderLine> {

	/**
     * Returns a List of all LineOrders
     */
	public List<OrderLine> getAll();

	/**
     * Returns a single LineOrders by its id
     */
    public OrderLine get(int lineorderId);

    /**
     * Deletes a single LineOrders by its id
     */
	public void delete(OrderLine order);
	
}
