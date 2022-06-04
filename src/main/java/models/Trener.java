package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "trener")
public class Trener extends Pracownik {

    private Double dodatekIlosciowy;

    public Trener(long id, String imie, String nazwisko, String email, Date dataUrodzenia, int numerTelefonu, Adres adres, Double pensja, Date dataZatrudnienia, Double dodatekIlosciowy) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, pensja, dataZatrudnienia);
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
