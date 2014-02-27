package ar.edu.itba.it.paw.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import ar.edu.itba.it.paw.utils.MailMail;
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
	public ModelAndView order(contactForm contactForm, Errors errors,
			HttpSession session) {

		contactfValidator.validate(contactForm, errors);
		if (errors.hasErrors()) {
			return order(session);
		}
		Contact contact = contactForm.build();
		contactRepo.add(contact);
		ModelAndView mav = new ModelAndView("contacts/success");
		mav.addObject("contact", contact);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Mail.xml");
		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail(
				"noreply.sentiteguapa@gmail.com",
				contact.getEmail(),
				"Sentite Guapa - Contacto Online",
				"Gracias por contactarte con SentiteGuapa.com \n\n En breve se estaran comunicando contigo \n \n Ante cualquier duda no dude contactarse con nosotros a traves de este mail: sentiteguapamoda@gmail.com \n\n");
		mm.sendMail(
				"noreply.sentiteguapa@gmail.com",
				"sentiteguapamoda@gmail.com",
				"Sentite Guapa - Se contactaron a traves del sitio",
				"Se contactaron desde el sitio \n\n "
						+ "La persona que se contacto es: "
						+ contact.getContactName() + "\n\n Su email: "
						+ contact.getEmail() + "\n\n y su mensaje: "
						+ contact.getMessage() + "\n\n");
		return mav;
	}
}
