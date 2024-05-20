package main.viewPackage;

import main.exceptionPackage.LocalityException;
import main.modelPackage.UserModel;
import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ListingPanel extends JPanel implements ActionListener {

    private MainWindow mainWindow;
    private JTable tableUsers;
    private ArrayList<UserModel> users = new ArrayList<>();
    private String[] columnNames;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonAdd;
    private JPanel addUserPanel;

    public ListingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());

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

        buttonAdd = new JButton("Ajouter un utilisateur");
        buttonAdd.addActionListener(this);

        buttonUpdate = new JButton("Modifier un utilisateur");
        buttonDelete = new JButton("Supprimer un utilisateur");

        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonUpdate);
        buttonPanel.add(buttonDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        try {
            Connection con = ConnectionDataAccess.getInstance();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
        } catch (ConnectionDataAccessException | SQLException e) {
            e.printStackTrace();
        }
        tableUsers = new JTable(new Object[][]{}, columnNames);
        scrollPane.setViewportView(tableUsers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            if (addUserPanel == null) {
                try {
                    this.addUserPanel = new AddUserPanel(mainWindow);
                    mainWindow.switchPanel(addUserPanel);
                } catch (CountriesDAOException | ConnectionDataAccessException | LocalityException ex) {
                    mainWindow.displayError(ex.toString());
                }
            } else {
                mainWindow.switchPanel(addUserPanel);
            }
        }
    }
}
