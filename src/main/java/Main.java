

import forms.MainPage;
import models.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainPage mainPage= new MainPage();
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
//            List<Pracownik> pracowniks=session.createQuery("from ");
//
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
