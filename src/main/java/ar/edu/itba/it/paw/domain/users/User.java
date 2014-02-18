package ar.edu.itba.it.paw.domain.users;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;

@Entity
@Table(name="systemuser")
public class User extends PersistentEntity {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
}
