package com.phonebook.phonebook.requestresponse.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {
    String id;
    String firstName;
    String lastName;
    String phoneNumber;
    String emailId;
}
