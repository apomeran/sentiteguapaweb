package ar.edu.itba.it.paw.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.domain.products.ClotheSize;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;
import ar.edu.itba.it.paw.domain.products.ProductRepo;
import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.validator.LoginFormValidator;

@Controller
public class IndexController extends BaseController {

	private ProductRepo productRepo;
	private CategoryRepo catRepo;

	@Autowired
	public IndexController(ProductRepo prodRepo, CategoryRepo catRepo,
			LoginFormValidator l, UserRepo u) {
		this.productRepo = prodRepo;
		this.catRepo = catRepo;

	}

	@RequestMapping(value = { "admin/login", "/index/login" })
	public ModelAndView login(HttpSession session) {
		return super.login(session);
	}

	@RequestMapping(value = { "admin/logout", "/index/logout" })
	public ModelAndView logout(HttpSession session) {
		return super.logout(session);
	}

	@RequestMapping(value = { "/search", "/index/search" })
	public EnhancedModelAndView search(HttpSession session,
			@RequestParam(value = "query", required = true) String query) {

		EnhancedModelAndView mav = generateContext("List", true, true);
		List<Product> p = productRepo.getProductsByQuery(query);
		mav.addObject("productList", p);
		mav.setViewName("index/list");
		mav.addObject("squery", query);
		mav.addObject("cartOrder", getCart(session));

		return mav;
	}
	
	@RequestMapping(value = { "/aboutus", "/index/aboutus" })
	public ModelAndView aboutus(HttpSession session) {
		ModelAndView mav = new ModelAndView("index/aboutus");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/additem",
			"/index/additem" })
	public EnhancedModelAndView addItem(@RequestParam("id") Product p,
			@RequestParam("catid") Integer catId,
			@RequestParam("quantity") int quantity,
			@RequestParam("colorid") ProductColor prodcolor,
			@RequestParam("sizeid") ClotheSize size, HttpSession session) {
		getCart(session).addItem(p, quantity,
				size.getName() + " (" + size.getCode() + ")", prodcolor);
		return list(session, "categories", catId, null);
	}

	@RequestMapping(value = { "/", "/index", "/index/list" })
	public EnhancedModelAndView list(HttpSession session,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "id", required = false) Integer catId,
			@RequestParam(value = "num", required = false) Integer num) {

		EnhancedModelAndView mav = generateContext("Sentite Guapa", true, true,
				"index/list");

		if (query == null) {
			mav = generateContext("Sentite Guapa", true, true, "index/catlist");
			mav.addObject("categoryList", catRepo.getAll());
			mav.addObject("cartOrder", getCart(session));

			return mav;
		}

		if (query.equals("categories")) {
			mav.addObject("productList", catRepo.get(catId).getProducts());
			mav.addObject("catid", catId);

		} else {

			mav.addObject("productList", productRepo.getAll());
			mav.addObject("tab_all", true);
		}
		mav.addObject("cartOrder", getCart(session));
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/killcheckout",
			"/index/killcheckout" })
	public ModelAndView killCart(HttpSession session) {
		emptyCart(session);
		ModelAndView mav = new ModelAndView("index/list");
		mav.addObject("cartOrder", getCart(session));
		return mav;
	}
}
