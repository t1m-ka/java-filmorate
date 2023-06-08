package ru.yandex.practicum.filmorate.model.exception;

public class MpaNotFoundException extends NullPointerException {
    public MpaNotFoundException(String message) {
        super(message);
    }
}
