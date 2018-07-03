package fr.dauphine.miageIf.msa.controller;

import fr.dauphine.miageIf.msa.microservice_operation.Operation;
import fr.dauphine.miageIf.msa.microservice_operation.TauxChange;
import fr.dauphine.miageIf.msa.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.*;

@RestController
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;

//  get operation par id
    @GetMapping(value = "/Operation/{id}")
    public Operation getOperation(@PathVariable long id){
        return operationRepository.findById(id);
    }


    // Retrouve un taux de change par rapport aux parametres
    @GetMapping(value = "/Operation/from/{from}/to/{to}/date/{date}")
    public TauxChange getTauxChange(@PathVariable String from, @PathVariable String to, @PathVariable String date){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        uriVariables.put("date", date);
        String url = "http://localhost:8000/Taux/from/{from}/to/{to}/date/{date}";

        ResponseEntity<TauxChange> responseEntity = new RestTemplate().getForEntity(url, TauxChange.class,
                uriVariables);
        TauxChange response = responseEntity.getBody();
        return new TauxChange(response.getIdTauxChange(), from, to, response.getTaux(), response.getDateTaux());
    }


    // ajoute opération dans la table
    @RequestMapping(value = "/Operation/Add/montant/{montant}/from/{from}/to/{to}/date/{date}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> addOperation(@PathVariable String from, @PathVariable String to, @PathVariable double montant, @PathVariable String date){

        //récupération des taux de change
        TauxChange tc = getTauxChange(from, to, date);

        double amount = (double) montant * (double) tc.getTaux();
        Operation newOperation = operationRepository.save(new Operation(from, to, amount, tc.getDateTaux(), tc.getTaux()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getIdOperation())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // supprime operation dans la table
    @DeleteMapping(value = "/Operation/Delete/{id}")
    public String deleteById(@PathVariable long id){
        operationRepository.deleteById(id);
        return "Opération d'id : "+id+" supprimée.";
    }

    // Update operation
    @RequestMapping(value = "/Operation/Update/id/{id}/from/{from}/to/{to}/montant/{montant}/date/{date}/taux/{taux}")
    public String updateeOperation(@PathVariable long id, @PathVariable String from, @PathVariable String to, @PathVariable double montant, @PathVariable String date, @PathVariable double taux){
        double finalMontant = (double) montant * (double) taux;
        Operation updateOp = new Operation(id, from, to, finalMontant, date, taux);
        operationRepository.save(updateOp);
        return "Opération mise à jour : "+updateOp.toString();
    }

}
