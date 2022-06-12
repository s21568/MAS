package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "rozliczenie_miesieczne")
public class RozliczenieMiesieczne {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private LocalDate miesiacPokrycia;
    @ManyToMany
    private List<Przychod> listaPrzychodow;
    @ManyToMany
    private List<Koszt> listaKosztow;
    private Double sumaPelnaKosztow;
    private Double sumaPelnaPrzychodow;
    private LocalDate dataDodania;
    @ManyToOne
    private Manager idManageraAutoryzujacego;
    @ManyToOne
    private Klub idKlubu;

    public RozliczenieMiesieczne(long id, Klub idKlubu, LocalDate miesiacPokrycia, Manager idManageraAutoryzujacego) {
        this.id = id;
        this.miesiacPokrycia = miesiacPokrycia;
        this.listaPrzychodow = new ArrayList<>();
        this.listaKosztow = new ArrayList<>();
        this.sumaPelnaKosztow = 0.0;
        this.sumaPelnaPrzychodow = 0.0;
        this.dataDodania = LocalDate.now();
        this.idManageraAutoryzujacego = idManageraAutoryzujacego;
        this.idKlubu = idKlubu;
    }

    public RozliczenieMiesieczne() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSumaPelnaKosztow(Double sumaPelnaKosztow) {
        this.sumaPelnaKosztow = sumaPelnaKosztow;
    }

    public void setSumaPelnaPrzychodow(Double sumaPelnaPrzychodow) {
        this.sumaPelnaPrzychodow = sumaPelnaPrzychodow;
    }

    public void setDataDodania(LocalDate dataDodania) {
        this.dataDodania = dataDodania;
    }

    public void setIdKlubu(Klub idKlubu) {
        this.idKlubu = idKlubu;
    }

    public long getId() {
        return id;
    }

    public Klub getIdKlubu() {
        return idKlubu;
    }

    public Double getSumaPelnaKosztow() {
        return sumaPelnaKosztow;
    }

    public Double getSumaPelnaPrzychodow() {
        return sumaPelnaPrzychodow;
    }

    public LocalDate getDataDodania() {
        return dataDodania;
    }

    public LocalDate getMiesiacPokrycia() {
        return miesiacPokrycia;
    }

    public void setMiesiacPokrycia(LocalDate miesiacPokrycia) {
        this.miesiacPokrycia = miesiacPokrycia;
    }

    public Manager getIdManageraAutoryzujacego() {
        return idManageraAutoryzujacego;
    }

    public void setIdManageraAutoryzujacego(Manager idManageraAutoryzujacego) {
        this.idManageraAutoryzujacego = idManageraAutoryzujacego;
    }

    public List<Przychod> getListaPrzychodow() {
        return listaPrzychodow;
    }

    public void setListaPrzychodow(List<Przychod> listaPrzychodow) {
        this.listaPrzychodow = listaPrzychodow;
    }

    public List<Koszt> getListaKosztow() {
        return listaKosztow;
    }

    public void setListaKosztow(List<Koszt> listaKosztow) {
        this.listaKosztow = listaKosztow;
    }
}
