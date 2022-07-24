package com.phonebook.phonebook.validation;

import java.util.Objects;

import com.phonebook.phonebook.repository.ContactRepository;
import com.phonebook.phonebook.requestresponse.request.CreateContactRequest;
import com.phonebook.phonebook.validation.exception.ContactValidationError;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
@Slf4j
public class Validator implements org.springframework.validation.Validator {

    @Autowired
    private ContactRepository contactRepository;

    public void validateRequest(CreateContactRequest request) {
        var contacts = contactRepository.getContacts();

        StringBuilder validationError= new StringBuilder();
        if(contacts.get(request.getId()) != null) {
            validationError.append(String.format("Contact with same Id already present: %s", contacts.get(request.getId()))).append("\n");
        }
        String phoneNumber = request.getPhoneNumber();
        if(phoneNumber.length()!= 10 && !phoneNumber.startsWith("9")) {
            validationError.append(String.format("Invalid phone number, should be 10 digits and start with 9: %s", phoneNumber)).append("\n");
        }

        String email = request.getEmailId();
        if(!email.endsWith("@gmail.com") || Objects.requireNonNull(email).split("@")[0].length()==0) {
            log.info("email.endsWith(\"@gmail.com\"): {}", email.endsWith("@gmail.com"));
            log.info("email.split(\"@\")[0].length()>0): {}", email.split("@")[0].length()>0);
            validationError.append(String.format("Email should end with @gmail.com and should have a prefix: %s", email)).append("\n");
        }

        if(validationError.toString().length()>0){
            log.error(validationError.toString());
            throw new ContactValidationError(validationError.toString());
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateContactRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        CreateContactRequest request = (CreateContactRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", String.valueOf(HttpStatus.BAD_REQUEST), "firstName should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", String.valueOf(HttpStatus.BAD_REQUEST), "lastName should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", String.valueOf(HttpStatus.BAD_REQUEST), "phoneNumber should not be empty");
    }
}
