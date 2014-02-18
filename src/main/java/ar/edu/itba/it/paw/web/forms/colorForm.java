package ar.edu.itba.it.paw.web.forms;

import ar.edu.itba.it.paw.domain.products.ProductColor;

public class colorForm {

	private String name;
	private Integer id;

	public colorForm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductColor build() {
		return new ProductColor(name);
	}
}
