package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class Genre {
    @NotNull
    int id;

    @NotNull
    String name;
}
