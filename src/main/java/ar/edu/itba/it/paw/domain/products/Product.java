package ar.edu.itba.it.paw.domain.products;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;
import ar.edu.itba.it.paw.domain.images.SGImage;

@Entity
public class Product extends PersistentEntity {

	@ManyToMany
	Set<Category> categories;
	private String name;
	private Float price;

	@OneToMany(mappedBy = "product")
	@Cascade(value = { CascadeType.ALL, CascadeType.SAVE_UPDATE,
			CascadeType.DELETE })
	private List<SGImage> prodimages;

	@ManyToMany
	private List<ProductColor> colors;
	private int stock;
	private int code;
	private int visible;

	@Enumerated(EnumType.STRING)
	private Unisex unisex = Unisex.MUJER; // By Default
	private Season season;
	@ManyToMany
	private List<ClotheSize> sizes;

	public Product(String prodName, Float prodPrice, Set<Category> cat,
			List<ProductColor> colors, int stock, Unisex unisex, Season season,
			List<ClotheSize> sizes, int code) {
		setName(prodName);
		setPrice(prodPrice);
		setCategories(cat);
		setColors(colors);
		setStock(stock);
		setUnisex(unisex);
		setSeason(season);
		setSizes(sizes);
		setCode(code);
		setVisible(1);
	}

	public Product() {
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Unisex getUnisex() {
		return unisex;
	}

	public void setUnisex(Unisex unisex) {
		this.unisex = unisex;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<SGImage> getProdimages() {
		return prodimages;
	}

	public void setProdimages(List<SGImage> prodimages) {
		this.prodimages = prodimages;
	}

	public void addPhoto(SGImage s) {
		this.prodimages.add(s);
	}

	public List<ProductColor> getColors() {
		return colors;
	}

	public void setColors(List<ProductColor> colors) {
		this.colors = colors;
	}

	public List<ClotheSize> getSizes() {
		return sizes;
	}

	public void setSizes(List<ClotheSize> sizes) {
		this.sizes = sizes;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int isVisible() {
		return visible;
	}

	public void setVisible(int i) {
		this.visible = i;
	}
	

}
