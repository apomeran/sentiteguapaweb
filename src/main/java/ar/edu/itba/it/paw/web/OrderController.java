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
import ar.edu.itba.it.paw.utils.MailSender;
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

		MailSender.send(generateCustomerMail(o), o.getEmail(),
				"SentiteGuapa - Pedido OnLine Nº" + o.getId());
		MailSender.send(
				generateManagerMail(o),
				"sentiteguapamoda@gmail.com",
				"SentiteGuapa - Realizaron un nuevo Pedido OnLine Nº"
						+ o.getId());

		emptyCart(session);
		return mav;
	}

	public String generateManagerMail(Order o) {
		String mail = "Administrador, se recibio un pedido de "
				+ o.getCustomerName() + "!! \n";
		mail += "Los datos enviados fueron: \n";
		mail += "\n -- Cuit: " + o.getCuit();
		mail += "\n -- Direccion: " + o.getAddress();
		mail += "\n -- Ciudad: " + o.getCity() + " - " + o.getState();
		mail += "\n -- Modo Envio: " + o.getExpress();
		mail += "\n -- Telefono: " + o.getPhone();
		mail += "\n -- Condicion IVA: " + o.getIvacondition();

		mail += "\n\n\n A continuacion mostraremos el detalle del pedido del cliente: \n\n";
		mail += "Pedido Nº: " + o.getId() + "\n";
		mail += "Total de la orden: $" + o.getTotal() + "0.- \n\n";
		int i = 1;
		for (OrderLine ol : o.getOrderLine()) {
			mail += "\n\n\n -- Linea N° " + i++ + " --";
			mail += "\n\n Producto: " + ol.getProduct().getName() + " - Cod ("
					+ ol.getProduct().getCode() + ")";
			mail += "\n Cantidad: " + ol.getQuantity() + " Unidad(es)";
			mail += "\n Precio Unitario: $" + ol.getProduct().getPrice()
					+ "0.-";
			if (ol.getSize() != null && ol.getSize() != "")
				mail += "\n Talle: " + ol.getSize();
			if (ol.getProdcolor() != null)
				mail += "\n Color: " + ol.getProdcolor().getName() + " - Cod: "
						+ ol.getProdcolor().getId();
			else
				mail += "\n Color: No especificado";
			mail += "\n Subtotal: $" + ol.getQuantity()
					* ol.getProduct().getPrice() + "0.-";
		}

		mail += "\n\n SentiteGuapa.com";
		return mail;
	}

	public String generateCustomerMail(Order o) {
		String mail = "Hola " + o.getCustomerName() + "!! \n";
		mail += "Gracias por emitir tu pedido en SentiteGuapa\n\n";
		mail += "Tus datos enviados son: \n";
		mail += "\n -- Cuit: " + o.getCuit();
		mail += "\n -- Direccion: " + o.getAddress();
		mail += "\n -- Ciudad: " + o.getCity() + " - " + o.getState();
		mail += "\n -- Modo Envio: " + o.getExpress();
		mail += "\n -- Telefono: " + o.getPhone();
		mail += "\n -- Condicion IVA: " + o.getIvacondition();

		mail += "\n\n\n A continuacion mostraremos el detalle de tu pedido: \n\n";
		mail += "Pedido Nº: " + o.getId() + "\n";
		mail += "Total de la orden: $" + o.getTotal() + "0.- \n\n";
		int i = 1;
		for (OrderLine ol : o.getOrderLine()) {
			mail += "\n\n\n -- Linea N° " + i++ + " --";
			mail += "\n\n Producto: " + ol.getProduct().getName() + " - Cod ("
					+ ol.getProduct().getCode() + ")";
			mail += "\n Cantidad: " + ol.getQuantity() + " Unidad(es)";
			mail += "\n Precio Unitario: $" + ol.getProduct().getPrice()
					+ "0.-";
			if (ol.getSize() != null && ol.getSize() != "")
				mail += "\n Talle: " + ol.getSize();
			if (ol.getProdcolor() != null)
				mail += "\n Color: " + ol.getProdcolor().getName() + " - Cod: "
						+ ol.getProdcolor().getId();
			else
				mail += "\n Color: No especificado";
			mail += "\n Subtotal: $" + ol.getQuantity()
					* ol.getProduct().getPrice() + "0.-";
		}
		mail += "\n\n\n\n Ante cualquier duda no dude contactarse con nosotros a traves de este mail: sentiteguapamoda@gmail.com \n\n";
		mail += "Nuestra direccion y telefono: \n Av. Avellaneda 3069 Flores - C.A.B.A Argentina - Tel: 114612-2317";
		mail += "\n\n SentiteGuapa.com";
		return mail;
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
				ol.setProdcolor(null);
				orderLinesCount++;
				ol.setOrder(order);
				orderLineRepo.add(ol);

			}
		}
		if (orderLinesCount > 0)
			orderRepo.add(order);
		ModelAndView mav = new ModelAndView("orders/success");
		mav.addObject("order", order);
		MailSender.send(generateCustomerMail(order), order.getEmail(),
				"SentiteGuapa - Pedido OnLine Nº" + order.getId());

		MailSender.send(
				generateManagerMail(order),
				"sentiteguapamoda@gmail.com",
				"SentiteGuapa - Realizaron un nuevo Pedido OnLine Nº"
						+ order.getId());

		return mav;
	}
}
