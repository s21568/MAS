package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "umowa")
public class Umowa {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long numerUmowy;
    private Date dataPodpisaniaUmowy;
    private Date dataKoncaUmowy;
    private boolean czyZawieszona;
    @ManyToOne
    private Pracownik idPracownika;
    @ManyToOne
    private Klient idKlienta;
    @ManyToOne
    private Pakiet pakiet;
    @ManyToOne
    private Klub klub;

    public Umowa(int numerUmowy, Date dataPodpisaniaUmowy, Date dataKoncaUmowy, Pakiet pakiet, Klub klub, Pracownik idPracownika, Klient idKlienta) {
        this.numerUmowy = numerUmowy;
        this.dataPodpisaniaUmowy = dataPodpisaniaUmowy;
        this.dataKoncaUmowy = dataKoncaUmowy;
        this.pakiet = pakiet;
        this.klub = klub;
        this.idPracownika = idPracownika;
        this.idKlienta = idKlienta;
        czyZawieszona=false;
    }

    public Umowa() {
    }

    public int policzUmowyKonczaceWMiesiacu(String miesiac) {
        return 1;
    }

    public void anulujUmowe(){
        dataKoncaUmowy=new Date();
    }

    public void zawiesUmowe(){
        czyZawieszona=true;
    }

    public void wznowUmowe(){
        czyZawieszona=false;
    }

    public long getNumerUmowy() {
        return numerUmowy;
    }

    public Date getDataPodpisaniaUmowy() {
        return dataPodpisaniaUmowy;
    }

    public Date getDataKoncaUmowy() {
        return dataKoncaUmowy;
    }

    public void setDataKoncaUmowy(Date dataKoncaUmowy) {
        this.dataKoncaUmowy = dataKoncaUmowy;
    }

    public Pakiet getPakiet() {
        return pakiet;
    }

    public void setPakiet(Pakiet pakiet) {
        this.pakiet = pakiet;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public Pracownik getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Pracownik idPracownika) {
        this.idPracownika = idPracownika;
    }

    public Klient getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Klient idKlienta) {
        this.idKlienta = idKlienta;
    }
}
