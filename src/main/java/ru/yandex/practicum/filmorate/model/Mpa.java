package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class Mpa {
    @NotNull
    int id;

    @NotNull
    String name;
}
