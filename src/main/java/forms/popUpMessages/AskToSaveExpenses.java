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

public class AskToSaveExpenses extends JFrame {

    private List<Koszt> changedKoszt;
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private JPanel mainSavePanel;
    private JLabel saveLabel;
    private JButton NO;
    private JButton YES;

    public AskToSaveExpenses(List<Koszt> changedKoszt, List<RozliczenieMiesieczne> rozliczenieMiesieczneList) {
        this.changedKoszt = changedKoszt;
        this.rozliczenieMiesieczneList = rozliczenieMiesieczneList;
        this.setContentPane(getContentPane());
        setInitialParametersAndActionsWithExpense();

    }

    private void setInitialParametersAndActionsWithExpense() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        NO.addActionListener(e -> this.dispose());
        YES.addActionListener(x -> {
            if (!changedKoszt.isEmpty()) {
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
                    for (Koszt koszt : changedKoszt) {
                        session.update(koszt);
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
        setSize(450, 200);
        setVisible(true);
        setContentPane(mainSavePanel);
        return mainSavePanel;
    }
}
