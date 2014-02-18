package ar.edu.itba.it.paw.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.contact.Contact;
import ar.edu.itba.it.paw.domain.contact.ContactRepo;
import ar.edu.itba.it.paw.domain.orders.OrderLineRepo;
import ar.edu.itba.it.paw.domain.orders.OrderRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.forms.contactForm;
import ar.edu.itba.it.paw.web.validator.ContactFormValidator;

@Controller
public class ContactController extends BaseController {

	private ContactRepo contactRepo;
	private ContactFormValidator contactfValidator;

	@Autowired
	public ContactController(OrderRepo orderRepo,
			ContactFormValidator contactfValidator, ContactRepo contactRepo,
			OrderLineRepo orderLineRepo) {
		this.contactfValidator = contactfValidator;
		this.contactRepo = contactRepo;
	}

	@RequestMapping(value = { "admin/contacts" })
	public ModelAndView contacts(HttpSession session) {
		if (!isLoggedIn(session)) {
			return login(session);
		}
		ModelAndView mav = new ModelAndView("contacts/list");
		List<Contact> o = contactRepo.list();
		Collections.sort(o);
		mav.addObject("contacts", o);
		return mav;
	}

	@RequestMapping(value = { "admin/contact/delete" })
	public ModelAndView delete(@RequestParam("id") Contact contact,
			HttpSession session) {

		contactRepo.delete(contact);
		return contacts(session);
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/contact",
			"/index/contact" })
	public EnhancedModelAndView order(HttpSession session) {
		EnhancedModelAndView mav = generateContext("Contacto", true, true);
		mav.setViewName("contacts/contactform");
		mav.addObject(new contactForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/contact",
			"/index/contact" })
	public EnhancedModelAndView order(contactForm contactForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session)) {
			return (EnhancedModelAndView) login(session);
		}
		contactfValidator.validate(contactForm, errors);
		if (errors.hasErrors()) {
			return order(session);
		}
		Contact contact = contactForm.build();

		contactRepo.add(contact);
		EnhancedModelAndView mav = generateContext("Contacto Enviado", true,
				true);
		mav.addObject("contact", contact);
		mav.setViewName("contacts/success");
		return mav;
	}
}
