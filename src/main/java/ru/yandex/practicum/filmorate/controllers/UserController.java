package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final List<User> users = new ArrayList<>();
    private int userId = 1;

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @PostMapping
    public User createFilm(@Valid @RequestBody User user) {
        User newUser;
        if (user.getName() == null || user.getName().isBlank()) {
            newUser = user.toBuilder().id(userId++).name(user.getLogin()).build();
        } else {
            newUser = user.toBuilder().id(userId++).build();
        }
        users.add(newUser);
        return newUser;
    }

    @PutMapping
    public User updateFilm(@Valid @RequestBody User user) throws ValidationException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                return user;
            }
        }
        throw new ValidationException("Пользователь с id=" + user.getId() + " не найден");
    }
}
