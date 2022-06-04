package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "manager")
public class Manager extends Pracownik{

    private double dodatek;

    public Manager(long id, String imie, String nazwisko, String email, Date dataUrodzenia, int numerTelefonu, Adres adres, Double pensja, Date dataZatrudnienia, double dodatek) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, pensja, dataZatrudnienia);
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
