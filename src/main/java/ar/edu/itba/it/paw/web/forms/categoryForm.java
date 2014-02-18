package ar.edu.itba.it.paw.web.forms;

import ar.edu.itba.it.paw.domain.products.Category;

public class categoryForm {

	private String name;
	private Integer id;

	public categoryForm() {
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

	public Category build() {
		return new Category(name);
	}

}
