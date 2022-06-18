package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "rozliczenie_miesieczne")
public class RozliczenieMiesieczne {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private LocalDate miesiacPokrycia;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Przychod> listaPrzychodow;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Koszt> listaKosztow;
    private Double sumaPelnaKosztow;
    private Double sumaPelnaPrzychodow;
    private LocalDate dataDodania;
    @ManyToOne
    private Manager idManageraAutoryzujacego;
    @ManyToOne
    private Klub idKlubu;

    public RozliczenieMiesieczne(long id, Klub idKlubu, LocalDate miesiacPokrycia, Manager idManageraAutoryzujacego) {
        this.id = id;
        this.miesiacPokrycia = miesiacPokrycia;
        this.listaPrzychodow = new ArrayList<>();
        this.listaKosztow = new ArrayList<>();
        this.sumaPelnaKosztow = getSumaPelnaKosztow();
        this.sumaPelnaPrzychodow = getSumaPelnaPrzychodow();
        this.dataDodania = LocalDate.now();
        this.idManageraAutoryzujacego = idManageraAutoryzujacego;
        this.idKlubu = idKlubu;
    }

    public RozliczenieMiesieczne() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSumaPelnaKosztow(Double sumaPelnaKosztow) {
        this.sumaPelnaKosztow = sumaPelnaKosztow;
    }

    public void setSumaPelnaPrzychodow(Double sumaPelnaPrzychodow) {
        this.sumaPelnaPrzychodow = sumaPelnaPrzychodow;
    }

    public void setDataDodania(LocalDate dataDodania) {
        this.dataDodania = dataDodania;
    }

    public void setIdKlubu(Klub idKlubu) {
        this.idKlubu = idKlubu;
    }

    public long getId() {
        return id;
    }

    public Klub getIdKlubu() {
        return idKlubu;
    }

    @Transactional
    public Double getSumaPelnaKosztow() {

        double sum = 0.0;
        for (Koszt x : getListaKosztow()) {
            sum += x.getWartosc();
        }
        sumaPelnaKosztow = sum;
        return sum;
    }

    @Transactional
    public Double getSumaPelnaPrzychodow() {
        double sum = 0.0;
        for (Przychod x : getListaPrzychodow()) {
            sum += x.getWartosc();
        }
        sumaPelnaPrzychodow = sum;
        return sum;
    }

    public void removeKoszt(Koszt koszt) {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            listaKosztow.remove(koszt);
            session.delete(koszt);
            session.update(this);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    ;

    public void removePrzychod(Przychod przychod) {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            listaPrzychodow.remove(przychod);
            session.delete(przychod);
            session.update(this);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    ;

    public LocalDate getDataDodania() {
        return dataDodania;
    }

    public LocalDate getMiesiacPokrycia() {
        return miesiacPokrycia;
    }

    public void setMiesiacPokrycia(LocalDate miesiacPokrycia) {
        this.miesiacPokrycia = miesiacPokrycia;
    }

    public Manager getIdManageraAutoryzujacego() {
        return idManageraAutoryzujacego;
    }

    public void setIdManageraAutoryzujacego(Manager idManageraAutoryzujacego) {
        this.idManageraAutoryzujacego = idManageraAutoryzujacego;
    }

    @Transactional
    public List<Przychod> getListaPrzychodow() {
        return listaPrzychodow;
    }

    public void setListaPrzychodow(List<Przychod> listaPrzychodow) {
        this.listaPrzychodow = listaPrzychodow;
    }

    @Transactional
    public List<Koszt> getListaKosztow() {
        return listaKosztow;
    }

    public void addKoszt(Koszt koszt) {
        listaKosztow.add(koszt);
    }

    public void addPrzychod(Przychod przychod) {
        listaPrzychodow.add(przychod);
    }

    public void setListaKosztow(List<Koszt> listaKosztow) {
        this.listaKosztow = listaKosztow;
    }

    public String[] getFullInfo() {
        String[] tmp = new String[7];
        tmp[0] = String.valueOf(getId());
        tmp[1] = getSumaPelnaKosztow().toString();
        tmp[2] = getSumaPelnaPrzychodow().toString();
        tmp[3] = getMiesiacPokrycia().getMonth().getValue() + "/" + getMiesiacPokrycia().getYear();
        tmp[4] = getDataDodania().getMonth().getValue() + "/" + getDataDodania().getYear();
        tmp[5] = getIdManageraAutoryzujacego().getId() + ":" + getIdManageraAutoryzujacego().getImie() + " " + getIdManageraAutoryzujacego().getNazwisko();
        tmp[6] = getIdKlubu().getId() + ":" + getIdKlubu().getAdres().pokazInfo();
        return tmp;
    }
}
