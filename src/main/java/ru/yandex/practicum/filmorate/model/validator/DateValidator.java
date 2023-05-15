package ru.yandex.practicum.filmorate.model.validator;

import ru.yandex.practicum.filmorate.model.annotation.CorrectFilmDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<CorrectFilmDate, LocalDate> {
    public static final LocalDate MIN_DATE = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false;
        }
        return localDate.isAfter(MIN_DATE);
    }
}
