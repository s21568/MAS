package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity(name = "recepcjonistka")
public class Recepcjonistka extends Pracownik {

    private double dodatekWydajnosciowy;

    public Recepcjonistka(long id, String imie, String nazwisko, String email, LocalDate dataUrodzenia, int numerTelefonu, Adres adres, Long idPracownika, LocalDate dataZatrudnienia, Double pensja, double dodatekWydajnosciowy) {
        super(id, imie, nazwisko, email, dataUrodzenia, numerTelefonu, adres, idPracownika, dataZatrudnienia, pensja);
        this.dodatekWydajnosciowy = dodatekWydajnosciowy;
    }

    public Recepcjonistka() {
    }

    public void setDodatekWydajnosciowy(double dodatekWydajnosciowy) {
        this.dodatekWydajnosciowy = dodatekWydajnosciowy;
    }

    @Override
    Double obliczDodatek() {
        return (super.getPensja() * 0.2);
    }

    private double getDodatekWydajnosciowy() {
        return (super.getPensja() * 0.12);
    }

}
