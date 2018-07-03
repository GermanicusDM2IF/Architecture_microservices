package fr.dauphine.miageIf.msa.Controller;

import fr.dauphine.miageIf.msa.microservice_taux_change.TauxChange;
import fr.dauphine.miageIf.msa.repository.TauxChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TauxChangeController {

    @Autowired
    private TauxChangeRepository tauxChangeRepository;

//  Trouve taux de change par rapport à l'id
    @GetMapping(value = "/Taux/id/{id}")
    public TauxChange afficherTauxChange(@PathVariable long id){
        return tauxChangeRepository.findById(id);
    }

//  Liste de tous les taux de change
    @RequestMapping(value = "/Taux", method = RequestMethod.GET)
    public MappingJacksonValue listeTauxChange(){
        Iterable<TauxChange> tauxChanges = tauxChangeRepository.findAll();

        MappingJacksonValue tauxChangesFiltres = new MappingJacksonValue(tauxChanges);

        return tauxChangesFiltres;
    }

//  Supprime taux change avec l'id
    @DeleteMapping(value = "/Taux/Delete/{id}")
    public String deleteTauxChange(@PathVariable long id){
        tauxChangeRepository.deleteById(id);
        return "Taux de change d'id : "+id+" supprimé.";
    }


//  Ajoute taux de change
    @RequestMapping(value = "/Taux/Add/from/{from}/to/{to}/date/{date}/taux/{taux}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> addTauxChange(@PathVariable String from, @PathVariable String to, @PathVariable String date, @PathVariable double taux){

        TauxChange tc = tauxChangeRepository.save(new TauxChange(from, to, taux, date));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tc.getIdTauxChange())
                .toUri();

        return ResponseEntity.created(location).build();
    }

//  MAJ des taux de change
    @RequestMapping(value = "/Taux/Update/id/{id}/from/{from}/to/{to}/date/{dateTaux}/taux/{taux}")
    public String updateTaux(@PathVariable long id, @PathVariable String from, @PathVariable String to, @PathVariable String dateTaux, @PathVariable double taux){
        TauxChange updatedTc = new TauxChange(id, from, to, taux, dateTaux);
        tauxChangeRepository.save(updatedTc);
        return "Taux de change mis à jour : "+updatedTc.toString();
    }

//  Trouve taux change par rapport à la devise source, la devise destination et la date
    @GetMapping(value = "/Taux/from/{from}/to/{to}/date/{date}")
    public TauxChange findTaux(@PathVariable String from, @PathVariable String to, @PathVariable String date){
        if(tauxChangeRepository.findTaux(from, to, date)== null)
            return new TauxChange();
        return tauxChangeRepository.findTaux(from, to, date);
    }
}
