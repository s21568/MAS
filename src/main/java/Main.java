

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
        MainPage mainPage = new MainPage();
//
//            Adres adres1 = new Adres(1, "Polska", "Mazurskie", "Kair", "00-000", 12);
//            Adres adres2 = new Adres(1, "Polska", "Podlaskie", "Mars", "11-111", 992, "Wrzosowa", 22);
//            Adres adres3 = new Adres(1, "Polska", "Mazowieckie", "Warszawa", "01-011", 1, "Kwiatowa", 10);
//            Adres adres4 = new Adres(1, "Polska", "Mazowieckie", "Radom", "01-211", 3, "Różana", 1);
//            Adres adres5 = new Adres(1, "Polska", "Mazowieckie", "Piaseczno", "05-220", 33, "Ostatnia", 4);
//            Adres adres6 = new Adres(1, "Polska", "Mazowieckie", "Olsztyn", "09-123", 212, "Polska");
//            Adres adres7 = new Adres(1, "Polska", "Mazowieckie", "Kraków", "10-291", 67, "Smocza", 85);
//
//            Klub klub1 = new Klub(1, adres1, LocalDate.of(1999, 10, 1), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub2 = new Klub(1, adres2, LocalDate.of(1999, 11, 12), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub7 = new Klub(1, adres7, LocalDate.of(1999, 11, 12), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub3 = new Klub(1, adres3, LocalDate.of(1999, 1, 1), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub6 = new Klub(1, adres6, LocalDate.of(1999, 1, 1), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub4 = new Klub(1, adres4, LocalDate.of(1999, 12, 25), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//            Klub klub5 = new Klub(1, adres5, LocalDate.of(1999, 8, 17), LocalDateTime.of(1999, 10, 1, 6, 20), LocalDateTime.of(1999, 10, 1, 20, 20));
//
//            session.save(adres1);
//            session.save(adres2);
//            session.save(adres3);
//            session.save(adres4);
//            session.save(adres5);
//            session.save(adres6);
//            session.save(adres7);
//
//            session.save(klub1);
//            session.save(klub2);
//            session.save(klub3);
//            session.save(klub4);
//            session.save(klub5);
//            session.save(klub6);
//            session.save(klub7);
//
//            Koszt koszt1 = new Koszt(1L, 200.0, "detergenty", "mydła, scierki, płyny");
//            Koszt koszt2 = new Koszt(1L, 1500.00, "pensja Włodek", "miesiąc czerwiec");
//            Koszt koszt3 = new Koszt(1L, 4500.0, "pensja Kamil", "miesiąc czerwiec");
//
//            Koszt koszt11 = new Koszt(1L, 200.0, "detergenty", "mydła, scierki, płyny");
//            Koszt koszt21 = new Koszt(1L, 1500.00, "pensja Włodek", "miesiąc maj");
//            Koszt koszt31 = new Koszt(1L, 4500.0, "pensja Kamil", "miesiąc maj");
//
//            Koszt koszt12 = new Koszt(1L, 200.0, "detergenty", "mydła, scierki, płyny");
//            Koszt koszt22 = new Koszt(1L, 1500.00, "pensja Włodek", "miesiąc kwiecień");
//            Koszt koszt32 = new Koszt(1L, 4500.0, "pensja Kamil", "miesiąc kwiecień");
//
//            Koszt koszt13 = new Koszt(1L, 200.0, "detergenty", "mydła, scierki, płyny");
//            Koszt koszt23 = new Koszt(1L, 1500.00, "pensja Włodek", "miesiąc luty");
//            Koszt koszt33 = new Koszt(1L, 4500.0, "pensja Kamil", "miesiąc luty");
//
//            Przychod przychod1 = new Przychod(1L, 79.99, "Normal", "Jan Kowalski czerwiec");
//            Przychod przychod2 = new Przychod(1L, 119.99, "VIP", "Jonna Nowak czerwiec");
//            Przychod przychod3 = new Przychod(1L, 159.99, "VIP_GOLD", "Rafał Rak czerwiec");
//
//            Przychod przychod11 = new Przychod(1L, 79.99, "Normal", "Jan Kowalski maj");
//            Przychod przychod21 = new Przychod(1L, 119.99, "VIP", "Jonna Nowak maj");
//            Przychod przychod31 = new Przychod(1L, 159.99, "VIP_GOLD", "Rafał Rak maj");
//
//            Przychod przychod12 = new Przychod(1L, 79.99, "Normal", "Jan Kowalski kwiecień");
//            Przychod przychod22 = new Przychod(1L, 119.99, "VIP", "Jonna Nowak kwiecień");
//            Przychod przychod32 = new Przychod(1L, 159.99, "VIP_GOLD", "Rafał Rak kwiecień");
//
//            Przychod przychod13 = new Przychod(1L, 79.99, "Normal", "Jan Kowalski luty");
//            Przychod przychod23 = new Przychod(1L, 119.99, "VIP", "Jonna Nowak luty");
//            Przychod przychod33 = new Przychod(1L, 159.99, "VIP_GOLD", "Rafał Rak luty");
//
//
//            session.save(koszt1);
//            session.save(koszt2);
//            session.save(koszt3);
//
//            session.save(koszt11);
//            session.save(koszt21);
//            session.save(koszt31);
//
//            session.save(koszt12);
//            session.save(koszt22);
//            session.save(koszt32);
//
//            session.save(koszt13);
//            session.save(koszt23);
//            session.save(koszt33);
//
//            session.save(przychod1);
//            session.save(przychod2);
//            session.save(przychod3);
//
//            session.save(przychod11);
//            session.save(przychod21);
//            session.save(przychod31);
//
//            session.save(przychod12);
//            session.save(przychod22);
//            session.save(przychod32);
//
//            session.save(przychod13);
//            session.save(przychod23);
//            session.save(przychod33);
//
//            Manager manager2 = new Manager( "Joanna", "Nowak", "Nowak@gmail.com", LocalDate.of(1982, 1, 10), 282918281, adres5,  LocalDate.now(), 4500.0, 0.0,"haslo123");
//            Manager manager3 = new Manager( "Robert", "Kowalski", "Kowalski@gmail.com", LocalDate.of(1999, 12, 12), 282919281, adres7, LocalDate.now(), 3500.0, 200.0,"password");
//            session.save(manager2);
//            session.save(manager3);
//
//            Klient klient1 = new Klient( "Michał", "Mak", "Mak@gmail.com", LocalDate.of(2000, 1, 1), 921921921, adres1);
//            Klient klient2 = new Klient( "Kamil", "Ślimak", "Ślimak@gmail.com", LocalDate.of(1993, 1, 1), 921921921, adres2);
//            Klient klient3 = new Klient( "Adrian", "Sas", "Sas@gmail.com", LocalDate.of(1991, 1, 1), 921921921, adres3);
//            Klient klient4 = new Klient( "Paweł", "Nowak", "Nowak@gmail.com", LocalDate.of(1978, 1, 1), 921921921, adres4);
//            Klient klient5 = new Klient( "Jan", "Kowalski", "Kowalski@gmail.com", LocalDate.of(1982, 1, 1), 921921921, adres5);
//            Klient klient6 = new Klient( "Kamil", "Nowak", "Nowak@gmail.com", LocalDate.of(1981, 1, 1), 921921921, adres6);
//            Klient klient7 = new Klient( "Włodzimierz", "Mały", "Mały@gmail.com", LocalDate.of(2005, 1, 1), 921921921, adres7);
//            Klient klient8 = new Klient( "Joanna", "Rak", "Rak@gmail.com", LocalDate.of(2002, 1, 1), 921921921, adres5);
//
//            Klient klient9=new Klient(manager2);
//
//            session.save(klient1);
//            session.save(klient2);
//            session.save(klient3);
//            session.save(klient4);
//            session.save(klient5);
//            session.save(klient6);
//            session.save(klient7);
//            session.save(klient8);
//            session.save(klient9);
//
//            RozliczenieMiesieczne rozliczenieMiesieczne2 = new RozliczenieMiesieczne(1, klub2, LocalDate.now(), manager3);
//            rozliczenieMiesieczne2.addKoszt(koszt1);
//            rozliczenieMiesieczne2.addKoszt(koszt2);
//            rozliczenieMiesieczne2.addKoszt(koszt3);
//            RozliczenieMiesieczne rozliczenieMiesieczne3 = new RozliczenieMiesieczne(1, klub2, LocalDate.now(), manager2);
//            rozliczenieMiesieczne3.addPrzychod(przychod1);
//            rozliczenieMiesieczne3.addPrzychod(przychod2);
//            rozliczenieMiesieczne3.addPrzychod(przychod3);
//            RozliczenieMiesieczne rozliczenieMiesieczne4 = new RozliczenieMiesieczne(1, klub4, LocalDate.now().minusMonths(1L), manager3);
//            rozliczenieMiesieczne4.addKoszt(koszt11);
//            rozliczenieMiesieczne4.addKoszt(koszt21);
//            rozliczenieMiesieczne4.addKoszt(koszt31);
//            rozliczenieMiesieczne4.addPrzychod(przychod11);
//            rozliczenieMiesieczne4.addPrzychod(przychod21);
//            rozliczenieMiesieczne4.addPrzychod(przychod31);
//            RozliczenieMiesieczne rozliczenieMiesieczne5 = new RozliczenieMiesieczne(1, klub6, LocalDate.now().minusMonths(2L), manager2);
//            rozliczenieMiesieczne5.addKoszt(koszt12);
//            rozliczenieMiesieczne5.addKoszt(koszt22);
//            rozliczenieMiesieczne5.addKoszt(koszt32);
//            RozliczenieMiesieczne rozliczenieMiesieczne6 = new RozliczenieMiesieczne(1, klub6, LocalDate.now().minusMonths(2L), manager2);
//            rozliczenieMiesieczne6.addPrzychod(przychod12);
//            rozliczenieMiesieczne6.addPrzychod(przychod22);
//            rozliczenieMiesieczne6.addPrzychod(przychod32);
//            RozliczenieMiesieczne rozliczenieMiesieczne7 = new RozliczenieMiesieczne(1, klub1, LocalDate.now().minusMonths(4L), manager3);
//            rozliczenieMiesieczne7.addPrzychod(przychod13);
//            rozliczenieMiesieczne7.addPrzychod(przychod23);
//            rozliczenieMiesieczne7.addPrzychod(przychod33);
//            rozliczenieMiesieczne7.addKoszt(koszt13);
//            rozliczenieMiesieczne7.addKoszt(koszt23);
//            rozliczenieMiesieczne7.addKoszt(koszt33);
//            session.save(rozliczenieMiesieczne2);
//            session.save(rozliczenieMiesieczne3);
//            session.save(rozliczenieMiesieczne4);
//            session.save(rozliczenieMiesieczne5);
//            session.save(rozliczenieMiesieczne6);
//            session.save(rozliczenieMiesieczne7);
//
//
//            Pakiet pakiet1 = new Pakiet(1L, PakietNazwa.Normal, false, false, false, false, false);
//            Pakiet pakiet2 = new Pakiet(1L, PakietNazwa.VIP, true, true, true, false, false);
//            Pakiet pakiet3 = new Pakiet(1L, PakietNazwa.VIP_GOLD, true, true, true, true, true);
//
//            session.save(pakiet1);
//            session.save(pakiet2);
//            session.save(pakiet3);
//

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
}
