package at.michaelkoenig.lab04_1.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Michael König
 */
@Entity
@Table(name = "einzahlung")
public class Einzahlung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ein_id")
    private Integer id;

    @Basic
    @Column(name = "ein_betrag")
    @Min(5)
    private BigDecimal betrag;

    @Basic
    @Column(name = "ein_datum")
    @PastOrPresent
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "ein_sch_kn", nullable = false)
    private Schueler schueler;

    public Integer getId() {
        return id;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public LocalDate getDate() {
        return date;
    }

    public Schueler getSchueler() {
        return schueler;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSchueler(Schueler schueler) {
        this.schueler = schueler;
    }
}
