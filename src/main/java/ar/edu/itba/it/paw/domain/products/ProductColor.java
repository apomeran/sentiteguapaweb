package ar.edu.itba.it.paw.domain.products;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.images.SGColorImage;

@Entity
public class ProductColor extends PersistentEntity {

	@ManyToMany(mappedBy = "colors")
	private List<Product> products;
	private String name;

	@OneToMany(mappedBy = "color")
    @Cascade(value={CascadeType.ALL, CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<SGColorImage> colorimages;

	public ProductColor(String colorname) {
		setName(colorname);
	}

	public ProductColor() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SGColorImage> getColorimages() {
		return colorimages;
	}

	public void setColorimages(List<SGColorImage> colorimages) {
		this.colorimages = colorimages;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addPhoto(SGColorImage sgColorImage) {
		this.colorimages.add(sgColorImage);
	}
}
