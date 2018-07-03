package fr.dauphine.miageIf.msa.repository;

import fr.dauphine.miageIf.msa.microservice_taux_change.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TauxChangeRepository extends JpaRepository<TauxChange, Long> {

    TauxChange findById(long id);

    void deleteById(Long aLong);

    @Query("SELECT t FROM TauxChange t WHERE t.deviseSource=:from AND t.deviseDest=:to AND t.dateTaux = :dateTaux")
    TauxChange findTaux(@Param("from") String from, @Param("to") String to, @Param("dateTaux") String date);
}
