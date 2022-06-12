package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity(name = "manager")
public class Manager extends Pracownik{

    private double dodatek;

    public Manager(long id, String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, Long idPracownika, LocalDate dataZatrudnienia, Double pensja, double dodatek) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, idPracownika, dataZatrudnienia, pensja);
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
