package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "pakiet")
public class Pakiet {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private String nazwa;
    private boolean napoje;
    private boolean sauna;
    private boolean solarium;
    private boolean masaze;
    private boolean sesjePrywatneZTrenerem;

    public Pakiet(long id,Enum<PakietNazwa> nazwa, boolean napoje, boolean sauna, boolean solarium, boolean masaze, boolean sesjePrywatneZTrenerem) {
        this.id=id;
        this.nazwa = nazwa.name();
        this.napoje = napoje;
        this.sauna = sauna;
        this.solarium = solarium;
        this.masaze = masaze;
        this.sesjePrywatneZTrenerem = sesjePrywatneZTrenerem;
    }

    public Pakiet() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {return id;}

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(Enum<PakietNazwa> nazwa) {
        this.nazwa = nazwa.name();
    }

    public boolean isNapoje() {
        return napoje;
    }

    public void setNapoje(boolean napoje) {
        this.napoje = napoje;
    }

    public boolean isSauna() {
        return sauna;
    }

    public void setSauna(boolean sauna) {
        this.sauna = sauna;
    }

    public boolean isSolarium() {
        return solarium;
    }

    public void setSolarium(boolean solarium) {
        this.solarium = solarium;
    }

    public boolean isMasaze() {
        return masaze;
    }

    public void setMasaze(boolean masaze) {
        this.masaze = masaze;
    }

    public boolean isSesjePrywatneZTrenerem() {
        return sesjePrywatneZTrenerem;
    }

    public void setSesjePrywatneZTrenerem(boolean sesjePrywatneZTrenerem) {
        this.sesjePrywatneZTrenerem = sesjePrywatneZTrenerem;
    }
}
