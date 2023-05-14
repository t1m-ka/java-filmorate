package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.ValidationException;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserStorage userStorage;

    @Autowired
    public UserController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("Получен запрос к эндпоинту: /GET /users");
        return userStorage.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long userId) {
        log.info("Получен запрос к эндпоинту: /GET /users/" + userId);
        return null;
    }

    @GetMapping("/{id}/friends")
    public List<User> getUserFriends(@PathVariable("id") long userId) {
        log.info("Получен запрос к эндпоинту: /GET /users/" + userId + "/friends");
        return null;
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonUserFriends(@PathVariable("id") long userId, @PathVariable long otherUserId) {
        log.info("Получен запрос к эндпоинту: /PUT /users/" + userId + "/friends/" + otherUserId);
        return null;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Получен запрос к эндпоинту: /POST /users");
        return userStorage.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /users");
        User result = userStorage.updateUser(user);
        if (result == null)
            throw new ValidationException("Фильм с id=" + user.getId() + " не найден");
        return result;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info("Получен запрос к эндпоинту: /PUT /users/" + userId + "/friends/" + friendId);
        return null;
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info("Получен запрос к эндпоинту: /DELETE /users/" + userId + "/friends/" + friendId);
    }
}
