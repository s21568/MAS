

import forms.MainPage;

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
//            List<Pracownik> list=session.createQuery("from pracownik ").list();
//            for (Pracownik x:list
//                 ) {
//                System.out.println(Arrays.toString(x.info()));
//            }
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
