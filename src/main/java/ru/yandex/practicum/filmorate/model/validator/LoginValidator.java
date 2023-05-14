package ru.yandex.practicum.filmorate.model.validator;

import ru.yandex.practicum.filmorate.model.annotation.NoSpaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<NoSpaces, String> {
    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return login != null && !login.contains(" ");
    }
}
