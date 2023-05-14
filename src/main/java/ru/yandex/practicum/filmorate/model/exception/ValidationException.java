package ru.yandex.practicum.filmorate.model.exception;

import java.io.IOException;

public class ValidationException extends IOException {
    public ValidationException(String message) {
        super(message);
    }
}
