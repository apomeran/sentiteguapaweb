package ar.edu.itba.it.paw.domain.products;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.images.SGCategoryImage;

@Entity
public class Category extends PersistentEntity {

	private String name;
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products;

	@OneToMany(mappedBy = "category")
	@Cascade(value = { CascadeType.ALL, CascadeType.SAVE_UPDATE,
			CascadeType.DELETE })
	private List<SGCategoryImage> catimages;
	@Transient
	private int numberPhotos;

	public Category(String name) {
		setName(name);
	}

	Category() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		Set<Product> visibleProducts = new HashSet<Product>();
		for (Product product : products) {
			if (product.isVisible() == 1)
				visibleProducts.add(product);
			
		}
		return visibleProducts;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getCatimages() {
		if (this.catimages == null)
			return null;
		if (this.catimages.isEmpty())
			return "0";
		SGCategoryImage img = this.catimages.get(0);
		if (img == null)
			return "0";
		return img.getUrl() + img.getName();
	}

	public void setCatimages(List<SGCategoryImage> catimages) {
		this.catimages = catimages;
	}

	public void addPhoto(SGCategoryImage sgCatImage) {
		this.catimages.add(sgCatImage);
	}

	public String getFirstPhoto() {
		if (this.catimages.isEmpty())
			return "0";
		SGCategoryImage img = this.catimages.get(0);
		if (img == null)
			return "0";
		return img.getUrl();
	}

	public List<SGCategoryImage> getCategoryImages() {
		return this.catimages;
	}

	public int getNumberPhotos() {
		return this.catimages == null ? 0 : this.catimages.size();
	}

}
