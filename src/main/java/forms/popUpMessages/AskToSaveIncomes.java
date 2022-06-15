package forms.popUpMessages;

import models.Koszt;
import models.Przychod;
import models.RozliczenieMiesieczne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AskToSaveIncomes extends JFrame {
    private JButton NO;
    private JButton YES;
    private JLabel saveLabel;
    private JPanel mainSavePanel;
    private List<Przychod> changedPrzychod;
    private List<Koszt> changedKoszt;
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;

    public AskToSaveIncomes(List<Przychod> changedPrzychod, List<RozliczenieMiesieczne> rozliczenieMiesieczneList) {
        this.changedPrzychod = changedPrzychod;
        this.rozliczenieMiesieczneList = rozliczenieMiesieczneList;
        this.setContentPane(getContentPane());
        setInitialParametersAndActionsWithIncome();

    }


    private void setInitialParametersAndActionsWithIncome() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        NO.addActionListener(e -> this.dispose());
        YES.addActionListener(x -> {
            if (!changedPrzychod.isEmpty()) {
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
                    for (Przychod przychod : changedPrzychod) {
                        session.update(przychod);
                    }
                    session.update(rozliczenieMiesieczneList.get(0));
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
            this.dispose();
        });
    }

    @Override
    public Container getContentPane() {
        setTitle("Save Progress");
        setSize(450,200);
        setVisible(true);
        setContentPane(mainSavePanel);
        return mainSavePanel;
    }
}
