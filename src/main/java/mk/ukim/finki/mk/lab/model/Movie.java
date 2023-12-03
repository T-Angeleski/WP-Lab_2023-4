package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;

    @ManyToOne
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Summary: %s, Rating: %.2f", title, summary, rating);
    }

}
