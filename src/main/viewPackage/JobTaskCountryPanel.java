package main.viewPackage;

import main.controllerPackage.CountriesController;
import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class JobTaskCountryPanel extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private JComboBox<String> countryComboBox;
    private JTable tableUsers;
    private JButton submitButton;
    private double percentage;
    private CountriesController countriesController;
    private List<UserModel> users;
    private UserController userController;
    private DefaultTableModel tableModel;
    private JLabel percentageLabel;

    public JobTaskCountryPanel(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;
        userController = new UserController();

        JLabel title = new JLabel("Sélectionne un pays pour voir le pourcentage d'utilisateurs correspondant");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);
        
        countriesController = new CountriesController();
        try {
            countryComboBox = new JComboBox<>(countriesController.getCountries().toArray(new String[0]));
        } catch (CountriesDAOException e) {
            mainWindow.displayError(e.toString());
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(countryComboBox, gbc);
        countryComboBox.addActionListener(this);

        String[] columnNames = {"Nom de l'utilisateur", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableUsers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        percentageLabel = new JLabel("Pourcentage d'utilisateurs correspondant : ");
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        percentageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(percentageLabel, gbc);

        submitButton = new JButton("Rechercher");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);
        submitButton.addActionListener(this);
    }

    private void submit() {
        try {
            resetRows();

            String selectedCountry = Objects.requireNonNull(countryComboBox.getSelectedItem()).toString();
            users = userController.getUsersByCountry(selectedCountry);
            if (users.isEmpty()) {
                mainWindow.displayError("Aucun utilisateur trouvé");
                resetPercentageText();
            } else {
                for (UserModel user : users) {
                    Object[] rowData = {
                            user.getUsername(),
                            user.getEmail(),
                    };
                    tableModel.addRow(rowData);
                }
                percentage = (double) users.size() / userController.getNbUser() * 100;
                percentageLabel.setText(String.format("Pourcentage d'utilisateurs venant de %s : %.2f%% (%d)", selectedCountry, percentage, users.size()));
            }
        } catch (UserSearchException exception) {
            resetPercentageText();
            mainWindow.displayError(exception.toString());
            resetRows();
        }
    }

    private void resetPercentageText() {
        percentageLabel.setText("Pourcentage d'utilisateurs correspondant : ");
    }

    private void resetRows() {
        tableModel.setRowCount(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submit();
        }
    }
}