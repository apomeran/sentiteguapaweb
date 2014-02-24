package ar.edu.itba.it.paw.web.forms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.ClotheSize;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;
import ar.edu.itba.it.paw.domain.products.Season;
import ar.edu.itba.it.paw.domain.products.Unisex;

public class productForm {

	private String name;
	private Float price;
	private Integer id;
	private List<ProductColor> colors;
	private Integer stock;
	private Set<Category> categories = new HashSet<Category>();
	private Season season;
	private Unisex unisex;
	private List<ClotheSize> sizes;
	private int code;

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public productForm() {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product build() {
		return new Product(name, price, categories, colors, stock, unisex,
				season, sizes, code);
	}

	public List<ProductColor> getColors() {
		return colors;
	}

	public void setColors(List<ProductColor> colors) {
		this.colors = colors;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stocklvl) {
		this.stock = stocklvl;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Unisex getUnisex() {
		return unisex;
	}

	public void setUnisex(Unisex unisex) {
		this.unisex = unisex;
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

}
