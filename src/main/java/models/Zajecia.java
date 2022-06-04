package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity(name = "zajecia")
public class Zajecia {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private int id;
    private String nazwa;
    private String opis;
    private Date dataOdbywania;
    private static final int iloscMaxUczestnikow = 128;
    private boolean dostepTylkoDlaDoroslych;
    @ManyToOne
    private Klub idKlubu;
    @ManyToMany
    private List<Okres> listaOkresow;

    public Zajecia(int id, String nazwa, String opis, Date dataOdbywania, boolean dostepTylkoDlaDoroslych, Klub idKlubu, List<Okres> listaOkresow) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataOdbywania = dataOdbywania;
        this.dostepTylkoDlaDoroslych = dostepTylkoDlaDoroslych;
        this.idKlubu = idKlubu;
        this.listaOkresow = listaOkresow;
    }

    public Zajecia() {
    }

    public List<Okres> getListaOkresow() {
        return listaOkresow;
    }

    public void setListaOkresow(List<Okres> listaOkresow) {
        this.listaOkresow = listaOkresow;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klub getIdKlubu() {
        return idKlubu;
    }

    public void setIdKlubu(Klub idKlubu) {
        this.idKlubu = idKlubu;
    }

    public int getId() {
        return id;
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

    public Date getDataOdbywania() {
        return dataOdbywania;
    }

    public void setDataOdbywania(Date dataOdbywania) {
        this.dataOdbywania = dataOdbywania;
    }

    public boolean isDostepTylkoDlaDoroslych() {
        return dostepTylkoDlaDoroslych;
    }

    public void setDostepTylkoDlaDoroslych(boolean dostepTylkoDlaDoroslych) {
        this.dostepTylkoDlaDoroslych = dostepTylkoDlaDoroslych;
    }
}
