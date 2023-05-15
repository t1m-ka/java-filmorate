package ru.yandex.practicum.filmorate.model.exception;

public class UserNotFoundException extends NullPointerException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
