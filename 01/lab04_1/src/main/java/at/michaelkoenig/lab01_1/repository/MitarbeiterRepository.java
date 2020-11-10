package at.michaelkoenig.lab01_1.repository;

import at.michaelkoenig.lab01_1.model.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Michael König
 */
@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, String> {
}
