package ar.edu.itba.it.paw.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.orders.Order;
import ar.edu.itba.it.paw.domain.orders.OrderLine;
import ar.edu.itba.it.paw.domain.orders.OrderLineRepo;
import ar.edu.itba.it.paw.domain.orders.OrderRepo;
import ar.edu.itba.it.paw.domain.products.ProductRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.forms.checkoutForm;
import ar.edu.itba.it.paw.web.forms.orderForm;
import ar.edu.itba.it.paw.web.validator.CheckOutFormValidator;
import ar.edu.itba.it.paw.web.validator.OrderFormValidator;

@Controller
public class OrderController extends BaseController {

	private OrderRepo orderRepo;
	private OrderLineRepo orderLineRepo;

	private OrderFormValidator orderfValidator;
	private CheckOutFormValidator checkoutfValidator;

	private ProductRepo prodRepo;

	@Autowired
	public OrderController(OrderRepo orderRepo,
			OrderFormValidator orderfValidator,
			CheckOutFormValidator checkoutfValidator, ProductRepo prodRepo,
			OrderLineRepo orderLineRepo) {
		this.orderfValidator = orderfValidator;
		this.checkoutfValidator = checkoutfValidator;
		this.orderRepo = orderRepo;
		this.prodRepo = prodRepo;
		this.orderLineRepo = orderLineRepo;
	}

	@RequestMapping(value = { "admin/orders" })
	public ModelAndView orders(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("orders/list");
		List<Order> o = orderRepo.list();
		Collections.sort(o);
		mav.addObject("orders", o);
		return mav;
	}

	@RequestMapping(value = { "admin/order/view" })
	public ModelAndView view(@RequestParam("id") Order order,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("orders/view");
		mav.addObject("order", order);
		return mav;
	}

	@RequestMapping(value = { "admin/order/delete" })
	public ModelAndView delete(@RequestParam("id") Order order,
			HttpSession session) {

		orderRepo.delete(order);
		return orders(session);
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/order",
			"/index/order" })
	public EnhancedModelAndView order(HttpSession session) {
		EnhancedModelAndView mav = generateContext("Order", true, true);
		mav.setViewName("orders/orderform");
		mav.addObject(new orderForm());
		mav.addObject("prodList", prodRepo.getAll());
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/checkout",
			"/index/checkout" })
	public EnhancedModelAndView checkout(HttpSession session) {
		EnhancedModelAndView mav = generateContext("Order", true, true);
		mav.setViewName("orders/checkoutform");
		mav.addObject(new checkoutForm());
		mav.addObject("prodList", prodRepo.getAll());
		mav.addObject("Order", getCart(session));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/checkout",
			"/index/checkout" })
	public EnhancedModelAndView checkout(HttpSession session,
			checkoutForm checkoutForm, Errors errors) {
		checkoutfValidator.validate(checkoutForm, errors);
		if (errors.hasErrors()) {
			return checkout(session);
		}
		Order o = checkoutForm.build(getCart(session));
		int orderLinesCount = 0;
		for (OrderLine ol : o.getOrderLine()) {

			if (ol.getQuantity() > 0) {
				orderLinesCount++;
				ol.setOrder(o);
				orderLineRepo.add(ol);
			}
		}
		if (orderLinesCount > 0)
			orderRepo.add(o);
		EnhancedModelAndView mav = generateContext("Orden Enviada", true, true);
		mav.addObject("order", o);
		mav.setViewName("orders/success");
		emptyCart(session);
		return mav;
	}
	
	

	@RequestMapping(method = RequestMethod.POST, value = { "/order",
			"/index/order" })
	public ModelAndView order(orderForm orderForm, Errors errors,
			HttpSession session) {
		orderfValidator.validate(orderForm, errors);
		if (errors.hasErrors()) {
			return order(session);
		}
		Order order = orderForm.build();
		int orderLinesCount = 0;
		for (OrderLine ol : orderForm.getOrderLine()) {

			if (ol.getQuantity() > 0) {
				orderLinesCount++;
				ol.setOrder(order);
				orderLineRepo.add(ol);
			}
		}
		if (orderLinesCount > 0)
			orderRepo.add(order);
		ModelAndView mav = new ModelAndView("orders/success");
		mav.addObject("order", order);
		return mav;
	}
}
