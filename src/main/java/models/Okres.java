package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "okres")
public class Okres {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private Date dataPoczatku;
    private Date dataKonca;
    private boolean czyZastepstwo;
    @ManyToOne
    private Klub idKlubu;
    @ManyToMany
    private List<Trener> idTrenera;

    public Okres(long id, Date dataPoczatku, Date dataKonca, boolean czyZastepstwo, Klub idKlubu, List<Trener> idTrenera) {
        this.id = id;
        this.dataPoczatku = dataPoczatku;
        this.dataKonca = dataKonca;
        this.czyZastepstwo = czyZastepstwo;
        this.idKlubu = idKlubu;
        this.idTrenera = idTrenera;
    }

    public Okres() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataPoczatku() {
        return dataPoczatku;
    }

    public void setDataPoczatku(Date dataPoczatku) {
        this.dataPoczatku = dataPoczatku;
    }

    public Date getDataKonca() {
        return dataKonca;
    }

    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    public boolean isCzyZastepstwo() {
        return czyZastepstwo;
    }

    public void setCzyZastepstwo(boolean czyZastepstwo) {
        this.czyZastepstwo = czyZastepstwo;
    }

    public Klub getIdKlubu() {
        return idKlubu;
    }

    public void setIdKlubu(Klub idKlubu) {
        this.idKlubu = idKlubu;
    }

    public List<Trener> getIdTrenera() {
        return idTrenera;
    }

    public void setIdTrenera(List<Trener> idTrenera) {
        this.idTrenera = idTrenera;
    }
}
