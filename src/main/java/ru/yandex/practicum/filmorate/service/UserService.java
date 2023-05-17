package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.IncorrectRequestException;
import ru.yandex.practicum.filmorate.model.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final HashMap<Long, HashSet<Long>> friends = new HashMap<>();

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
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
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
    }

    public void deleteFriend(long userId, long friendId) {
        checkUserExist(userId);
        checkUserExist(friendId);
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
        if (friends.containsKey(userId)) {
            friends.get(userId).remove(friendId);
        }
        if (friends.containsKey(friendId)) {
            friends.get(friendId).remove(userId);
        }
    }

    public List<User> getUserFriends(long userId) {
        checkUserExist(userId);
        ArrayList<User> result = new ArrayList<>();
        for (long friend : friends.get(userId)) {
            result.add(userStorage.getUserById(friend));
        }
        return result;
    }

    public List<User> getCommonUserFriends(long userId, long otherUserId) {
        checkUserExist(userId);
        checkUserExist(otherUserId);
        if (userId == otherUserId)
            throw new IncorrectRequestException("Id пользователя и id другого пользователя не могут быть одинаковыми");
        ArrayList<User> result = new ArrayList<>();
        if (!friends.containsKey(userId) && !friends.containsKey(otherUserId)) {
            return result;
        }
        for (long user : friends.get(userId)) {
            for (long otherUser : friends.get(otherUserId)) {
                if (user == otherUser) {
                    result.add(userStorage.getUserById(user));
                }
            }
        }
        return result;
    }
}
