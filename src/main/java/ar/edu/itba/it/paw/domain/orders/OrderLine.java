package ar.edu.itba.it.paw.domain.orders;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;

@Entity
public class OrderLine extends PersistentEntity {

	@ManyToOne
	private Order order;
	private int lineNumber;
	@OneToOne
	private Product product;
	private int quantity;
	private String size;
	@OneToOne
	private ProductColor prodcolor;

	public OrderLine() {
	}

	public OrderLine(Product p, int q, String size, ProductColor prodcolor) {
				super();
		setProduct(p);
		setQuantity(q);
		setSize(size);
		setProdcolor(prodcolor);
	}

	public OrderLine(int id) {
		super();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProductColor getProdcolor() {
		return prodcolor;
	}

	public void setProdcolor(ProductColor prodcolor) {
		this.prodcolor = prodcolor;
	}
}
