package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.controllerPackage.CountriesController;
import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;

public class AddUserPanel extends JPanel implements ActionListener, ItemListener {
    private String[] GENDER_CHOICE = {"Male", "Female", "Other"};
    private int TEXT_FIELD_COLUMNS = 20;
    private int LABEL_PADDING = 20;
    private int FIELD_PADDING = 5;

    private MainWindow mainWindow;
    private int nbFields = 0;
    private JLabel topLabel;
    private JTextField username;
    private JPasswordField password;
    private JTextField email;
    private JTextField dateOfBirth;
    private JTextField phoneNumber;
    private JTextField street;
    private JTextField bio;
    private JCheckBox isAdmin;
    private JSpinner dateOfBirthSpinner;
    private SpinnerDateModel dateOfBirthModel;
    private JButton submit;
    private JComboBox<String> zipCode;
    private JComboBox<String> country;
    private JComboBox<String> gender;
    private Dimension textFieldSize = new JTextField(TEXT_FIELD_COLUMNS).getPreferredSize();

    private CountriesController countriesController;
    private UserController userController;

    public AddUserPanel(MainWindow mainWindow) throws CountriesDAOException, ConnectionDataAccessException, LocalityException {
        countriesController = new CountriesController();
        userController = new UserController();

        this.mainWindow = mainWindow;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(LABEL_PADDING, LABEL_PADDING, LABEL_PADDING, LABEL_PADDING);

        topLabel = new JLabel("Création d'un utilisateur");
        gbc.gridx = 0;
        gbc.gridy = nbFields;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(topLabel, gbc);

        gbc.insets = new Insets(FIELD_PADDING, FIELD_PADDING, FIELD_PADDING, FIELD_PADDING);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addField(gbc, "Champs Obligatoires *", new JLabel(""));
        addField(gbc, "Email *", email = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Nom d'utilisateur *", username = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Mot de passe *", password = new JPasswordField(TEXT_FIELD_COLUMNS));

        dateOfBirthModel = new SpinnerDateModel();
        dateOfBirthSpinner = new JSpinner(dateOfBirthModel);
        dateOfBirthSpinner.setEditor(new JSpinner.DateEditor(dateOfBirthSpinner, "dd/MM/yyyy"));
        dateOfBirthSpinner.setPreferredSize(textFieldSize);

        addField(gbc, "Date de naissance *", dateOfBirthSpinner);
        addField(gbc, "Rue et numéro *", street = new JTextField(TEXT_FIELD_COLUMNS));

        zipCode = new JComboBox<>();
        zipCode.setPreferredSize(textFieldSize);
        addField(gbc, "Code postal *", zipCode);

        addField(gbc, "Téléphone", phoneNumber = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Biographie", bio = new JTextField(TEXT_FIELD_COLUMNS));

        gender = new JComboBox<>(GENDER_CHOICE);
        gender.setPreferredSize(textFieldSize);
        addField(gbc, "Genre *", gender);

        country = new JComboBox<>(countriesController.getCountries().toArray(new String[0]));
        country.setPreferredSize(textFieldSize);
        country.addItemListener(this);
        addField(gbc, "Pays *", country);
        refreshLocalities();

        addField(gbc, "Admin *", isAdmin = new JCheckBox());

        submit = new JButton("Ajouter l'utilisateur");
        submit.setPreferredSize(textFieldSize);
        submit.addActionListener(this);
        addField(gbc, "", submit);

        setVisible(true);
    }

    private void addField(GridBagConstraints gbc, String labelText, Component component) {
        nbFields++;
        gbc.gridx = 0; // Texte a gauche
        gbc.gridy = nbFields; // Position du component
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1; // placement du component a droite du texte correspondant
        add(component, gbc);
    }

    private void refreshLocalities() {
        List<LocalityModel> localities;
        try {
            localities = userController.getLocality(Objects.requireNonNull(country.getSelectedItem()).toString());
            zipCode.removeAllItems();
            List<String> formattedLocalities = new ArrayList<>();
            if (localities.isEmpty()) {
                formattedLocalities.add("Aucune localité trouvée");
            } else {
                for (LocalityModel locality : localities) {
                    formattedLocalities.add(locality.getCity() + " - " + locality.getZipCode());
                }
            }

            for (String formattedLocality : formattedLocalities) {
                zipCode.addItem(formattedLocality);
            }
        } catch (ConnectionDataAccessException | LocalityException ex) {
            mainWindow.displayError(ex.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            Date date = new Date(dateOfBirthModel.getDate().getTime());
            // send sql via controller
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == country) {
            refreshLocalities();
        }
    }
}
