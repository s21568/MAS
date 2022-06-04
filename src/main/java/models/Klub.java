package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "klub")
public class Klub {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private Date dataOtwarcia;
    private Date godzinaOtwarcia;
    private Date godzinaZamkniecia;
    @ManyToOne
    private Adres adres;

    public Klub(long id, Adres adres, Date dataOtwarcia, Date godzinaOtwarcia, Date godzinaZamkniecia) {
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

    public Date getDataOtwarcia() {
        return dataOtwarcia;
    }

    public void setDataOtwarcia(Date dataOtwarcia) {
        this.dataOtwarcia = dataOtwarcia;
    }

    public Date getGodzinaOtwarcia() {
        return godzinaOtwarcia;
    }

    public void setGodzinaOtwarcia(Date godzinaOtwarcia) {
        this.godzinaOtwarcia = godzinaOtwarcia;
    }

    public Date getGodzinaZamkniecia() {
        return godzinaZamkniecia;
    }

    public void setGodzinaZamkniecia(Date godzinaZamkniecia) {
        this.godzinaZamkniecia = godzinaZamkniecia;
    }
}
