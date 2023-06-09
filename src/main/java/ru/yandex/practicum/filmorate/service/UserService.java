package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.IncorrectRequestException;
import ru.yandex.practicum.filmorate.storage.friendship.FriendshipStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final FriendshipStorage friendshipStorage;

    public UserService(UserStorage userStorage, FriendshipStorage friendshipStorage) {
        this.userStorage = userStorage;
        this.friendshipStorage = friendshipStorage;
    }

    public List<User> getUsers() {
        return userStorage.getUsers();
    }

    public User getUserById(long userId) {
        return userStorage.getUserById(userId);
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public void addFriend(long userId, long friendId) {
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
        friendshipStorage.addFriend(userId, friendId);
    }

    public List<User> getUserFriends(long userId) {
        return friendshipStorage.getUserFriends(userId);
    }

    public List<User> getCommonUserFriends(long userId, long otherUserId) {
        if (userId == otherUserId)
            throw new IncorrectRequestException("Id пользователя и id другого пользователя не могут быть одинаковыми");
        return friendshipStorage.getCommonUserFriends(userId, otherUserId);
    }

    public void deleteFriend(long userId, long friendId) {
        if (userId == friendId)
            throw new IncorrectRequestException("Пользователь не может дружить с собой");
        friendshipStorage.deleteFriend(userId, friendId);
    }
}
