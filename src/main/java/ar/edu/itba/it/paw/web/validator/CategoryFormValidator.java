package ar.edu.itba.it.paw.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.products.CategoryRepo;
import ar.edu.itba.it.paw.web.forms.categoryForm;

@Component
public class CategoryFormValidator implements Validator {

	private CategoryRepo categoryRepo;

	public boolean supports(Class<?> clazz) {
		return categoryForm.class.equals(clazz);
	}

	@Autowired
	public CategoryFormValidator(CategoryRepo repo) {
		this.categoryRepo = repo;

	}

	public void validate(Object target, Errors errors) {
		categoryForm form = (categoryForm) target;
		if (form.getName().isEmpty())
			errors.reject("category.emptyname");
	}
}
