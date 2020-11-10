package at.michaelkoenig.lab01_1.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Michael König
 */
@Entity
@Table(name = "taetigkeit")
public class Taetigkeit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taet_id")
    private Integer id;

    @Basic
    @Column(name = "taet_beschreibung")
    private String description;

    @Basic
    @Column(name = "taet_datum")
    private LocalDate date;

    @Basic
    @Column(name = "taet_dauer")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "taet_mit_id")
    private Mitarbeiter employee;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getDuration() {
        return duration;
    }

    public Mitarbeiter getEmployee() {
        return employee;
    }
}
