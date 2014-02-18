package ar.edu.itba.it.paw.domain.contact;

import java.util.Date;

import javax.persistence.Entity;

import ar.edu.itba.it.paw.domain.common.PersistentEntity;

@Entity
public class Contact extends PersistentEntity implements Comparable<Contact> {

	private String contactName;
	private int phone;
	private String email;
	private String message;
	private Date contactDate;

	public Contact(String contactName, int phone, String email, Date date,
			String message) {
		this.setContactName(contactName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setMessage(message);
		this.setContactDate(date);
	}

	Contact() {
	}

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

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	@Override
	public int compareTo(Contact otherContact) {
		return otherContact.contactDate.compareTo(contactDate);

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
