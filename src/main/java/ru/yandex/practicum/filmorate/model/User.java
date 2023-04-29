package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Value;
import ru.yandex.practicum.filmorate.model.annotations.NoSpaces;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class User {
    int id;

    @Email
    String email;

    @NotBlank
    @NoSpaces
    String login;

    String name;

    @Past
    LocalDate birthday;
}
