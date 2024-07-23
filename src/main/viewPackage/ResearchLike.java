package main.viewPackage;

import main.controllerPackage.LikeController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.NonEditableTableModel;
import main.modelPackage.LikeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ResearchLike extends JPanel implements ActionListener {
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton submitButton;
    private LikeController likeController;
    private NonEditableTableModel tableModel;

    public ResearchLike() throws ConnectionDataAccessException {
        likeController = new LikeController();

        JLabel title = new JLabel("Sélectionner une plage de dates pour trouver les likes entre ces dates :");
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

        JLabel startDateLabel = new JLabel("Date de début :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(startDateLabel, gbc);

        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "dd-MM-yyyy");
        startDateSpinner.setEditor(startDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(startDateSpinner, gbc);

        JLabel endDateLabel = new JLabel("Date de fin :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(endDateLabel, gbc);

        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "dd-MM-yyyy");
        endDateSpinner.setEditor(endDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(endDateSpinner, gbc);

        submitButton = new JButton("Rechercher");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);
        submitButton.addActionListener(this);

        String[] columnNames = {"Nom de l'utilisateur", "Date du like", "Contenu de la publication"};
        tableModel = new NonEditableTableModel(columnNames, 0);
        JTable tableLikes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLikes);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }

    private void submit() {
        Date startDate = new Date(((java.util.Date) startDateSpinner.getValue()).getTime());
        Date endDate = new Date(((java.util.Date) endDateSpinner.getValue()).getTime());

        try {
            List<LikeModel> likes = likeController.getLikesBetween(startDate, endDate);
            resetRows();
            for (LikeModel like : likes) {
                Object[] rowData = {
                    like.getUsername(),
                    like.getDate(),
                    like.getPostContent()
                };
                tableModel.addRow(rowData);
            }
        } catch (LikeSearchException e) {
            MainWindow main = (MainWindow) SwingUtilities.getWindowAncestor(this);
            main.displayError(e.toString());
            resetRows();
        }
    }

    private void resetRows() {
        tableModel.setRowCount(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submit();
        }
    }
}
