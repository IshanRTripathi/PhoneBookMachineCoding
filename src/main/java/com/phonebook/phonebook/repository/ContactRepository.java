package com.phonebook.phonebook.repository;

import java.util.HashMap;
import java.util.Map;

import com.phonebook.phonebook.entities.ContactEntity;

import lombok.Getter;

import org.springframework.stereotype.Repository;

@Repository
@Getter
public class ContactRepository {
    Map<String, ContactEntity> contacts;

    ContactRepository() {
        contacts = new HashMap<>();
        contacts.put("0", ContactEntity.builder()
            .id("0")
            .phoneNumber("9876543210")
            .firstName("ishan")
            .lastName("tripathi")
            .emailId("ishanrtripathi@gmail.com")
            .build());
        contacts.put("1", ContactEntity.builder()
            .id("1")
            .phoneNumber("9382957892")
            .firstName("pooks")
            .lastName("shukla")
            .emailId("pooksshukla@gmail.com")
            .build());
        contacts.put("2", ContactEntity.builder()
            .id("2")
            .phoneNumber("9876513210")
            .firstName("piuu")
            .lastName("tiuu")
            .emailId("piuutiuu@gmail.com")
            .build());
    }
}
