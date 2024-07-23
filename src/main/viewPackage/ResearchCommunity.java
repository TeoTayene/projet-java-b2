package main.viewPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import main.controllerPackage.CommunityController;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.NonEditableTableModel;
import main.modelPackage.MemberModel;

public class ResearchCommunity  extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private JComboBox<String> communityComboBox;
    private JButton submitButton;
    private CommunityController communityController;
    private DefaultTableModel tableModel;
    private CommunityModel selectedCommunity;

    public ResearchCommunity(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;

        JLabel title = new JLabel("Sélectionner la commmunauté pour laquelle vous voulez voir les utilisateurs :");
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

        communityController = new CommunityController();
        communityComboBox = new JComboBox<>();

        communityComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(communityComboBox, gbc);

        submitButton = new JButton("Rechercher");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);
        submitButton.addActionListener(this);

        String[] columnNames = {"Nom de l'utilisateur", "Rue et numéro ", "Région", "Code postal", "Pays"};
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

    public void refreshData() throws CommunityDAOException {
        communityComboBox.removeAllItems();
        resetRows();

        List<CommunityModel> communities = communityController.getAllCommunities();
        for (CommunityModel community : communities) {
            communityComboBox.addItem(community.getName());
        }
    }

    private void resetRows() {
        tableModel.setRowCount(0);
    }

    private void submit() {
        try {
            String selectedCommunityName = (String) communityComboBox.getSelectedItem();

            for (CommunityModel community : communityController.getAllCommunities()) {
                if (community.getName().equals(selectedCommunityName)) {
                    selectedCommunity = community;
                    break;
                }
            }

            if (selectedCommunity == null) {
                mainWindow.displayError("La communauté sélectionnée n'existe pas.");
                return;
            }

            int communityId = selectedCommunity.getId();
            resetRows();
            List<MemberModel> members = communityController.getCommunityById(communityId);

            for (MemberModel member : members) {
                Object[] data = {
                        member.getUsername(),
                        member.getStreetAndNumber(),
                        member.getLocationName(),
                        member.getZipCode(),
                        member.getCountry()
                };
                tableModel.addRow(data);
            }

        } catch (CommunityDAOException exception) {
            mainWindow.displayError(exception.toString());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submit();
        }
    }
}
