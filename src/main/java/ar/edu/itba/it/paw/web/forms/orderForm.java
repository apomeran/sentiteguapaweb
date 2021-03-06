package ar.edu.itba.it.paw.web.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.itba.it.paw.domain.orders.Order;
import ar.edu.itba.it.paw.domain.orders.OrderLine;

public class orderForm {

	private String customerName;
	private String address;
	private String city;
	private String state;
	private String phone;
	private String email;
	private String cuit;
	private String ivacondition;
	private String express;
	private List<OrderLine> orderLine = new ArrayList<OrderLine>();

	public orderForm() {
		orderLine.add(new OrderLine());
	}

	public Order build() {
		return new Order(customerName, address, city, state, phone, email,
				cuit, ivacondition, orderLine, new Date(), express);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getIvacondition() {
		return ivacondition;
	}

	public void setIvacondition(String ivacondition) {
		this.ivacondition = ivacondition;
	}

	public List<OrderLine> getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(List<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}
}
