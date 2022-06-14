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
    private Long id;
    private double wartosc;
    private String nazwa;
    private String opis;

    public Przychod(Long id, double wartosc, String nazwa, String opis) {
        this.id = id;
        this.wartosc = wartosc;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Przychod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    public String[] getFullInfo() {
        String[] tmp = new String[6];
        tmp[0] = String.valueOf(getId());
        tmp[1] = String.valueOf(getWartosc());
        tmp[2] = getNazwa();
        tmp[3] = getOpis();
        return tmp;
    }
}
