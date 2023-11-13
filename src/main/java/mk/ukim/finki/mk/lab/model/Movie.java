package mk.ukim.finki.mk.lab.model;

import lombok.Data;

@Data
public class Movie {
    private Long id;
    private String title;
    private String summary;
    private double rating;
    private Production production;

    public Movie(String title, String summary, double rating) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Summary: %s, Rating: %.2f", title, summary, rating);
    }

}
