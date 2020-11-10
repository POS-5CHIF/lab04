package at.michaelkoenig.lab01_1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael König
 */
@Entity
@Table(name="mitarbeiter")
public class Mitarbeiter {
    @Id

    @Column(name="mit_id", length=6)
    private String id;

    @Basic
    @Column(name="mit_zuname")
    private String zuname;

    @Basic
    @Column(name="mit_vorname")
    private String vorname;

    public String getId() {
        return id;
    }

    public String getZuname() {
        return zuname;
    }

    public String getVorname() {
        return vorname;
    }

    @OneToMany(mappedBy = "employee")
    List<Taetigkeit> taetigkeiten = new ArrayList<>();

}
