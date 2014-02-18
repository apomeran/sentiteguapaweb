package ar.edu.itba.it.paw.web.validator;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.products.Season;
import ar.edu.itba.it.paw.domain.products.Unisex;
import ar.edu.itba.it.paw.web.forms.productForm;

@Component
public class ProductFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return productForm.class.equals(clazz);
	}

	public ProductFormValidator() {

	}

	public void validate(Object target, Errors errors) {
		productForm form = (productForm) target;
		if (form.getName().isEmpty())
			errors.reject("product.emptyname");
		if (form.getPrice() == null)
			errors.reject("product.emptyprice");
		else if (form.getPrice() < 0)
			errors.reject("product.badprice");
		if (form.getCategories() == null)
			errors.reject("product.emptycategory");
		if (form.getColors() == null)
			errors.reject("color.emptycolors");
		if (form.getPrice().isNaN())
			errors.reject("product.badprice");
		if (!Arrays.asList(Unisex.values()).contains(form.getUnisex())) {
			errors.reject("product.emptyunisex");
		}
		if (form.getSizes() == null) {
			errors.reject("product.emptysizes");
		}
		if (!Arrays.asList(Season.values()).contains(form.getSeason())) {
			errors.reject("product.emptyseason");
		}

	}
}
