package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.yandex.practicum.filmorate.model.annotation.CorrectFilmDate;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class Film {
    long id;

    @NotBlank
    String name;

    @NotBlank
    @Size(max = 200)
    String description;

    @NotNull
    @PastOrPresent
    @CorrectFilmDate
    LocalDate releaseDate;

    @NotNull
    @Positive
    int duration;
}


