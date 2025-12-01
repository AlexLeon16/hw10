package ru.netology.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosterManagerBoundaryTest {

    @Test
    void shouldHandleExactLimit() {
        PosterManager manager = new PosterManager(3);

        Movie[] testMovies = {
                new Movie("Фильм 1", "жанр1"),
                new Movie("Фильм 2", "жанр2"),
                new Movie("Фильм 3", "жанр3")
        };

        for (Movie movie : testMovies) {
            manager.addMovie(movie);
        }

        Movie[] expected = {
                testMovies[2], // "Фильм 3"
                testMovies[1], // "Фильм 2"
                testMovies[0]  // "Фильм 1"
        };

        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(3, actual.length);
    }

    @Test
    void shouldHandleSingleMovie() {
        PosterManager manager = new PosterManager();
        Movie movie = new Movie("Одинокий рейнджер", "вестерн");

        manager.addMovie(movie);

        Movie[] expectedAll = {movie};
        Movie[] actualAll = manager.findAll();
        assertArrayEquals(expectedAll, actualAll);

        Movie[] expectedLast = {movie};
        Movie[] actualLast = manager.findLast();
        assertArrayEquals(expectedLast, actualLast);

        assertEquals(1, manager.getMoviesCount());
    }

    @Test
    void shouldHandleZeroLimit() {
        PosterManager manager = new PosterManager(0);

        manager.addMovie(new Movie("Фильм 1", "жанр1"));
        manager.addMovie(new Movie("Фильм 2", "жанр2"));

        Movie[] expected = new Movie[0];
        Movie[] actual = manager.findLast();

        assertArrayEquals(expected, actual);
        assertEquals(0, actual.length);
    }

    @Test
    void shouldHandleNegativeLimit() {

        PosterManager manager = new PosterManager(-1);

        manager.addMovie(new Movie("Фильм 1", "жанр1"));

    }
}