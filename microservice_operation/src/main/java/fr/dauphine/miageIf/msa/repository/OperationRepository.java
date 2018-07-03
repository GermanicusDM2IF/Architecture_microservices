package fr.dauphine.miageIf.msa.repository;

import fr.dauphine.miageIf.msa.microservice_operation.Operation;
import fr.dauphine.miageIf.msa.microservice_operation.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findById(long id);

    void deleteById(Long aLong);

}
