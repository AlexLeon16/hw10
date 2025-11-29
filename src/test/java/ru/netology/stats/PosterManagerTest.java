package ru.netology.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PosterManagerTest {

    @Test
    void shouldAddMovie() {
        PosterManager manager = new PosterManager();
        Movie movie = new Movie("Бладиот боевик", "боевик");

        manager.addMovie(movie);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertEquals(movie, all[0]);
    }

    @Test
    void shouldFindAllMoviesInOrder() {
        PosterManager manager = new PosterManager();
        Movie movie1 = new Movie("Бладиот боевик", "боевик");
        Movie movie2 = new Movie("Вперёд", "мультфильм");
        Movie movie3 = new Movie("Отель «Белград»", "комедия");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] all = manager.findAll();
        assertEquals(3, all.length);
        assertEquals(movie1, all[0]);
        assertEquals(movie2, all[1]);
        assertEquals(movie3, all[2]);
    }

    @Test
    void shouldFindLastMoviesDefaultLimit() {
        PosterManager manager = new PosterManager();

        // Добавляем больше фильмов, чем лимит по умолчанию (5)
        Movie[] testMovies = {
                new Movie("Бладиот боевик", "боевик"),
                new Movie("Вперёд", "мультфильм"),
                new Movie("Отель «Белград»", "комедия"),
                new Movie("Джентльмены", "боевик"),
                new Movie("Человек-невидимка", "ужасы"),
                new Movie("Тролли. Мировой тур", "мультфильм"),
                new Movie("Номер один", "комедия")
        };

        for (Movie movie : testMovies) {
            manager.addMovie(movie);
        }

        Movie[] last = manager.findLast();
        assertEquals(5, last.length); // Должны вернуться последние 5 фильмов

        // Проверяем порядок (последние добавленные должны быть первыми)
        assertEquals(testMovies[6], last[0]); // "Номер один"
        assertEquals(testMovies[5], last[1]); // "Тролли. Мировой тур"
        assertEquals(testMovies[4], last[2]); // "Человек-невидимка"
        assertEquals(testMovies[3], last[3]); // "Джентльмены"
        assertEquals(testMovies[2], last[4]); // "Отель «Белград»"
    }

    @Test
    void shouldFindLastMoviesCustomLimit() {
        PosterManager manager = new PosterManager(3); // Устанавливаем лимит 3

        Movie[] testMovies = {
                new Movie("Бладиот боевик", "боевик"),
                new Movie("Вперёд", "мультфильм"),
                new Movie("Отель «Белград»", "комедия"),
                new Movie("Джентльмены", "боевик")
        };

        for (Movie movie : testMovies) {
            manager.addMovie(movie);
        }

        Movie[] last = manager.findLast();
        assertEquals(3, last.length); // Должны вернуться последние 3 фильма

        assertEquals(testMovies[3], last[0]); // "Джентльмены"
        assertEquals(testMovies[2], last[1]); // "Отель «Белград»"
        assertEquals(testMovies[1], last[2]); // "Вперёд"
    }

    @Test
    void shouldFindLastMoviesWhenLessThanLimit() {
        PosterManager manager = new PosterManager(5);

        Movie[] testMovies = {
                new Movie("Бладиот боевик", "боевик"),
                new Movie("Вперёд", "мультфильм")
        };

        for (Movie movie : testMovies) {
            manager.addMovie(movie);
        }

        Movie[] last = manager.findLast();
        assertEquals(2, last.length); // Должны вернуться все 2 фильма

        assertEquals(testMovies[1], last[0]); // "Вперёд"
        assertEquals(testMovies[0], last[1]); // "Бладиот боевик"
    }

    @Test
    void shouldFindLastMoviesWhenEmpty() {
        PosterManager manager = new PosterManager();

        Movie[] last = manager.findLast();

        assertEquals(0, last.length);
    }

    @Test
    void shouldUseDefaultLimit() {
        PosterManager manager = new PosterManager();

        assertEquals(5, manager.getLimit());
    }

    @Test
    void shouldUseCustomLimit() {
        PosterManager manager = new PosterManager(7);

        assertEquals(7, manager.getLimit());
    }
}