package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "trener")
public class Trener extends Pracownik {

    private double dodatekIlosciowy;

    public Trener(long id, String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, Long idPracownika, LocalDate dataZatrudnienia, Double pensja, Double dodatekIlosciowy) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, idPracownika, dataZatrudnienia, pensja);
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
