package mk.ukim.finki.emtlab2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Author {
    // id (Long), name (String), surname (String), country (Country
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;


    public Author() {
    }

    public Author(long id, String name, String surname, Country country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
