package com.example.z7I.Controller;

import com.example.z7I.dto.ContactRequest;
import com.example.z7I.model.Contact;
import com.example.z7I.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = {"http://localhost:3000", "https://z7i.in"})
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody ContactRequest contactRequest) {
        Contact createdContact = contactService.createContact(contactRequest);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }
}
