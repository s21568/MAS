package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "ostrzezenie")
public class Ostrzezenie {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long numer;
    private String opis;
    private boolean czyPowazne;
    @ManyToOne
    private Pracownik idPracownika;
    @ManyToOne
    private Klient idKlienta;

    public Ostrzezenie(long numer, String opis, boolean czyPowazne, Pracownik idPracownika, Klient idKlienta) {
        this.numer = numer;
        this.opis = opis;
        this.czyPowazne = czyPowazne;
        this.idPracownika = idPracownika;
        this.idKlienta = idKlienta;
    }

    public Ostrzezenie() {
    }


    public List<Ostrzezenie> wyswietlOstrzezenia(Klient klient) {
        return new ArrayList<Ostrzezenie>();
    }

    public void setIdPracownika(Pracownik idPracownika) {
        this.idPracownika = idPracownika;
    }

    public void setIdKlienta(Klient idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Pracownik getIdPracownika() {
        return idPracownika;
    }

    public Klient getIdKlienta() {
        return idKlienta;
    }

    public long getNumer() {
        return numer;
    }

    public void setNumer(long numer) {
        this.numer = numer;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isCzyPowazne() {
        return czyPowazne;
    }

    public void setCzyPowazne(boolean czyPowazne) {
        this.czyPowazne = czyPowazne;
    }
}
