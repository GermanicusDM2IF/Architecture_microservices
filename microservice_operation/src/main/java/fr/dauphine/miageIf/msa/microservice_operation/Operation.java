package fr.dauphine.miageIf.msa.microservice_operation;

import javax.persistence.*;

@Entity
public class Operation {

    @Id
    @GeneratedValue
    private long idOperation;

    private String deviseSource;

    private String deviseDest;

    private double montant;

    @Column(name = "date_operation")
    private String dateOperation;

    private double taux;

    public Operation(){}

    public Operation(long idTransaction, String deviseSource, String deviseDest, double montant, String dateOperation, double taux){
        this.idOperation = idTransaction;
        this.deviseSource = deviseSource;
        this.deviseDest = deviseDest;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.taux = taux;
    }

    public Operation(String deviseSource, String deviseDest, double montant, String dateOperation, double taux){
        this.deviseSource = deviseSource;
        this.deviseDest = deviseDest;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.taux = taux;
    }

    public String getDeviseSource() {
        return deviseSource;
    }

    public String getDeviseDest() {
        return deviseDest;
    }

    public long getIdOperation() {
        return idOperation;
    }

    public double getTaux() {
        return taux;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setDeviseSource(String deviseSource) {
        this.deviseSource = deviseSource;
    }

    public void setIdOperation(long idOperation) {
        this.idOperation = idOperation;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void setDeviseDest(String deviseDest) {
        this.deviseDest = deviseDest;
    }

    public void setDateOperation(String dateTransaction) {
        this.dateOperation = dateOperation;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String toString(){
        return "{"+
                "idOperation = " + idOperation +
                ", deviseSource = '"+ deviseSource + '\'' +
                ", deviseDest = '"+ deviseDest + '\'' +
                ", dateOperation = '"+ dateOperation + '\'' +
                ", montant = '"+ montant + '\'' +
                ", taux =" + taux+ '}';
    }

}
