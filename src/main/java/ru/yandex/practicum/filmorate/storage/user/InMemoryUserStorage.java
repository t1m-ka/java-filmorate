package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage{
    private final List<User> users = new ArrayList<>();
    private int userId = 1;

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User createUser(User user) {
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

    @Override
    public User updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                log.info("Пользователь с id = " + user.getId() + " успешно обновлен.");
                return user;
            }
        }
        return null;
    }
}