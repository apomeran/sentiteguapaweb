package ar.edu.itba.it.paw.domain.images;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.products.Product;

@Entity
public class SGImage extends PersistentEntity {

	@ManyToOne
	private Product product;
	private String url;
	private String name;
	private String size;

	SGImage() {
	}

	public SGImage(Product product, String url, String name, String size) {
		setProduct(product);
		setUrl(url);
		setSize(size);
		setName(name);

	}

	public Product getProduct() {
		return product;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
