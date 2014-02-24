package ar.edu.itba.it.paw.domain.orders;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;

@Entity
@Table(name = "systemorder")
public class Order extends PersistentEntity implements Comparable<Order> {

	private String customerName;
	private Date orderDate;
	private String address;
	private String city;
	private String state;
	private String phone;
	private String email;
	private String cuit;
	private String ivacondition;
	private String express;
	@OneToMany(mappedBy = "order")
	@Cascade(value = { CascadeType.ALL, CascadeType.SAVE_UPDATE,
			CascadeType.DELETE })
	private List<OrderLine> orderLine;

	public Order(String customerName, String address, String city,
			String state, String phone, String email, String cuit,
			String ivacondition, List<OrderLine> orderLine, Date orderDate, String express) {
		this.setAddress(address);
		this.setCity(city);
		this.setCuit(cuit);
		this.setCustomerName(customerName);
		this.setEmail(email);
		this.setIvacondition(ivacondition);
		this.setOrderLine(orderLine);
		this.setState(state);
		this.setPhone(phone);
		this.setOrderDate(orderDate);
		this.setExpress(express);
	}

	Order() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Float getTotal() {
		Float result = (float) 0;
		for (OrderLine o : orderLine) {
			result += o.getProduct().getPrice() * o.getQuantity();
		}
		return result;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int compareTo(Order otherOrder) {
		return otherOrder.orderDate.compareTo(orderDate);
	}

	public void addItem(Product product, int quantity, String size,
			ProductColor prodcolor) {
		orderLine.add(new OrderLine(product, quantity, size, prodcolor));
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

}
