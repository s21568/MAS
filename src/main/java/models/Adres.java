package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;
    private String kraj;
    private String wojewodztwo;
    private String miasto;
    private String kodPocztowy;
    private int numerBudynku;
    private String ulica;
    private int numerMieszkania;

    public Adres(long id, String kraj, String wojewodztwo, String miasto, String kodPocztowy, int numerBudynku) {
        this.id = id;
        this.kraj = kraj;
        this.wojewodztwo = wojewodztwo;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.numerBudynku = numerBudynku;
    }

    public Adres(long id, String kraj, String wojewodztwo, String miasto, String kodPocztowy, int numerBudynku, String ulica, int numerMieszkania) {
        this.id = id;
        this.kraj = kraj;
        this.wojewodztwo = wojewodztwo;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.numerBudynku = numerBudynku;
        this.ulica = ulica;
        this.numerMieszkania = numerMieszkania;
    }

    public Adres(long id, String kraj, String wojewodztwo, String miasto, String kodPocztowy, int numerBudynku, String ulica) {
        this.id = id;
        this.kraj = kraj;
        this.wojewodztwo = wojewodztwo;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.numerBudynku = numerBudynku;
        this.ulica = ulica;
    }

    public Adres() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public int getNumerBudynku() {
        return numerBudynku;
    }

    public void setNumerBudynku(int numerBudynku) {
        this.numerBudynku = numerBudynku;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getUlica() {
        if (ulica != null)
            return "ul." + ulica + " ";
        else
            return "";
    }

    public void setNumerMieszkania(int numerMieszkania) {

        this.numerMieszkania = numerMieszkania;
    }

    public String getNumerMieszkania() {
        if (numerMieszkania != 0)
            return "m." + numerMieszkania + " ";
        else
            return "";
    }

    public String pokazInfo() {
        return miasto + " " + getUlica() + "" + numerBudynku + " " + getNumerMieszkania();
    }
}
