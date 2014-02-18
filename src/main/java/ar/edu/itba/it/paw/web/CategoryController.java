package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.images.SGCategoryImage;
import ar.edu.itba.it.paw.domain.images.SGCategoryImageRepo;
import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.web.forms.LoginForm;
import ar.edu.itba.it.paw.web.forms.categoryForm;
import ar.edu.itba.it.paw.web.validator.LoginFormValidator;

@Controller
public class CategoryController extends BaseController {

	private CategoryRepo catRepo;
	private SGCategoryImageRepo imgRepo;
	private UserRepo userRepo;
	private LoginFormValidator lValidator;

	@Autowired
	public CategoryController(CategoryRepo catRepo, UserRepo userRepo,
			LoginFormValidator loginFormValidator,
			SGCategoryImageRepo sgCategoryImageRepo) {
		this.catRepo = catRepo;
		this.userRepo = userRepo;
		this.lValidator = loginFormValidator;
		this.imgRepo = sgCategoryImageRepo;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "admin/login2",
			"/index/login2" })
	public ModelAndView login2(LoginForm loginForm, Errors errors,
			HttpSession session) {
		lValidator.validate(loginForm, errors);
		if (errors.hasErrors()) {
			return login(session);
		}
		User user = userRepo.login(loginForm.getEmail(),
				loginForm.getPassword());
		if (user != null) {
			setLoggedInUser(session, user);
			return list(session);
		} else {
			errors.rejectValue("username", "mismatch");
			return login(session);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/category/add",
			"/admin/category/add" })
	public ModelAndView add(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/add");
		mav.addObject(new categoryForm());
		mav.addObject("title", "Categoria");
		mav.addObject("linkAdmin", "category");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/category/imagecategorylist" })
	public ModelAndView imagelist(@RequestParam("id") Category category,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/imagelist");
		mav.addObject("itemList", category.getCategoryImages());
		mav.addObject("showingImages", true);
		mav.addObject("linkAdmin", "category");
		mav.addObject("imgauxid", category.getId());
		mav.addObject("title", "Imagenes");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/category/imagedelete" })
	public ModelAndView imagedelete(@RequestParam("pid") Category category,
			@RequestParam("id") SGCategoryImage sgcatImage, HttpSession session) {

		if (!isLoggedIn(session)) {
			return login(session);
		}
		category.getCategoryImages().remove(sgcatImage);
		imgRepo.delete(sgcatImage);
		return imagelist(category, session);

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/category/add",
			"/admin/category/add" })
	public ModelAndView add(categoryForm categoryForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		catfValidator.validate(categoryForm, errors);
		if (errors.hasErrors()) {
			return add(session);
		}
		Category c = categoryForm.build();
		catRepo.add(c);
		return categories(session);
	}

	@RequestMapping(value = { "/categories", "/admin/categories" })
	public ModelAndView categories(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/list");
		mav.addObject("itemList", catRepo.getAll());
		mav.addObject("itemTitle", "Categorias");
		mav.addObject("linkAdmin", "category");
		mav.addObject("showingCategories", "true");

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/category/delete",
			"/admin/category/delete" })
	public ModelAndView delete(@RequestParam("id") Category c,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		catRepo.delete(c);

		return categories(session);
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/category/edit",
			"/admin/category/edit" })
	public ModelAndView edit(categoryForm categoryForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		Integer id = categoryForm.getId();
		Category c = catRepo.get(id);
		catfValidator.validate(categoryForm, errors);
		if (errors.hasErrors()) {
			return edit(c, session);
		}
		c.setName(categoryForm.getName());
		return categories(session);

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/category/edit",
			"/admin/category/edit" })
	public ModelAndView edit(@RequestParam("id") Category category,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/edit");
		mav.addObject(new categoryForm());
		mav.addObject("item", category);
		mav.addObject("title", "Categoria");
		mav.addObject("linkAdmin", "category");
		mav.addObject("imageType", "cat");

		return mav;
	}

}
