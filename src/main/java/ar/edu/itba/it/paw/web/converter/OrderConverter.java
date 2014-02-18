package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.orders.Order;
import ar.edu.itba.it.paw.domain.orders.OrderRepo;

@Component
public class OrderConverter implements Converter<String, Order> {

	private OrderRepo orderRepo;

	@Autowired
	public OrderConverter(OrderRepo orderRepo) {

		this.orderRepo = orderRepo;
	}

	@Override
	public Order convert(String arg) {
		return orderRepo.get(Integer.valueOf(arg));
	}
}
