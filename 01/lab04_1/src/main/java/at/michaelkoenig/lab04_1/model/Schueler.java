package at.michaelkoenig.lab04_1.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael König
 */
@Entity
@Table(name = "schueler")
public class Schueler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sch_kn")
    private Integer kn;

    @Basic
    @Column(name = "sch_zuname")
    @NotEmpty
    private String zuname;

    @Basic
    @Column(name = "sch_vorname")
    @NotEmpty
    private String vorname;

    public Integer getKn() {
        return kn;
    }

    public String getZuname() {
        return zuname;
    }

    public String getVorname() {
        return vorname;
    }

    @OneToMany(mappedBy = "schueler")
    List<Einzahlung> einzahlungen = new ArrayList<>();

}
