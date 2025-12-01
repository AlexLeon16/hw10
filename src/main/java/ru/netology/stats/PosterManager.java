package ru.netology.stats;

public class PosterManager {
    private Movie[] movies;
    private int limit;

    public PosterManager() {
        this.limit = 5;
        this.movies = new Movie[0];
    }

    public PosterManager(int limit) {
        this.limit = limit;
        this.movies = new Movie[0];
    }

    public void addMovie(Movie movie) {
        Movie[] tmp = new Movie[movies.length + 1];
        for (int i = 0; i < movies.length; i++) {
            tmp[i] = movies[i];
        }
        tmp[tmp.length - 1] = movie;
        movies = tmp;
    }

    public Movie[] findAll() {
        return movies.clone();
    }

    public Movie[] findLast() {
        int resultLength;
        if (movies.length < limit) {
            resultLength = movies.length;
        } else {
            resultLength = limit;
        }

        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[movies.length - 1 - i];
        }
        return result;
    }

    public int getLimit() {
        return limit;
    }

    public int getMoviesCount() {
        return movies.length;
    }
}