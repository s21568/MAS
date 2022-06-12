package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "osoba")
public abstract class Osoba {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String email;
    private int numerTelefonu;
    @ManyToOne
    private Adres adres;

    public Osoba(long id, String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.dataUrodzenia = dataUrodzenia;
        this.numerTelefonu = numerTelefonu;
        this.adres = adres;
    }

    public Osoba() {
    }


    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public int getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(int numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String pokazInfo() {
        return imie + " " + nazwisko + " " + (dataUrodzenia.getDayOfMonth() + 1) + "-" + dataUrodzenia.getMonth() + "-" + dataUrodzenia.getYear();
    }
}

