package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime dataOdbywania;
    private static final int iloscMaxUczestnikow = 128;
    private boolean dostepTylkoDlaDoroslych;
    @ManyToOne
    private Klub idKlubu;
    @ManyToMany
    private List<Trener> listaTrenerow;

    public Zajecia(int id, String nazwa, String opis, LocalDateTime dataOdbywania, boolean dostepTylkoDlaDoroslych, Klub idKlubu, List<Trener> listaTrenerow) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataOdbywania = dataOdbywania;
        this.dostepTylkoDlaDoroslych = dostepTylkoDlaDoroslych;
        this.idKlubu = idKlubu;
        this.listaTrenerow = listaTrenerow;
    }

    public Zajecia() {
    }

    public List<Trener> getListaTrenerow() {
        return listaTrenerow;
    }

    public void setListaTrenerow(List<Trener> listaTrenerow) {
        this.listaTrenerow = listaTrenerow;
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

    public LocalDateTime getDataOdbywania() {
        return dataOdbywania;
    }

    public void setDataOdbywania(LocalDateTime dataOdbywania) {
        this.dataOdbywania = dataOdbywania;
    }

    public boolean isDostepTylkoDlaDoroslych() {
        return dostepTylkoDlaDoroslych;
    }

    public void setDostepTylkoDlaDoroslych(boolean dostepTylkoDlaDoroslych) {
        this.dostepTylkoDlaDoroslych = dostepTylkoDlaDoroslych;
    }
    public String[] info() {
        String[] tmp = new String[8];
        tmp[0] = String.valueOf(getId());
        tmp[1] = getNazwa();
        tmp[2] = getDataOdbywania().toString();
        tmp[3] = getOpis();
        tmp[4] = String.valueOf(getIdKlubu());
        tmp[5] = String.valueOf(isDostepTylkoDlaDoroslych());
        return tmp;
    }
}
