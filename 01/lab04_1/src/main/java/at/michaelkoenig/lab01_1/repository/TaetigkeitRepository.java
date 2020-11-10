package at.michaelkoenig.lab01_1.repository;

import at.michaelkoenig.lab01_1.model.Mitarbeiter;
import at.michaelkoenig.lab01_1.model.Taetigkeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michael König
 */
@Repository
public interface TaetigkeitRepository extends JpaRepository<Taetigkeit, Integer> {

    @Query("select coalesce(sum(t.duration), 0) from Taetigkeit t where t.employee = :m")
    Integer getTotalWorkingTimeByMitarbeiter(Mitarbeiter m);

    List<Taetigkeit> findAllByEmployeeEqualsAndDateBetween(Mitarbeiter mitarbeiter, LocalDate from, LocalDate to);

}
