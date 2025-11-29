package ru.netology.stats;

public class Movie {
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return java.util.Objects.equals(title, movie.title) &&
                java.util.Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, genre);
    }
}