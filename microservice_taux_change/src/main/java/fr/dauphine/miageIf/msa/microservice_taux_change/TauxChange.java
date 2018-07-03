package fr.dauphine.miageIf.msa.microservice_taux_change;

import javax.persistence.*;

@Entity
public class TauxChange {

    @Id
    @GeneratedValue
    private long idTauxChange;

    private String deviseSource;

    private String deviseDest;

    private double taux;

    @Column(name = "date_taux")
    private String dateTaux;

    public TauxChange(){}

    public TauxChange(long idTauxChange, String deviseSource, String deviseDest, double taux, String dateTaux){
        this.idTauxChange = idTauxChange;
        this.deviseSource = deviseSource;
        this.deviseDest = deviseDest;
        this.taux = taux;
        this.dateTaux = dateTaux;
    }

    public TauxChange(String deviseSource, String deviseDest, double taux, String dateTaux){
        this.deviseSource = deviseSource;
        this.deviseDest = deviseDest;
        this.taux = taux;
        this.dateTaux = dateTaux;
    }

    public long getIdTauxChange() {
        return idTauxChange;
    }

    public double getTaux() {
        return taux;
    }

    public String getDateTaux() {
        return dateTaux;
    }

    public String getDeviseDest() {
        return deviseDest;
    }

    public String getDeviseSource() {
        return deviseSource;
    }

    public void setDateTaux(String dateTaux) {
        this.dateTaux = dateTaux;
    }

    public void setDeviseDest(String deviseDest) {
        this.deviseDest = deviseDest;
    }

    public void setDeviseSource(String deviseSource) {
        this.deviseSource = deviseSource;
    }

    public void setIdTauxChange(long idTauxChange) {
        this.idTauxChange = idTauxChange;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String toString(){
        return "{"+
                "idTauxChange = " + idTauxChange +
                ", deviseSource = '"+ deviseSource + '\'' +
                ", deviseDest = '"+ deviseDest + '\'' +
                ", dateTaux = '"+ deviseSource + '\'' +
                ", taux =" + taux+ '}';
    }
}

