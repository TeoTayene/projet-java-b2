package main.viewPackage;

import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class JobTaskAgePanel extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private JComboBox<String> ageComoboBox;
    private JTable tableUsers;
    private JButton submitButton;
    private double percentage;
    private List<UserModel> users;
    private UserController userController;
    private DefaultTableModel tableModel;
    private JLabel percentageLabel;

    public JobTaskAgePanel(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;
        userController = new UserController();

        JLabel title = new JLabel("Sélectionne une tranche d'age pour voir le pourcentage d'utilisateurs correspondant :");
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

        userController = new UserController();
        ageComoboBox = new JComboBox<>();
        ageComoboBox.addItem("0-17");
        ageComoboBox.addItem("18-25");
        ageComoboBox.addItem("26-49");
        ageComoboBox.addItem("50-64");
        ageComoboBox.addItem("65+");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(ageComoboBox, gbc);
        ageComoboBox.addActionListener(this);

        String[] columnNames = {"Nom de l'utilisateur", "Email","Date de naissance"};
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
            Date[] dateRange = getDateRange((String) Objects.requireNonNull(ageComoboBox.getSelectedItem()));
            // date range est inversé
            java.sql.Date startDate = new java.sql.Date(dateRange[1].getTime());
            java.sql.Date endDate = new java.sql.Date(dateRange[0].getTime());

            users = userController.getUsersByAge(startDate, endDate);
            int nbUsers = userController.getAllUsers().size();
            int count = 0;
            for (UserModel user : users) {
                if (user.getDateOfBirth().before(dateRange[0]) && user.getDateOfBirth().after(dateRange[1])) {
                    tableModel.addRow(new Object[]{user.getUsername(), user.getEmail(), user.getDateOfBirth()});
                    count++;
                }
            }
            percentage = (double) count / nbUsers * 100;
            System.out.println(percentage);
            System.out.println(count);
            System.out.println(users.size());
            percentageLabel.setText("Pourcentage d'utilisateurs correspondant : %.2f%s".formatted(percentage, "%"));
        } catch (Exception ex) {
            percentageLabel.setText("Pourcentage d'utilisateurs correspondant : ");
            mainWindow.displayError(ex.toString());
        }
    }

    private void resetRows() {
        tableModel.setRowCount(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submit();
        }
    }

    public Date[] getDateRange(String ageRange)  {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Date startDate;
        Date endDate;

        switch (ageRange) {
            case "0-17":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -17);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -0);
                startDate = cal.getTime();
                break;
            case "18-25":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -25);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -18);
                startDate = cal.getTime();
                break;
            case "26-49":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -49);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -26);
                startDate = cal.getTime();
                break;
            case "50-64":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -64);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -50);
                startDate = cal.getTime();
                break;
            case "65+":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -120); // 120 Age maximum
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -65);
                startDate = cal.getTime();
                break;
            default:
                mainWindow.displayError("Tranche d'age invalide");
                return null;
        }
        return new Date[]{startDate, endDate};
    }
}
