package ar.edu.itba.it.paw.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.orders.Order;
import ar.edu.itba.it.paw.domain.orders.OrderLine;
import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.domain.products.ProductRepo;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.forms.LoginForm;
import ar.edu.itba.it.paw.web.validator.CategoryFormValidator;
import ar.edu.itba.it.paw.web.validator.ColorFormValidator;
import ar.edu.itba.it.paw.web.validator.LoginFormValidator;
import ar.edu.itba.it.paw.web.validator.ProductFormValidator;

@Controller
public abstract class BaseController {

	@Autowired
	private ProductRepo prodRepo;
	@Autowired
	private CategoryRepo catprodRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private LoginFormValidator lValidator;
	@Autowired
	protected CategoryFormValidator catfValidator;
	@Autowired
	protected ColorFormValidator colorfValidator;
	@Autowired
	protected ProductFormValidator prodfValidator;

	public boolean isLoggedIn(HttpSession session) {
		if (session == null)
			return false;
		Object o = null;
		try {
			o = session.getAttribute("user");

		} catch (IllegalStateException e) {
			// Session is invalidated
			return false;
		}
		return o != null;
	}

	public User getLoggedInUser(HttpSession session) {

		return (User) session.getAttribute("user");
	}

	public void setLoggedInUser(HttpSession session, User user) {

		session.setAttribute("user", user);
	}

	public void setCart(HttpSession session) {
		session.setAttribute("cartOrder", new Order("", "", "", "", "", "", "",
				"", new ArrayList<OrderLine>(), null));
	}

	public Order getCart(HttpSession session) {
		Order o = (Order) session.getAttribute("cartOrder");
		if (o == null)
			setCart(session);
		return (Order) session.getAttribute("cartOrder");
	}

	public void emptyCart(HttpSession session) {
		session.setAttribute("cartOrder", null);
	}

	public void logoutUser(HttpSession session) {
		session.invalidate();
	}

	public EnhancedModelAndView generateContext(String title, boolean sidebar,
			boolean setCategories) {

		EnhancedModelAndView mav = new EnhancedModelAndView(title);

		mav.addObject("sidebar", sidebar);
		mav.addObject("numberOfProducts", prodRepo.getAll().size());
		List<Category> allCategories = catprodRepo.getAll();
		int numberOfCategoriesWithMoreThanOneProduct = 0;
		for (Category cat : allCategories) {
			if (!cat.getProducts().isEmpty())
				numberOfCategoriesWithMoreThanOneProduct++;
		}

		mav.addObject("numberOfCategories",
				numberOfCategoriesWithMoreThanOneProduct);
		if (setCategories) {
			mav.addObject("categoriesList", catprodRepo.getAll());
		}

		return mav;
	}

	@RequestMapping
	public ModelAndView list(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/list");
		mav.addObject("itemTitle", "Sentite Guapa Website");
		mav.addObject("user", this.getLoggedInUser(session));
		mav.addObject("indexadmin", true);

		return mav;
	}
	
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {

		if (isLoggedIn(session)) {
			return list(session);
		}
		ModelAndView mav = new ModelAndView("admin/login");
		mav.addObject(new LoginForm());
		return mav;

	}

	@RequestMapping
	public ModelAndView logout(HttpSession session) {
		logoutUser(session);
		return login(session);
	}

	public EnhancedModelAndView generateContext(String title, boolean sidebar,
			boolean setCategories, String viewName) {

		EnhancedModelAndView mav = generateContext(title, sidebar,
				setCategories);
		mav.setViewName(viewName);
		return mav;
	}

	public EnhancedModelAndView indexContext() {

		EnhancedModelAndView mav = new EnhancedModelAndView("Sentite Guapa");
		mav.setViewName("redirect:/bin/index/list");
		return mav;
	}

}
