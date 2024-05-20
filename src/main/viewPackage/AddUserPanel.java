package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.controllerPackage.CountriesController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

public class AddUserPanel extends JPanel implements ActionListener {
    private String[] GENDER_CHOICE = {"Male", "Female", "Other"};
    private int TEXT_FIELD_COLUMNS = 20;

    private JTextField username;
    private JTextField password;
    private JTextField email;
    private JTextField dateOfBirth;
    private JTextField phoneNumber;
    private JTextField street;
    private JTextField bio;
    private JCheckBox isAdmin;
    private JButton submit;
    private JComboBox<String> country;
    private JComboBox<String> gender;
    private Dimension textFieldSize = new JTextField(TEXT_FIELD_COLUMNS).getPreferredSize();

    private CountriesController countriesController;

    public AddUserPanel() throws CountriesDAOException, ConnectionDataAccessException {
        countriesController = new CountriesController();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding between components

        JLabel label = new JLabel("Add User Panel");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(label, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addField(gbc, "Email", email = new JTextField(TEXT_FIELD_COLUMNS), 1);
        addField(gbc, "Username", username = new JTextField(TEXT_FIELD_COLUMNS), 2);
        addField(gbc, "Password", password = new JPasswordField(TEXT_FIELD_COLUMNS), 3);
        addField(gbc, "Date of Birth", dateOfBirth = new JTextField(TEXT_FIELD_COLUMNS), 4);
        addField(gbc, "Street and number", street = new JTextField(TEXT_FIELD_COLUMNS), 5);
        addField(gbc, "Phone Number", phoneNumber = new JTextField(TEXT_FIELD_COLUMNS), 6);
        addField(gbc, "Biography", bio = new JTextField(TEXT_FIELD_COLUMNS), 7);

        gender = new JComboBox<>(GENDER_CHOICE);
        gender.setPreferredSize(textFieldSize);
        addField(gbc, "Gender", gender, 8);

        country = new JComboBox<>(countriesController.getCountries().toArray(new String[0]));
        country.setPreferredSize(textFieldSize);
        addField(gbc, "Country", country, 9);

        addField(gbc, "Admin user", isAdmin = new JCheckBox(), 10);

        submit = new JButton("Ajouter l'utilisateur");
        submit.setPreferredSize(textFieldSize);
        addField(gbc, "", submit, 11);

        setVisible(true);
    }

    private void addField(GridBagConstraints gbc, String labelText, Component component, int yPos) {
        gbc.gridx = 0; // Texte a gauche
        gbc.gridy = yPos; // Position du component
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1; // placement du component a droite du texte correspondant
        add(component, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
