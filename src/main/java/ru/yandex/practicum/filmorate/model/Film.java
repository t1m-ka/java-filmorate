package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.model.annotation.CorrectFilmDate;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Film {

    private long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @NotNull
    @PastOrPresent
    @CorrectFilmDate
    private LocalDate releaseDate;

    @NotNull
    @Positive
    private int duration;

    private int rate;

    @NotNull
    private Mpa mpa;

    private HashSet<Genre> genres;
}


