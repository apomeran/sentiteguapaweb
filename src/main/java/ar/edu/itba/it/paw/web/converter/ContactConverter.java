package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.contact.Contact;
import ar.edu.itba.it.paw.domain.contact.ContactRepo;

@Component
public class ContactConverter implements Converter<String, Contact> {

	private ContactRepo contactRepo;

	@Autowired
	public ContactConverter(ContactRepo contactRepo) {

		this.contactRepo = contactRepo;
	}

	@Override
	public Contact convert(String arg) {
		return contactRepo.get(Integer.valueOf(arg));
	}
}
