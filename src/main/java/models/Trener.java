package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "trener")
public class Trener extends Pracownik {

    private double dodatekIlosciowy;

    public Trener( String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, LocalDate dataZatrudnienia, Double pensja, Double dodatekIlosciowy) {
        super( imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, dataZatrudnienia, pensja);
        this.dodatekIlosciowy = dodatekIlosciowy;
    }

    public Trener(Klient klient, LocalDate dataZatrudnienia, Double pensja, double dodatekIlosciowy) {
        super(klient,dataZatrudnienia,pensja);
        this.dodatekIlosciowy = dodatekIlosciowy;
    }
    @Override
    Double obliczDodatek() {
        return null;
    }

    public Trener() {
    }

    public Double getDodatekIlosciowy() {
        return dodatekIlosciowy;
    }

    public void setDodatekIlosciowy(Double dodatekIlosciowy) {
        this.dodatekIlosciowy = dodatekIlosciowy;
    }
}
