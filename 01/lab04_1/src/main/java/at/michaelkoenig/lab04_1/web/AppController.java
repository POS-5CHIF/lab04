package at.michaelkoenig.lab04_1.web;

import at.michaelkoenig.lab04_1.exception.RestException;
import at.michaelkoenig.lab04_1.model.Einzahlung;
import at.michaelkoenig.lab04_1.model.Schueler;
import at.michaelkoenig.lab04_1.repository.EinzahlungRepository;
import at.michaelkoenig.lab04_1.repository.SchuelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael König
 */
@RestController
public class AppController {
    private final SchuelerRepository schuelerRepository;
    private final EinzahlungRepository einzahlungRepository;

    public AppController(SchuelerRepository schuelerRepository, EinzahlungRepository einzahlungRepository) {
        this.schuelerRepository = schuelerRepository;
        this.einzahlungRepository = einzahlungRepository;
    }

    @GetMapping("/einzahlungen/{kn}")
    public List<Einzahlung> getEinzahlungenBySchueler(@PathVariable Integer kn) {
        Optional<Schueler> schueler = schuelerRepository.findById(kn);
        if (!schueler.isPresent())
            throw new RestException("Can't find schueler " + kn, HttpStatus.NOT_FOUND);

        return einzahlungRepository.findAllBySchuelerKn(kn);
    }

    @GetMapping("/einzahlung/{id}")
    public Einzahlung getEinzahlung(@PathVariable Integer id) {
        Optional<Einzahlung> einzahlung = einzahlungRepository.findById(id);
        if (!einzahlung.isPresent())
            throw new RestException("Can't find einzahlung " + id, HttpStatus.NOT_FOUND);

        return einzahlung.get();
    }

    @GetMapping("/schueler/{minBetrag}")
    public List<Schueler> getEinzahlungen(@PathVariable BigDecimal minBetrag) {
        return schuelerRepository.findAllByMinBetrag(minBetrag);
    }

    @PostMapping("/einzahlung/{kn}")
    public ResponseEntity<Einzahlung> addEinzahlung(@Valid @RequestBody Einzahlung einzahlung, @PathVariable Integer kn) {
        if(einzahlung.getId() != null)
            throw new RestException("Einzahlung ID should be null", HttpStatus.BAD_REQUEST);
        Optional<Schueler> schueler = schuelerRepository.findById(kn);
        if (!schueler.isPresent())
            throw new RestException("Can't find schueler " + kn, HttpStatus.NOT_FOUND);

        einzahlung.setSchueler(schueler.get());
        einzahlung = einzahlungRepository.save(einzahlung);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/einzahlung/{id}").
                build(einzahlung.getId());
        return ResponseEntity.created(uri).body(einzahlung);
    }

}
