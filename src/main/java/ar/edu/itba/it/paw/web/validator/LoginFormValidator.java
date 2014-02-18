package ar.edu.itba.it.paw.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.web.forms.LoginForm;

@Component
public class LoginFormValidator implements Validator {

    private UserRepo userRepo;

	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}
	@Autowired
	public LoginFormValidator(UserRepo u){
		this.userRepo = u;
		
	}

	public void validate(Object target, Errors errors) {
		LoginForm form = (LoginForm) target;
		 if (!userRepo.checkLogin(form)) {
		  errors.reject("user.invalid");
		 }
		if (form.getEmail() == null
				|| !form.getEmail().matches(
						"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
			errors.reject("user.invalidmail");
	}
}
