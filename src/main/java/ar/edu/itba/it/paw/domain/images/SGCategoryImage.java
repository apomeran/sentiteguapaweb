package ar.edu.itba.it.paw.domain.images;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.products.Category;

@Entity
public class SGCategoryImage extends PersistentEntity {

	@ManyToOne
	private Category category;
	private String url;
	private String name;
	private String size;

	SGCategoryImage() {
	}

	public SGCategoryImage(Category category, String url, String name,
			String size) {
		setCategory(category);
		setUrl(url);
		setSize(size);
		setName(name);

	}

	public Category getProduct() {
		return category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCategory(Category category) {
		this.category = category;
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
