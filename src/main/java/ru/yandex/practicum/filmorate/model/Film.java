package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Film {
    int id;

    @NotBlank
    String name;

    @NotBlank
    @Size(max = 200)
    String description;

    @PastOrPresent
    @CorrectFilmDate
    LocalDate releaseDate;

    @NotNull
    @Positive
    int duration;
}


