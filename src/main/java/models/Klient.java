package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "klient")
public class Klient extends Osoba {
    private int iloscSesji;


    public Klient(long id, String imie, String nazwisko, String email, Date dataUrodzenia, int numerTelefonu, Adres adres) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres);
        this.iloscSesji = 0;
    }

    public Klient() {
    }


    public int getIloscSesji() {
        return iloscSesji;
    }

    public void setIloscSesji(int iloscSesji) {
        this.iloscSesji = iloscSesji;
    }

    public int sprawdzIloscSesji() {
        return iloscSesji;
    }

    public String pokazInfo() {
        return  ":" + super.pokazInfo();
    }
}
