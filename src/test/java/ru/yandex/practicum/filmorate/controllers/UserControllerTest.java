package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private User userSample;

    @BeforeEach
    void createUserSample() {
        userSample = new User(1,"user@mail.ru", "login",
                "userName", LocalDate.parse("2000-01-01"));
    }

    @Test
    void createUserWithoutEmail() {
        User user = userSample.toBuilder().email(null).build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createUserWithEmptyEmail() {
        User user = userSample.toBuilder().email("").build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createUserWithWrongEmail() {
        User user = userSample.toBuilder().email("wrong email").build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createUserWithoutLogin() {
        User user = userSample.toBuilder().login(null).build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(2, violationSet.size());
    }

    @Test
    void createUserWithEmptyLogin() {
        User user = userSample.toBuilder().login("  ").build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(2, violationSet.size());
    }

    @Test
    void createUserWithWrongLogin() {
        User user = userSample.toBuilder().login("login with spaces").build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createUserWithoutName() {
        User user = userSample.toBuilder().name("").build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(0, violationSet.size());
    }

    @Test
    void createUserWithoutBirthday() {
        User user = userSample.toBuilder().birthday(null).build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createUserWithWrongBirthday() {
        User user = userSample.toBuilder().birthday(LocalDate.parse("2025-05-15")).build();

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createValidUser() {
        Set<ConstraintViolation<User>> violationSet = validator.validate(userSample);
        assertEquals(0, violationSet.size());
    }
}