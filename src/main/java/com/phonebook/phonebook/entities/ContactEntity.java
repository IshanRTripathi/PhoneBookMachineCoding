package com.phonebook.phonebook.entities;

import java.util.UUID;

import javax.annotation.Generated;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@Builder
@Getter
@ToString
public class ContactEntity {
    @Id
    String id;
    String firstName;
    String lastName;
    String phoneNumber;
    String emailId;
}
