package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
@Entity(name = "rekord_cwiczenia")
public class RekordCwiczenia {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private String nazwaCwiczenia;
    private double iloscKg;
    private int iloscPowtorzen;
    private Date dataDodania;
    @ManyToOne
    private Klient idKlienta;

    public RekordCwiczenia(long id, String nazwaCwiczenia, double iloscKg, int iloscPowtorzen, Date dataDodania, Klient idKlienta) {
        this.id = id;
        this.nazwaCwiczenia = nazwaCwiczenia;
        this.iloscKg = iloscKg;
        this.iloscPowtorzen = iloscPowtorzen;
        this.dataDodania = new Date();
        this.idKlienta = idKlienta;
    }

    public RekordCwiczenia() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNazwaCwiczenia(String nazwaCwiczenia) {
        this.nazwaCwiczenia = nazwaCwiczenia;
    }

    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }

    public List<RekordCwiczenia> wyswietlRekordyKlienta(Klient klient) {
        return new ArrayList<RekordCwiczenia>();
    }

    public long getId() {
        return id;
    }

    public Klient getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Klient idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Date getDataDodania() {
        return dataDodania;
    }

    public String getNazwaCwiczenia() {
        return nazwaCwiczenia;
    }

    public double getIloscKg() {
        return iloscKg;
    }

    public void setIloscKg(double iloscKg) {
        this.iloscKg = iloscKg;
    }

    public int getIloscPowtorzen() {
        return iloscPowtorzen;
    }

    public void setIloscPowtorzen(int iloscPowtorzen) {
        this.iloscPowtorzen = iloscPowtorzen;
    }
}
