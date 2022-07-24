package com.phonebook.phonebook.controller;

import com.phonebook.phonebook.requestresponse.request.CreateContactRequest;
import com.phonebook.phonebook.service.PhoneBookService;
import com.phonebook.phonebook.validation.Validator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phonebook")
@Slf4j
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity addNewContact(@RequestBody CreateContactRequest request, BindingResult errors) {
        log.info("Received post request to create new contact: {}", request);
        validator.validate(request, errors);
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneBookService.createNewContact(request));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity searchContact(@PathVariable String query) {
        log.info("Received get request to search by: {}", query);
        return ResponseEntity.ok(phoneBookService.searchDirectory(query));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContact(@PathVariable String id) {
        return ResponseEntity.ok(phoneBookService.deleteContact(id));
    }
}
