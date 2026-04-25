package app.kompleksitet;

import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private String title;
    private int rating;

    public Movie(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    //Comparable bruges af TreeSet
    @Override
    public int compareTo(Movie other) {
        return this.title.compareTo(other.title); //sorter efter titel
    }

    //equals bruges af HashSet
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    //hashCode bruges af HashSet
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}