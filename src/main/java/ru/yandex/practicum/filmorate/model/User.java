package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.annotation.NoSpaces;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    private long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NoSpaces
    private String login;

    private String name;

    @NotNull
    @Past
    private LocalDate birthday;
}
