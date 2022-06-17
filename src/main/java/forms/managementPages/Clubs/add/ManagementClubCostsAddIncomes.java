package forms.managementPages.Clubs.add;

import forms.MainPage;
import forms.unilities.SwingUiChanger;
import forms.managementPages.Clubs.list.ManagementClubCostsIncomesPage;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Manager;
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

public class ManagementClubCostsAddIncomes extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton saveButton;
    private JLabel descriptionLabel;
    private JTextArea descriptionInput;
    private JLabel nameLabel;
    private JTextArea nameInput;
    private JLabel worthLabel;
    private JTextArea worthInput;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();

    public ManagementClubCostsAddIncomes(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesieczneList, List<Klub> klubList) {
        setTitle("Management Clubs Costs Add Incomes");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome " + manager.getImie());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage(manager, rozliczenieMiesieczneList, klubList)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        saveButton.addActionListener(x -> {
            try {
                if (!nameInput.getText().isEmpty()) {
                    nameLabel.setForeground(Color.BLACK);
                    if (Double.parseDouble(worthInput.getText()) >= 0.0) {
                        worthLabel.setBackground(Color.BLACK);
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
                            Przychod newPrzychod = new Przychod(1L, Double.parseDouble(worthInput.getText()), nameInput.getText(), descriptionInput.getText());
                            session.save(newPrzychod);
                            rozliczenieMiesieczneList.get(0).addPrzychod(newPrzychod);
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
                        swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage(manager, rozliczenieMiesieczneList, klubList));
                    } else {
                        worthLabel.setForeground(Color.RED);
                    }
                } else {
                    nameLabel.setForeground(Color.RED);
                }
            } catch (NumberFormatException n) {
                worthLabel.setForeground(Color.RED);
            }


        });
    }


    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Costs Add Incomes");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        return managementPageMainPanel;
    }
}
