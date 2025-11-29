package ru.netology.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PosterManagerBoundaryTest {

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

        Movie[] last = manager.findLast();
        assertEquals(3, last.length);
        assertEquals(testMovies[2], last[0]);
        assertEquals(testMovies[1], last[1]);
        assertEquals(testMovies[0], last[2]);
    }

    @Test
    void shouldHandleSingleMovie() {
        PosterManager manager = new PosterManager();
        Movie movie = new Movie("Одинокий рейнджер", "вестерн");

        manager.addMovie(movie);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertEquals(movie, all[0]);

        Movie[] last = manager.findLast();
        assertEquals(1, last.length);
        assertEquals(movie, last[0]);
    }
}