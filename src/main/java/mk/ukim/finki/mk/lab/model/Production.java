package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String address;


    public Production(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Country: %s, Address: %s", name, country, address);
    }
}
