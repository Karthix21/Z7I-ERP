package com.example.z7I.service;

import com.example.z7I.dto.ContactRequest;
import com.example.z7I.model.Contact;
import com.example.z7I.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setName(contactRequest.getName());
        contact.setEmail(contactRequest.getEmail());
        contact.setMobileNumber(contactRequest.getMobileNumber());
        contact.setRegisterNumber(contactRequest.getRegisterNumber());
        
        return contactRepository.save(contact);
    }
}
