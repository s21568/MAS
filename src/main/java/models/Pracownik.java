package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
@Entity(name = "pracownik")
public abstract class Pracownik extends Osoba {
    private Date dataZatrudnienia;
    private Double pensja;
    abstract Double obliczDodatek();

    public Pracownik(long id, String imie, String nazwisko, String email, Date dataUrodzenia, int numerTelefonu, Adres adres, Double pensja, Date dataZatrudnienia) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres);
        this.pensja = pensja;
        this.dataZatrudnienia = dataZatrudnienia;

    }

    public Pracownik() {

    }

    public Date getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }

    public static void obliczPensje(double procent) {
//        System.out.println("=======podwyzkaInflacjna " + procent + "%=======");
//        for (Pracownik p : extent) {
//            p.setPensja(p.pensja * (1 + procent / 100));
//        }
    }

    public static void znajdzNajdluzszyStaz() {
//        System.out.println("=======najdluzszyStaz=======");
//        try {
//            Date tmpdata = Collections.min(extent, Comparator.comparing(Pracownik::getDataZatrudnienia)).dataZatrudnienia;
//
//        for (Pracownik p : extent) {
//            if (p.dataZatrudnienia.equals(tmpdata)) {
//                System.out.println(p.pokazInfo()+" staż:"+pokazStaz(p));
//            }
//        }
//        }catch (NoSuchElementException ignored){}
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
        tmp[8]= String.valueOf(getPensja());
        return tmp;
    }
}
