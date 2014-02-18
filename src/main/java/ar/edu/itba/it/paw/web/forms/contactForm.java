package ar.edu.itba.it.paw.web.forms;

import java.util.Date;

import ar.edu.itba.it.paw.domain.contact.Contact;

public class contactForm {

	private String contactName;
	private int phone;
	private String message;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String email;

	public contactForm() {
	}

	public Contact build() {
		return new Contact(contactName, phone, email, new Date(), message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
