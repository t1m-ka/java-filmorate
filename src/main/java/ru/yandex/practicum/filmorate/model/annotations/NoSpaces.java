package ru.yandex.practicum.filmorate.model.annotations;

import ru.yandex.practicum.filmorate.model.validators.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface NoSpaces {
    String message() default "не может содержать пробелы";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
