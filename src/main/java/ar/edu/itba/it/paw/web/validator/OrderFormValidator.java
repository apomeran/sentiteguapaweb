package ar.edu.itba.it.paw.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.forms.orderForm;

@Component
public class OrderFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return orderForm.class.equals(clazz);
	}

	public OrderFormValidator() {
	}

	public void validate(Object target, Errors errors) {
		orderForm form = (orderForm) target;
		if (form.getAddress().isEmpty())
			errors.reject("order.emptyaddress");
		if (form.getCity().isEmpty())
			errors.reject("order.emptycity");
		if (form.getCustomerName().isEmpty())
			errors.reject("order.emptycustomername");
		if (form.getEmail().isEmpty())
			errors.reject("order.emptyemail");
		if (form.getIvacondition().isEmpty())
			errors.reject("order.emptyivacondition");
		if (form.getPhone().isEmpty())
			errors.reject("order.emptyphone");
		if (form.getState().isEmpty())
			errors.reject("order.emptystate");
		if (!Utils.isEmail(form.getEmail()))
			errors.reject("order.badmail");

	}
}
