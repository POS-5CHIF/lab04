package at.michaelkoenig.lab01_1.web;

import at.michaelkoenig.lab01_1.exception.RestException;
import at.michaelkoenig.lab01_1.model.Mitarbeiter;
import at.michaelkoenig.lab01_1.model.Taetigkeit;
import at.michaelkoenig.lab01_1.repository.MitarbeiterRepository;
import at.michaelkoenig.lab01_1.repository.TaetigkeitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael König
 */
@RestController
public class AppController {
    private final MitarbeiterRepository mitarbeiterRepository;
    private final TaetigkeitRepository taetigkeitRepository;

    public AppController(MitarbeiterRepository mitarbeiterRepository, TaetigkeitRepository taetigkeitRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.taetigkeitRepository = taetigkeitRepository;
    }

    @GetMapping(path = "/employees")
    public List<Mitarbeiter> getAllEmployees() {
        return mitarbeiterRepository.findAll();
    }

    @PostMapping("/employees")
    public ResponseEntity<Mitarbeiter> addMitarbeiter(@Valid @RequestBody Mitarbeiter mitarbeiter) {
        mitarbeiter = mitarbeiterRepository.save(mitarbeiter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/employee/{id}").
                build(mitarbeiter.getId());
        return ResponseEntity.created(uri).body(mitarbeiter);
    }

    @GetMapping("/employees/totalworkingtime/{empid}")
    public Integer getTotalWorkingTimeById(@PathVariable String empid) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterRepository.findById(empid);
        if (!mitarbeiter.isPresent())
            throw new RestException("Can't find employee " + empid, HttpStatus.NOT_FOUND);

        return taetigkeitRepository.getTotalWorkingTimeByMitarbeiter(mitarbeiter.get());
    }

    @GetMapping("/employees/activities/{empid}/{startdate}/{enddate}")
    public List<Taetigkeit> getActivitiesBetween(@PathVariable String empid, @PathVariable @DateTimeFormat(pattern = "yyyy-dd-MM") LocalDate startdate, @PathVariable @DateTimeFormat(pattern = "yyyy-dd-MM") LocalDate enddate) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterRepository.findById(empid);
        if (!mitarbeiter.isPresent())
            throw new RestException("Can't find employee " + empid, HttpStatus.NOT_FOUND);

        return taetigkeitRepository.findAllByEmployeeEqualsAndDateBetween(mitarbeiter.get(), startdate, enddate);
    }

}
