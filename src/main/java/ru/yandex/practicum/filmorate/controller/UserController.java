package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.IncorrectRequestException;
import ru.yandex.practicum.filmorate.model.exception.ValidationException;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("Получен запрос к эндпоинту: /GET /users");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long userId) {
        log.info("Получен запрос к эндпоинту: /GET /users/" + userId);
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getUserFriends(@PathVariable("id") long userId) {
        log.info("Получен запрос к эндпоинту: /GET /users/" + userId + "/friends");
        return userService.getUserFriends(userId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonUserFriends(@PathVariable("id") long userId, @PathVariable long otherUserId) {
        log.info("Получен запрос к эндпоинту: /PUT /users/" + userId + "/friends/" + otherUserId);
        if (userId == otherUserId)
            throw new IncorrectRequestException("Id пользователя и id пользователя не могут быть одинаковыми");
        return userService.getCommonUserFriends(userId, otherUserId);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Получен запрос к эндпоинту: /POST /users");
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /users");
        return userService.updateUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info("Получен запрос к эндпоинту: /PUT /users/" + userId + "/friends/" + friendId);
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
        userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") long userId, @PathVariable long friendId) {
        log.info("Получен запрос к эндпоинту: /DELETE /users/" + userId + "/friends/" + friendId);
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
        userService.deleteFriend(userId, friendId);
    }
}
