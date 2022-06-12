package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "klient")
public class Klient extends Osoba {

    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long idKarnetu;
    private int iloscSesji;


    public Klient(long id, String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, Long idKarnetu, int iloscSesji) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres);
        this.idKarnetu = idKarnetu;
        this.iloscSesji = iloscSesji;
    }

    public Klient() {
    }

    public Long getIdKarnetu() {
        return idKarnetu;
    }

    public void setIdKarnetu(Long idKarnetu) {
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
