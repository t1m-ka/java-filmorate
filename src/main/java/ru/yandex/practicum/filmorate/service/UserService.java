package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;
    private final HashMap<Long, HashSet<Long>> friends = new HashMap<>();

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public List<User> getUsers() {
        return userStorage.getUsers();
    }

    public User getUserById(long userId) {
        checkUserExist(userId);
        return userStorage.getUserById(userId);
    }

    public void checkUserExist(long userId) {
        if (userStorage.getUserById(userId) == null)
            throw new UserNotFoundException("Пользователь с id=" + userId + " не найден");
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        checkUserExist(user.getId());
        return userStorage.updateUser(user);
    }

    public void addFriend(long userId, long friendId) {
        checkUserExist(userId);
        checkUserExist(friendId);
        if (friends.containsKey(userId)) {
            friends.get(userId).add(friendId);
        } else {
            friends.put(userId, new HashSet<>(Collections.singleton(friendId)));
        }
        if (friends.containsKey(friendId)) {
            friends.get(friendId).add(userId);
        } else {
            friends.put(friendId, new HashSet<>(Collections.singleton(userId)));
        }
        log.info("Пользователи id=" + userId + " и id=" + friendId + " теперь друзья.");
    }

    public void deleteFriend(long userId, long friendId) {
        checkUserExist(userId);
        checkUserExist(friendId);
        if (friends.containsKey(userId)) {
            friends.get(userId).remove(friendId);
        }
        if (friends.containsKey(friendId)) {
            friends.get(friendId).remove(userId);
        }
        log.info("Пользователи id=" + userId + " и id=" + friendId + " больше не друзья");
    }

    public List<User> getUserFriends(long userId) {
        ArrayList<User> result = new ArrayList<>();
        for (long friend : friends.get(userId)) {
            result.add(userStorage.getUserById(friend));
        }
        log.info("Количество друзей пользователя id=" + userId + " : " + result.size());
        return result;
    }
}
