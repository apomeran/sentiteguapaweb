package ar.edu.itba.it.paw.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.products.ProductColorRepo;
import ar.edu.itba.it.paw.web.forms.colorForm;

@Component
public class ColorFormValidator implements Validator {

    private ProductColorRepo prodColorRepo;

	public boolean supports(Class<?> clazz) {
		return colorForm.class.equals(clazz);
	}
	@Autowired
	public ColorFormValidator(ProductColorRepo p){
		this.prodColorRepo = p;
		
	}

	public void validate(Object target, Errors errors) {
		colorForm form = (colorForm) target;
		if (form.getName().isEmpty())
			errors.reject("color.emptyname");
	}
}
