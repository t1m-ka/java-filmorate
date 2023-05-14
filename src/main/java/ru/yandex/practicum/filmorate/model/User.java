package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.yandex.practicum.filmorate.model.annotation.NoSpaces;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    long id;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @NoSpaces
    String login;

    String name;

    @NotNull
    @Past
    LocalDate birthday;
}
