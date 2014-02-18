package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;

@Entity
public class ClotheSize extends PersistentEntity {
	@ManyToMany(mappedBy = "sizes")
	private List<Product> products;
	private String name;
	private String code;

	public ClotheSize() {

	}

	public ClotheSize(String name, String code) {
		setCode(code);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
