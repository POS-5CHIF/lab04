package at.michaelkoenig.lab04_1.repository;

import at.michaelkoenig.lab04_1.model.Einzahlung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michael König
 */
@Repository
public interface EinzahlungRepository extends JpaRepository<Einzahlung, Integer> {
    List<Einzahlung> findAllBySchuelerKn(Integer kn);
}
