package ar.edu.itba.it.paw.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.images.SGImage;
import ar.edu.itba.it.paw.domain.images.SGImageRepo;
import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.domain.products.ClotheSize;
import ar.edu.itba.it.paw.domain.products.ClotheSizeRepo;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;
import ar.edu.itba.it.paw.domain.products.ProductColorRepo;
import ar.edu.itba.it.paw.domain.products.ProductRepo;
import ar.edu.itba.it.paw.domain.products.Season;
import ar.edu.itba.it.paw.domain.products.Unisex;
import ar.edu.itba.it.paw.web.forms.productForm;

@Controller
public class ProductController extends BaseController {

	private ProductRepo prodRepo;
	private ProductColorRepo prodColorRepo;
	private SGImageRepo imgRepo;
	private ClotheSizeRepo clotheSizeRepo;

	private CategoryRepo catRepo;

	@Autowired
	public ProductController(ProductRepo productRepo,
			ProductColorRepo prodColorRepo, CategoryRepo catRepo,
			SGImageRepo imgRepo, ClotheSizeRepo clotheSizeRepo) {
		this.prodRepo = productRepo;
		this.prodColorRepo = prodColorRepo;
		this.catRepo = catRepo;
		this.imgRepo = imgRepo;
		this.clotheSizeRepo = clotheSizeRepo;
	}

	@RequestMapping
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = new ModelAndView("products/list");
		mav.addObject("products", prodRepo.list());
		mav.addObject("itemTitle", "Productos");
		mav.addObject("linkAdmin", "product");
		mav.addObject("cartOrder", getCart(session));

		return mav;
	}

	@RequestMapping(value = { "/product/view" })
	public ModelAndView view(HttpSession session, @RequestParam("id") Product p) {
		ModelAndView mav = new ModelAndView("products/view");
		mav.addObject("product", p);
		mav.addObject("cartOrder", getCart(session));

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/product/delete" })
	public ModelAndView delete(@RequestParam("id") Product p,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		prodRepo.delete(p);
		return products(session);

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/admin/product/edit" })
	public ModelAndView edit(productForm productForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		Integer id = productForm.getId();
		Product p = prodRepo.get(id);
		prodfValidator.validate(productForm, errors);
		if (errors.hasErrors()) {
			return edit(p, session);
		}
		p.setSizes(productForm.getSizes());
		p.setPrice(productForm.getPrice());
		p.setName(productForm.getName());
		p.setCategories(productForm.getCategories());
		p.setColors(productForm.getColors());
		p.setStock(productForm.getStock());
		p.setSeason(productForm.getSeason());
		p.setUnisex(productForm.getUnisex());
		p.setCode(productForm.getCode());
		return products(session);

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/product/edit" })
	public ModelAndView edit(@RequestParam("id") Product product,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/edit");
		mav.addObject(new productForm());
		mav.addObject("item", product);
		mav.addObject("showingProductForm", true);
		List<Unisex> unisexList = new ArrayList<Unisex>(Arrays.asList(Unisex
				.values()));
		Unisex u = product.getUnisex();
		mav.addObject("selectedUnisex", u.name());
		unisexList.remove(u);
		mav.addObject("unisexList", unisexList);
		List<Season> seasonList = new ArrayList<Season>(Arrays.asList(Season
				.values()));
		Season s = product.getSeason();
		mav.addObject("selectedSeason", s.name());
		seasonList.remove(s);
		mav.addObject("seasonList", seasonList);
		// List<ProductColor> l = prodColorRepo.getAll();
		// l.remove(product.getColor());
		// mav.addObject("colorList", l);

		List<ProductColor> colorUnchecked = prodColorRepo.getAll();
		List<ProductColor> colorsChecked = product.getColors();
		for (ProductColor c : colorsChecked)
			colorUnchecked.remove(c);

		mav.addObject("colorsList", colorsChecked);
		mav.addObject("colorsListUnchecked", colorUnchecked);

		List<ClotheSize> sizesUnchecked = clotheSizeRepo.getAll();
		List<ClotheSize> sizesChecked = product.getSizes();
		for (ClotheSize c : sizesChecked)
			sizesUnchecked.remove(c);

		mav.addObject("sizesList", sizesChecked);
		mav.addObject("sizesListUnchecked", sizesUnchecked);
		
		List<Category> catUnchecked = catRepo.getAll();
		Set<Category> catChecked = product.getCategories();
		for (Category c : catChecked)
			catUnchecked.remove(c);

		mav.addObject("categoriesList", catChecked);
		mav.addObject("categoriesListUnchecked", catUnchecked);
		mav.addObject("title", "Productos");
		mav.addObject("linkAdmin", "product");
		mav.addObject("imageType", "prod");

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/product/imageprodlist" })
	public ModelAndView imagelist(@RequestParam("id") Product product,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/imagelist");
		mav.addObject("itemList", product.getProdimages());
		mav.addObject("showingImages", true);
		mav.addObject("linkAdmin", "product");
		mav.addObject("imgauxid", product.getId());
		mav.addObject("title", "Imagenes");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/product/imagedelete" })
	public ModelAndView imagedelete(@RequestParam("pid") Product p,
			@RequestParam("id") SGImage image, HttpSession session) {

		if (!isLoggedIn(session)) {
			return login(session);
		}
		p.getProdimages().remove(image);
		imgRepo.delete(image);
		return imagelist(p, session);

	}

	@RequestMapping(value = { "/products", "/admin/products" })
	public ModelAndView products(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/list");
		mav.addObject("itemList", prodRepo.getAll());
		mav.addObject("showingProducts", true);
		mav.addObject("linkAdmin", "product");
		mav.addObject("title", "Productos");
		mav.addObject("itemTitle", "Productos");
		mav.addObject("cartOrder", getCart(session));

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/product/add",
			"/admin/product/add" })
	public ModelAndView add(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/add");
		mav.addObject(new productForm());
		mav.addObject("title", "Productos");
		mav.addObject("showingProductForm", true);
		List<Unisex> unisexList = new ArrayList<Unisex>(Arrays.asList(Unisex
				.values()));
		mav.addObject("unisexList", unisexList);
		List<Season> seasonList = new ArrayList<Season>(Arrays.asList(Season
				.values()));
		mav.addObject("seasonList", seasonList);
		List<ProductColor> l = prodColorRepo.getAll();
		mav.addObject("colorsList", l);
		List<ClotheSize> clotheList = clotheSizeRepo.getAll();
		mav.addObject("sizesList", clotheList);
		List<Category> categories = catRepo.getAll();
		mav.addObject("categoriesList", categories);
		mav.addObject("itemTitle", "Productos");
		mav.addObject("linkAdmin", "product");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/product/add",
			"/admin/product/add" })
	public ModelAndView add(productForm productForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		prodfValidator.validate(productForm, errors);
		if (errors.hasErrors()) {
			return add(session);
		}
		Product product = productForm.build();
		prodRepo.add(product);
		return products(session);
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/product/additem" })
	public ModelAndView addItem(@RequestParam("id") Product p,
			@RequestParam("quantity") int quantity,
			@RequestParam("colorid") ProductColor prodcolor,
			HttpSession session, @RequestParam("sizeid") ClotheSize size) {
		getCart(session).addItem(p, quantity, size.getName() + " (" + size.getCode() + ")", prodcolor);
		return view(session, p);
	}

}
