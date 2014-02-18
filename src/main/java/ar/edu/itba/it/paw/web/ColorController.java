package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.images.SGColorImage;
import ar.edu.itba.it.paw.domain.images.SGColorImageRepo;
import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.domain.products.ProductColor;
import ar.edu.itba.it.paw.domain.products.ProductColorRepo;
import ar.edu.itba.it.paw.domain.products.ProductRepo;
import ar.edu.itba.it.paw.web.forms.colorForm;

@Controller
public class ColorController extends BaseController {

	private ProductColorRepo prodColorRepo;
	private SGColorImageRepo imgRepo;

	@Autowired
	public ColorController(ProductRepo productRepo,
			ProductColorRepo prodColorRepo, CategoryRepo catRepo,
			SGColorImageRepo sgImageColorRepo) {
		this.prodColorRepo = prodColorRepo;
		this.imgRepo = sgImageColorRepo;
	}

	@RequestMapping(value = { "/colors", "/admin/colors" })
	public ModelAndView colors(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/list");
		mav.addObject("itemList", prodColorRepo.getAll());
		mav.addObject("showingColors", true);

		mav.addObject("itemTitle", "Colores");
		mav.addObject("linkAdmin", "color");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/color/edit" })
	public ModelAndView edit(@RequestParam("id") ProductColor color,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/edit");
		mav.addObject(new colorForm());
		mav.addObject("item", color);
		mav.addObject("title", "Color");
		mav.addObject("linkAdmin", "color");
		mav.addObject("imageType", "color");

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/color/imagecolorlist" })
	public ModelAndView imagelist(@RequestParam("id") ProductColor color,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/imagelist");
		mav.addObject("itemList", color.getColorimages());
		mav.addObject("showingImages", true);
		mav.addObject("linkAdmin", "color");
		mav.addObject("imgauxid", color.getId());
		mav.addObject("title", "Imagenes");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/color/imagedelete" })
	public ModelAndView imagedelete(@RequestParam("pid") ProductColor color,
			@RequestParam("id") SGColorImage image, HttpSession session) {

		if (!isLoggedIn(session)) {
			return login(session);
		}
		color.getColorimages().remove(image);
		imgRepo.delete(image);
		return imagelist(color, session);

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/admin/color/edit" })
	public ModelAndView edit(colorForm colorForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ProductColor prodColor = prodColorRepo.get(colorForm.getId());
		colorfValidator.validate(colorForm, errors);
		if (errors.hasErrors()) {
			return edit(prodColor, session);
		}
		prodColor.setName(colorForm.getName());
		return colors(session);

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/admin/color/delete" })
	public ModelAndView delete(@RequestParam("id") ProductColor color,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		prodColorRepo.delete(color);
		return colors(session);

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/color/add",
			"/admin/color/add" })
	public ModelAndView add(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("admin/add");
		mav.addObject(new colorForm());
		mav.addObject("title", "Color");
		mav.addObject("linkAdmin", "color");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/color/add",
			"/admin/color/add" })
	public ModelAndView add(colorForm colorForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		colorfValidator.validate(colorForm, errors);
		if (errors.hasErrors()) {
			return add(session);
		}
		ProductColor prodcolor = colorForm.build();
		prodColorRepo.add(prodcolor);
		return colors(session);
	}

}
