package ru.yandex.practicum.filmorate.model.annotations;

import ru.yandex.practicum.filmorate.model.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface CorrectFilmDate {
    String message() default "не может быть раньше 28.12.1895 и позже настоящего времени";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
