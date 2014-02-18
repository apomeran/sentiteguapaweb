package ar.edu.itba.it.paw.domain.images;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.products.ProductColor;

@Entity
public class SGColorImage extends PersistentEntity {

	@ManyToOne
	private ProductColor color;
	private String url;
	private String name;
	private String size;

	SGColorImage() {
	}

	public SGColorImage(ProductColor color, String url, String name, String size) {
		setProductColor(color);
		setUrl(url);
		setSize(size);
		setName(name);

	}

	public ProductColor getProduct() {
		return color;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setProductColor(ProductColor color) {
		this.color = color;
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
