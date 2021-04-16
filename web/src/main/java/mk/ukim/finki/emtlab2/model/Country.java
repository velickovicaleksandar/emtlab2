package mk.ukim.finki.emtlab2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Country {
    //id (Long), name (String), continent (String).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String continent;

    public Country() {

    }

    public Country(Long id, String name, String continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }
}
