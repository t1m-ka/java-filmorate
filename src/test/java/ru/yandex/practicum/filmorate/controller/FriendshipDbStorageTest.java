package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.friendship.FriendshipStorage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations="classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql" })
public class FriendshipDbStorageTest {
    private final FriendshipStorage friendshipStorage;

    @Test
    public void addFriendTest() {
        friendshipStorage.addFriend(2, 1);
        List<User> friends = friendshipStorage.getUserFriends(2);
        assertThat(friends).hasSize(2);
        assertThat(friends).element(0).hasFieldOrPropertyWithValue("name", "George");
    }

    @Test
    public void getUserFriendsTest() {
        List<User> friends = friendshipStorage.getUserFriends(1);
        assertThat(friends).hasSize(2);
        assertThat(friends).element(0).hasFieldOrPropertyWithValue("name", "valera");
    }

    @Test
    public void getCommonUserFriendsTest() {
        List<User> commonFriends = friendshipStorage.getCommonUserFriends(1, 2);
        assertThat(commonFriends).hasSize(1);
    }

    @Test
    public void deleteFriendTest() {
        friendshipStorage.deleteFriend(1,3);
        List<User> friends = friendshipStorage.getUserFriends(1);
        assertThat(friends).hasSize(1);
    }
}
