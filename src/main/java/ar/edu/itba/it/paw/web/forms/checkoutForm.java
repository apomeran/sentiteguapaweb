package ar.edu.itba.it.paw.web.forms;

import java.util.Date;

import ar.edu.itba.it.paw.domain.orders.Order;

public class checkoutForm {

	private String customerName;
	private String address;
	private String city;
	private String state;
	private String phone;
	private String email;
	private String cuit;
	private String ivacondition;

	public checkoutForm() {
	}

	public Order build(Order o) {
		o.setAddress(address);
		o.setCity(city);
		o.setCuit(cuit);
		o.setCustomerName(customerName);
		o.setEmail(email);
		o.setIvacondition(ivacondition);
		o.setPhone(phone);
		o.setState(state);
		o.setOrderDate(new Date());
		return o;
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

}
