package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "klient")
public class Klient extends Osoba {


    private long idKarnetu;
    private int iloscSesji;


    public Klient( String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres) {
        super( imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres);
        this.iloscSesji = 0;
    }

    public Klient(Pracownik pracownik) {
        super( pracownik.getImie(), pracownik.getNazwisko(), pracownik.getEmail(), pracownik.getDataUrodzenia(), pracownik.getNumerTelefonu(), pracownik.getAdres());
        this.iloscSesji = 0;
    }


    public Klient() {
    }



    public long getIdKarnetu() {
        return idKarnetu;
    }

    public void setIdKarnetu(long idKarnetu) {
        this.idKarnetu = idKarnetu;
    }

    public int getIloscSesji() {
        return iloscSesji;
    }

    public void setIloscSesji(int iloscSesji) {
        this.iloscSesji = iloscSesji;
    }

    public String pokazInfo() {
        return ":" + super.pokazInfo();
    }

    public String[] info() {
        String[] tmp = new String[8];
        tmp[0] = String.valueOf(getId());
        tmp[1] = getImie();
        tmp[2] = getNazwisko();
        tmp[3] = getDataUrodzenia().toString();
        tmp[4] = String.valueOf(getNumerTelefonu());
        tmp[5] = getEmail();
        tmp[6] = String.valueOf(getIloscSesji());
        tmp[7] = getAdres().pokazInfo();
        return tmp;
    }
}
