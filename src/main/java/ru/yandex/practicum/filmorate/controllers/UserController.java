package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exceptions.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final List<User> users = new ArrayList<>();
    private int userId = 1;

    @GetMapping
    public List<User> getUsers() {
        log.info("Получен запрос к эндпоинту: /GET /users");
        return users;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /POST /users");
        if (user == null) {
            throw new ValidationException("Тело запроса не содержит объекта");
        }
        User newUser;
        if (user.getName() == null || user.getName().isBlank()) {
            newUser = user.toBuilder().id(userId++).name(user.getLogin()).build();
        } else {
            newUser = user.toBuilder().id(userId++).build();
        }
        users.add(newUser);
        log.info("Пользователь успешно создан, id = " + newUser.getId() + ". Всего пользователей " + users.size());
        return newUser;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /users");
        if (user == null) {
            throw new ValidationException("Тело запроса не содержит объекта");
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                log.info("Пользователь с id = " + user.getId() + " успешно обновлен.");
                return user;
            }
        }
        throw new ValidationException("Пользователь с id=" + user.getId() + " не найден");
    }
}
