package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity(name = "manager")
public class Manager extends Pracownik{

    private double dodatek;

    public Manager( String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, LocalDate dataZatrudnienia, Double pensja, double dodatek) {
        super( imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres,  dataZatrudnienia, pensja);
        this.dodatek = dodatek;
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
