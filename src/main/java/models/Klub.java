package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "klub")
public class Klub {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private LocalDate dataOtwarcia;
    private LocalDate godzinaOtwarcia;
    private LocalDate godzinaZamkniecia;
    @ManyToOne
    private Adres adres;

    public Klub(long id, Adres adres, LocalDate dataOtwarcia, LocalDate godzinaOtwarcia, LocalDate godzinaZamkniecia) {
        this.id = id;
        this.adres = adres;
        this.dataOtwarcia = dataOtwarcia;
        this.godzinaOtwarcia = godzinaOtwarcia;
        this.godzinaZamkniecia = godzinaZamkniecia;
    }

    public Klub() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public LocalDate getDataOtwarcia() {
        return dataOtwarcia;
    }

    public void setDataOtwarcia(LocalDate dataOtwarcia) {
        this.dataOtwarcia = dataOtwarcia;
    }

    public LocalDate getGodzinaOtwarcia() {
        return godzinaOtwarcia;
    }

    public void setGodzinaOtwarcia(LocalDate godzinaOtwarcia) {
        this.godzinaOtwarcia = godzinaOtwarcia;
    }

    public LocalDate getGodzinaZamkniecia() {
        return godzinaZamkniecia;
    }

    public void setGodzinaZamkniecia(LocalDate godzinaZamkniecia) {
        this.godzinaZamkniecia = godzinaZamkniecia;
    }

    public String[] info() {
        String[] tmp = new String[6];
        tmp[0] = String.valueOf(getId());
        tmp[1] = getDataOtwarcia().toString();
        tmp[2] = godzinaOtwarcia.toString();
        tmp[3] = godzinaZamkniecia.toString();
        tmp[4] = getAdres().pokazInfo();
        return tmp;
    }
}
