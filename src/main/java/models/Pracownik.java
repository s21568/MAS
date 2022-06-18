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
import java.util.*;

@Entity(name = "pracownik")
public abstract class Pracownik extends Osoba {


    private long idPracownika;
    private LocalDate dataZatrudnienia;
    private Double pensja;

    abstract Double obliczDodatek();

    public Pracownik(String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, LocalDate dataZatrudnienia, Double pensja) {
        super(imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres);
        this.dataZatrudnienia = dataZatrudnienia;
        this.pensja = pensja;
    }

    public Pracownik(Klient klient, LocalDate dataZatrudnienia, Double pensja) {
        super(klient.getImie(), klient.getNazwisko(), klient.getEmail(), klient.getDataUrodzenia(), klient.getNumerTelefonu(), klient.getAdres());
        this.dataZatrudnienia = dataZatrudnienia;
        this.pensja = pensja;
    }


    public Pracownik() {
    }

    public long getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(long idPracownika) {
        this.idPracownika = idPracownika;
    }

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }

    public String pokazInfo() {
        return ":" + super.pokazInfo() + " " + pensja;
    }

    public String[] info() {
        String[] tmp = new String[9];
        tmp[0] = String.valueOf(getId());
        tmp[1] = getImie();
        tmp[2] = getNazwisko();
        tmp[3] = getDataUrodzenia().toString();
        tmp[4] = String.valueOf(getNumerTelefonu());
        tmp[5] = getEmail();
        tmp[6] = getAdres().pokazInfo();
        tmp[7] = getDataZatrudnienia().toString();
        tmp[8] = String.valueOf(getPensja());
        return tmp;
    }
}
