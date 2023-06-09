package ru.yandex.practicum.filmorate.storage.friendship;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendshipStorage {
    void addFriend(long userId, long friendId);

    List<User> getUserFriends(long userId);

    List<User> getCommonUserFriends(long userId, long otherUserId);

    void deleteFriend(long userId, long friendId);
}
