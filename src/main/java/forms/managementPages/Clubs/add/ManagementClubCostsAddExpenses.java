package forms.managementPages.Clubs.add;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.list.ManagementClubCostsPage;
import forms.managementPages.ManagementPage;
import models.Koszt;
import models.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagementClubCostsAddExpenses extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton saveButton;
    private JTextArea nameInput;
    private JTextArea descriptionInput;
    private JTextArea worthInput;
    private JLabel descriptionLabel;
    private JLabel nameLabel;
    private JLabel worthLabel;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();

    public ManagementClubCostsAddExpenses(Manager manager) {
        setTitle("Management Clubs Costs Add Expenses");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome "+manager.getImie());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(manager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        saveButton.addActionListener(x -> {
            try{

            if (nameInput.getText() != null) {
                nameInput.setBackground(Color.BLACK);
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
                        session.save(new Koszt(1L, Double.parseDouble(worthInput.getText()), nameInput.getText(), descriptionInput.getText()));
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
                    swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(manager,new ArrayList<>()));
                } else {
                    worthLabel.setBackground(Color.RED);
                }
            } else {
                nameLabel.setBackground(Color.RED);
            }
            }catch (NumberFormatException e){
                nameInput.setBackground(Color.RED);
            }

        });
    }


    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Costs Add Expenses");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        return managementPageMainPanel;
    }
}
