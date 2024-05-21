package main.viewPackage;

import main.controllerPackage.UserController;
import main.dataAccessPackage.UserDAOImpl;
import main.modelPackage.UserModel;
import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import javax.swing.*;;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingPanel extends JPanel implements ActionListener {

    private MainWindow mainWindow;
    private JTable tableUsers;
    private List<UserModel> users;
    private List<String> columnsNames;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonAdd;
    private JPanel addUserPanel;


    private UserController userController;

    public ListingPanel(MainWindow mainWindow)  throws  ConnectionDataAccessException {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());

        userController = new UserController();

        // Top label
//        JLabel label = new JLabel("Listing Panel", SwingConstants.CENTER);
//        add(label, BorderLayout.NORTH);

        // Adding a vertical gap
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(0, 20)); // 20 pixels height gap
        add(gapPanel, BorderLayout.CENTER);

        // Table in the center
        tableUsers = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonAdd = new JButton("Add User");
        buttonAdd.addActionListener(this);

        buttonUpdate = new JButton("Update User");
        buttonDelete = new JButton("Delete User");

        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonUpdate);
        buttonPanel.add(buttonDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        users = userController.getAllUsers();
        columnsNames = userController.getColumnsNames();

        tableUsers = updateTable(users, columnsNames);
        scrollPane.setViewportView(tableUsers);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            if (addUserPanel == null) {
                try {
                    this.addUserPanel = new AddUserPanel();
                    mainWindow.switchPanel(addUserPanel);
                } catch (CountriesDAOException | ConnectionDataAccessException ex) {
                    mainWindow.displayError(ex.toString());
                }
            } else {
                mainWindow.switchPanel(addUserPanel);
            }
        }
    }

    public JTable updateTable(List<UserModel> users, List<String> columnsNames) {
        JTable table;
        Object[][] data = new Object[users.size()][columnsNames.size()];
        for (int i = 0; i < users.size(); i++) {
            UserModel user = users.get(i);
            data[i][0] = user.getEmail();
            data[i][1] = user.getUsername();
            data[i][2] = user.getPassword();
            data[i][3] = user.getDateOfBirth();
            data[i][4] = user.getStreetAndNumber();
            data[i][5] = user.getPhoneNumber();
            data[i][6] = user.getBio();
            data[i][7] = user.isAdmin();
        }
        table = new JTable(data, columnsNames.toArray());
        return table;
    }
}

