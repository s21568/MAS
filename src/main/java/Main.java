

import forms.MainPage;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
//        ClientList clientList = new ClientList();
//        StandardServiceRegistry registry = null;
//        SessionFactory sessionFactory = null;
//        try {
//            registry = new StandardServiceRegistryBuilder()
//                    .configure()
//                    .build();
//            sessionFactory = new MetadataSources(registry)
//                    .buildMetadata()
//                    .buildSessionFactory();
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//
//            Adres adres1 = new Adres(1, "polska", "mazurskie", "kair", "00-000", 12);
//            Adres adres2 = new Adres(1, "sosnowiec", "kosmiczne", "mars", "11-111", 992, "wrzosowa", 22);
//            Adres adres3 = new Adres(1, "rosja", "moskiewskie", "moskwa", "123456", 1, "ostatnia");
//
//            Klub klub1 = new Klub(1, adres1, LocalDate.of(1999, 10, 1), LocalDateTime.of(1999, 10, 1, 10, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub2 = new Klub(1, adres2, LocalDate.of(1999, 10, 1), LocalDateTime.of(1999, 10, 1, 10, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub3 = new Klub(1, adres3, LocalDate.of(1999, 10, 1), LocalDateTime.of(1999, 10, 1, 10, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//
//            session.save(adres1);
//            session.save(adres2);
//            session.save(adres3);
//            session.save(klub1);
//            session.save(klub2);
//            session.save(klub3);
//
//            Koszt koszt1 = new Koszt(1L,200.0,"detergenty","mydła, scierki, płyny");
//            Koszt koszt2 = new Koszt(1L,1500.00,"pensja Włodek","miesiąc czerwiec");
//            Koszt koszt3 = new Koszt(1L,4500.0,"pensja Kamil","miesiąc czerwiec");
//
//            Przychod przychod1 = new Przychod(1L,79.99,"Normal","Jan Kowalski czerwiec");
//            Przychod przychod2 = new Przychod(1L,119.99,"VIP","Jonna Nowak czerwiec");
//            Przychod przychod3 = new Przychod(1L,159.99,"VIP_GOLD","Rafał Rak czerwiec");
//
//            session.save(koszt1);
//            session.save(koszt2);
//            session.save(koszt3);
//
//            session.save(przychod1);
//            session.save(przychod2);
//            session.save(przychod3);
//
//            Manager manager = new Manager(1L,"Jan","Kowalski","email@email.com",LocalDate.of(1990,10,10),123456789,adres2,1L,LocalDate.now(),4500.0,0.0);
//            session.save(manager);
//            RozliczenieMiesieczne rozliczenieMiesieczne = new RozliczenieMiesieczne(1,klub2,LocalDate.now(),manager);
//            rozliczenieMiesieczne.addKoszt(koszt1);
//            rozliczenieMiesieczne.addKoszt(koszt2);
//            rozliczenieMiesieczne.addKoszt(koszt3);
//
//            rozliczenieMiesieczne.addPrzychod(przychod1);
//            rozliczenieMiesieczne.addPrzychod(przychod2);
//            rozliczenieMiesieczne.addPrzychod(przychod3);
//            session.save(rozliczenieMiesieczne);
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            StandardServiceRegistryBuilder.destroy(registry);
//        } finally {
//            if (sessionFactory != null) {
//                sessionFactory.close();
//            }
//        }
    }
}
