package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql"})
public class UserDbStorageTests {
    private final UserStorage userStorage;

    @Test
    public void getUsersTest() {
        List<User> allUsers = userStorage.getUsers();
        assertThat(allUsers).hasSize(3);
    }

    @Test
    public void getUserByIdTest() {
        User user = userStorage.getUserById(1);
        assertThat(user).hasFieldOrPropertyWithValue("id", 1L);
    }

    @Test
    public void createUserTest() {
        User newUser = userStorage.createUser(User.builder()
                .name("test")
                .email("test@test.ru")
                .login("test")
                .birthday(LocalDate.now())
                .build());
        assertThat(newUser).hasFieldOrPropertyWithValue("id", 4L);
    }

    @Test
    public void updateUserTest() {
        User updatedUser = userStorage.updateUser(User.builder()
                .id(3L)
                .name("updated")
                .email("test@test.ru")
                .login("test")
                .birthday(LocalDate.now())
                .build());
        assertThat(updatedUser).hasFieldOrPropertyWithValue("id", 3L);
        assertThat(updatedUser).hasFieldOrPropertyWithValue("name", "updated");
    }
}
