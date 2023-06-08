package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@AllArgsConstructor
public class Genre {
    @NotNull
    int id;

    @NotNull
    String name;
}
