package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "przychod")
public class Przychod {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private int id;
    private double wartosc;
    private String nazwa;
    private int opis;

    public Przychod(int id, double wartosc, String nazwa, int opis) {
        this.id = id;
        this.wartosc = wartosc;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Przychod() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOpis() {
        return opis;
    }

    public void setOpis(int opis) {
        this.opis = opis;
    }
}
