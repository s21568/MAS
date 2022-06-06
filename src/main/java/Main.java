

import forms.ClientList;
import forms.MainPage;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainPage mainPage= new MainPage();
//        ClientList clientList=new ClientList();
//        StandardServiceRegistry registry=null;
//        SessionFactory sessionFactory=null;
//        try {
//            registry=new StandardServiceRegistryBuilder()
//                    .configure()
//                    .build();
//            sessionFactory=new MetadataSources(registry)
//                    .buildMetadata()
//                    .buildSessionFactory();
//            Session session=sessionFactory.openSession();
//            session.beginTransaction();
//
////            Adres adres1=new Adres(1,"Polska","Mazowieckie","Warsawa","01-000",22);
////            Adres adres2=new Adres(1,"Polska","Mazowieckie","Warsawa","01-000",22);
////
////            session.save(adres1);
////            session.save(adres2);
////
////
////            Klient klient1=new Klient(1,"Jana","Kowalski","email.Jan@gmail.com",new Date(1990,10,1),123456789,adres1);
////            Klient klient2=new Klient(2,"Jan","Nowak","email.JanN@gmail.com",new Date(1990,11,1),111111111,adres2);
////
////
////            session.save(klient1);
////            session.save(klient2);
//
////            Trener trener1=new Trener(1,"Jana","Kowalski","email.Jan@gmail.com",new Date(1990,10,1),123456789,adres1,2137.0,new Date(),200.0);
////
////            session.save(trener1);
////
////            Manager manager1= new Manager(1,"Jana","Kowalski","email.Jan@gmail.com",new Date(1990,10,1),123456789,adres2,2137.0,new Date(),200.0);
////            session.save(manager1);
////
////            Pakiet pakiet=new Pakiet(1,PakietNazwa.Normal,false,false,false,false,false);
////            session.save(pakiet);
//
//            List<Klient> klientList=session.createQuery("from klient").list();
//
//
//
//
//            session.getTransaction().commit();
//            session.close();
//        }catch (Exception e){
//            e.printStackTrace();
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//        finally {
//            if(sessionFactory!=null){
//                sessionFactory.close();
//            }
//        }
    }
}
