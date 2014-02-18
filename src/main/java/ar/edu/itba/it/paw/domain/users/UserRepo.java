package ar.edu.itba.it.paw.domain.users;

import ar.edu.itba.it.paw.domain.common.BaseRepo;
import ar.edu.itba.it.paw.web.forms.LoginForm;

public interface UserRepo extends BaseRepo<User> {
	
	public User get(String email);
	public User get(Integer id);
	public boolean checkLogin(LoginForm form);
	
	 /**
     * Checks if email already exists
     */
    public boolean emailExists(String email);

    /**
     * Checks if user name already exists
     */
    public boolean usernameExists(String username);
    
	public User login(String email, String password);

    /**
     * Returns a User by username and password
     */
	
}
