package at.michaelkoenig.lab04_1.repository;

import at.michaelkoenig.lab04_1.model.Einzahlung;
import at.michaelkoenig.lab04_1.model.Schueler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Michael König
 */
@Repository
public interface SchuelerRepository extends JpaRepository<Schueler, Integer> {
    @Query("SELECT s from Schueler s inner join Einzahlung e on s = e.schueler group by e.schueler having sum(e.betrag) >= :minBetrag")
    List<Schueler> findAllByMinBetrag(BigDecimal minBetrag);
}