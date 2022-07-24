package com.phonebook.phonebook.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.phonebook.phonebook.entities.ContactEntity;
import com.phonebook.phonebook.repository.ContactRepository;
import com.phonebook.phonebook.requestresponse.request.CreateContactRequest;
import com.phonebook.phonebook.service.exception.ContactException;
import com.phonebook.phonebook.validation.Validator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneBookService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private Validator validator;

    PhoneBookService() {

    }

    public Object createNewContact(CreateContactRequest request) {
        validator.validate(request, null);
        validator.validateRequest(request);
        contactRepository.getContacts().put(request.getId(), ContactEntity.builder()
            .id(request.getId())
            .emailId(request.getEmailId())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .phoneNumber(request.getPhoneNumber())
            .build());
        return contactRepository.getContacts().get(request.getId());
    }

    public Object searchDirectory(String query) {
        Set<ContactEntity> matchedContacts = new HashSet<>();
        contactRepository.getContacts().values().forEach(contactEntity -> {
            if (contactEntity.getFirstName().startsWith(query)
                || contactEntity.getLastName().startsWith(query)
                || contactEntity.getPhoneNumber().contains(query)
                || contactEntity.getEmailId().startsWith(query)) {
                matchedContacts.add(contactEntity);
            }
        });
        return matchedContacts;
    }

    public Object deleteContact(String id) {
        if(!contactRepository.getContacts().containsKey(id)) {
            log.error("No contact associated with Id present in DB: {}", id);
            throw new ContactException("Id doesn't exist to delete");
        }
        ContactEntity entity = contactRepository.getContacts().get(id);
        contactRepository.getContacts().remove(id);
        return entity;
    }
}
