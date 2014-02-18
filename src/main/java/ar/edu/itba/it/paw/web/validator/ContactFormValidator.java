package ar.edu.itba.it.paw.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.forms.contactForm;

@Component
public class ContactFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return contactForm.class.equals(clazz);
	}

	public ContactFormValidator() {
	}

	public void validate(Object target, Errors errors) {
		contactForm form = (contactForm) target;
		if (form.getContactName().isEmpty())
			errors.reject("contact.emptyname");
		if (form.getEmail().isEmpty())
			errors.reject("contact.emptyemail");
		if (form.getPhone() == 0)
			errors.reject("contact.emptyphone");
	
//		if (!Utils.isEmail(form.getEmail()))
//			errors.reject("contact.badmail");

	}
}
