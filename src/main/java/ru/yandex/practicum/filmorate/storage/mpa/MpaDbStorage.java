package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

@Component
public class MpaDbStorage implements MpaStorage {
    @Override
    public List<Mpa> getAllMpa() {
        return null;
    }

    @Override
    public Mpa getMpaById(int mpaId) {
        return null;
    }
}
