package ru.yandex.practicum.filmorate.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Mpa {
    @NotNull
    int id;

    @NotNull
    String name;
}
