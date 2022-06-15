package com.programming.techie;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public static void setupAll(){
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should Create Contact")
    public void shouldCreateContact(){
        contactManager.addContact("Aretha", "Franklin", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Franklin", "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Aretha", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Aretha", "Franklin", null);
        });
    }
}