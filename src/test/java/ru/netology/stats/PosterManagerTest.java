package ru.netology.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosterManagerTest {

    @Test
    void shouldAddMovie() {
        PosterManager manager = new PosterManager();
        Movie movie = new Movie("Бладиот боевик", "боевик");

        manager.addMovie(movie);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertArrayEquals(new Movie[]{movie}, all);
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

        Movie[] expected = {movie1, movie2, movie3};
        Movie[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindLastMoviesDefaultLimit() {
        PosterManager manager = new PosterManager();

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

        Movie[] expected = {
                testMovies[6], // "Номер один"
                testMovies[5], // "Тролли. Мировой тур"
                testMovies[4], // "Человек-невидимка"
                testMovies[3], // "Джентльмены"
                testMovies[2]  // "Отель «Белград»"
        };

        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(5, actual.length);
    }

    @Test
    void shouldFindLastMoviesCustomLimit() {
        PosterManager manager = new PosterManager(3);

        Movie[] testMovies = {
                new Movie("Бладиот боевик", "боевик"),
                new Movie("Вперёд", "мультфильм"),
                new Movie("Отель «Белград»", "комедия"),
                new Movie("Джентльмены", "боевик")
        };

        for (Movie movie : testMovies) {
            manager.addMovie(movie);
        }

        Movie[] expected = {
                testMovies[3], // "Джентльмены"
                testMovies[2], // "Отель «Белград»"
                testMovies[1]  // "Вперёд"
        };

        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(3, actual.length);
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

        Movie[] expected = {
                testMovies[1], // "Вперёд"
                testMovies[0]  // "Бладиот боевик"
        };

        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(2, actual.length);
    }

    @Test
    void shouldFindLastMoviesWhenEmpty() {
        PosterManager manager = new PosterManager();

        Movie[] expected = new Movie[0];
        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(0, actual.length);
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

    @Test
    void shouldReturnCorrectMoviesCount() {
        PosterManager manager = new PosterManager();

        assertEquals(0, manager.getMoviesCount());

        manager.addMovie(new Movie("Фильм 1", "жанр1"));
        assertEquals(1, manager.getMoviesCount());

        manager.addMovie(new Movie("Фильм 2", "жанр2"));
        assertEquals(2, manager.getMoviesCount());
    }
}