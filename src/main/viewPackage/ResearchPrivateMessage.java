package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.controllerPackage.DirectMessageController;
import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.DirectMessageModel;
import main.modelPackage.NonEditableTableModel;
import main.modelPackage.UserModel;

public class ResearchPrivateMessage  extends JPanel implements ActionListener {
    private JComboBox<UserItem> privateMessageComboBox = new JComboBox<>();
    private JButton searchButton;
    private NonEditableTableModel tableModel;
    private UserController userController;
    private DirectMessageController directMessageController;

    public ResearchPrivateMessage() throws ConnectionDataAccessException {
        userController = new UserController();
        directMessageController = new DirectMessageController();

        JLabel title = new JLabel("Selectioner l'email d'un utilisateur pour voir ses messages privés : ");
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

        privateMessageComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(privateMessageComboBox, gbc);

        searchButton = new JButton("Rechercher");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(searchButton, gbc);
        searchButton.addActionListener(this);

        String[] columnNames = {"Nom du receveur", "Text", "URL du media", "Type du media"};
        tableModel = new NonEditableTableModel(columnNames, 0);
        JTable tableDm = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableDm);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }

    public void refreshData() throws UserSearchException {
        privateMessageComboBox.removeAllItems();
        resetRows();

        List<UserModel> users = userController.getAllUsers();
        for (UserModel user : users) {
            privateMessageComboBox.addItem(new UserItem(user.getId(), user.getEmail()));
        }
    }

    private void submit() {
        UserItem userSelected = (UserItem) privateMessageComboBox.getSelectedItem();

        if (userSelected == null) {
            return;
        }

        try {
            List<DirectMessageModel> messages = directMessageController.getDirectMessagesByUserId(userSelected.getUserId());
            resetRows();

            for (DirectMessageModel message : messages) {
                Object[] rowData = {
                        message.getSender().getUsername(),
                        message.getText(),
                        message.getMediaUrl(),
                        message.getMediaType()
                };
                tableModel.addRow(rowData);
            }

        } catch (DirectMessageException e) {
            MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(this);
            mainWindow.displayError(e.toString());
            resetRows();
        }
    }

    private void resetRows() {
        tableModel.setRowCount(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            submit();
        }
    }
}
