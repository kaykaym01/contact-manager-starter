package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public void setupAll(){
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
    @DisplayName("Should Create Contact on Developer Machine")
    public void shouldCreateContactOnDEV(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Aretha", "Franklin", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Create Contact on Test Machine")
    public void shouldCreateContactOnTEST(){
        Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
        contactManager.addContact("Aretha", "Franklin", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Create Contact Only on MAC")
    @EnabledOnOs(value = OS.MAC,disabledReason = "Should Run only on MAC")
    public void shouldCreateContactOnlyOnMAC(){
        contactManager.addContact("Aretha", "Franklin", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Create Contact Only On Windows")
    @EnabledOnOs(value = OS.WINDOWS,disabledReason = "Should Run only on WINDOWS")
    public void shouldCreateContactOnlyOnWindows(){
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

    @AfterEach
    public void tearDown(){
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public void tearDownAll(){
        System.out.println("Should be executed at the end of the Test");
    }

    @Nested
    class RepeatedNestedTest{
        @RepeatedTest(value = 5,
                name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        @DisplayName("Repeat Contact Creation Test 5 Times")
        public void shouldTestContactCreationRepeatedly(){
            contactManager.addContact("Aretha", "Franklin", "0123456789");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }

    @ParameterizedTest
    @DisplayName("Method Source Case - Phone Number should match the required Format")
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber){
        contactManager.addContact("Aretha", "Franklin", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Nested
    class ParameterizedNestedTest{
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        @DisplayName("Value Source Case - Phone Number should match the required Format")
        public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber){
            contactManager.addContact("Aretha", "Franklin", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        @ParameterizedTest
        @DisplayName("CSV Source Case - Phone Number should match the required Format")
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber){
            contactManager.addContact("Aretha", "Franklin", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        @ParameterizedTest
        @DisplayName("CSV File Source Case - Phone Number should match the required Format")
        @CsvFileSource(resources="/data.csv")
        public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber){
            contactManager.addContact("Aretha", "Franklin", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }

}