package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "manager")
public class Manager extends Pracownik {

    private double dodatek;

    private String haslo;

    public Manager(String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, LocalDate dataZatrudnienia, Double pensja, double dodatek,String haslo) {
        super(imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, dataZatrudnienia, pensja);
        this.dodatek = dodatek;
        this.haslo=haslo;
    }

    public Manager(Klient klient, LocalDate dataZatrudnienia, Double pensja, double dodatek,String haslo) {
        super(klient, dataZatrudnienia, pensja);
        this.dodatek = dodatek;
        this.haslo=haslo;
    }

    @Override
    Double obliczDodatek() {
        return null;
    }

    public Manager() {
    }

    public double getDodatek() {
        return dodatek;
    }

    public void setDodatek(double dodatek) {
        this.dodatek = dodatek;
    }
}
