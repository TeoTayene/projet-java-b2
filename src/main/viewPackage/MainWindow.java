package main.viewPackage;

import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.UserSearchException;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final String WINDOW_TITLE = "Social Network";
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int X_BOUNDS = (SCREEN_SIZE.width - FRAME_WIDTH) / 2;
    private static final int Y_BOUNDS = (SCREEN_SIZE.height - FRAME_HEIGHT) / 2;

    private JPanel homePanel;
    private JPanel listingPanel;
    private JPanel researchPrivateMessage;
    private JPanel researchLike;
    private JPanel researchCommunity;
    private JPanel jobTaskCountryPanel;
    private JPanel jobTaskAgePanel;
    private JPanel threadPanel;
    private MenuBar menuBar;

    public MainWindow() {
        super(WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(X_BOUNDS, Y_BOUNDS, FRAME_WIDTH, FRAME_HEIGHT);

        try {
            ConnectionDataAccess.getInstance();
            listingPanel = new ListingPanel(this);
            homePanel = new HomePanel();
            jobTaskCountryPanel = new JobTaskCountryPanel(this);
            jobTaskAgePanel = new JobTaskAgePanel(this);
            menuBar = new MenuBar(this);
            researchPrivateMessage = new ResearchPrivateMessage();
            researchLike = new ResearchLike();
            researchCommunity = new ResearchCommunity(this);
            threadPanel = new ThreadPanel();
        } catch (ConnectionDataAccessException e) {
            displayError(e.toString());
            exit();
        }

        switchPanel(homePanel);
        setVisible(true);
    }

    public void exit() {
        try {
            ConnectionDataAccess.closeConnection();
            System.exit(1);
        } catch (ConnectionDataAccessException ex) {
            displayError(ex.toString());
            System.exit(1);
        }
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void switchPanel(JPanel panel) {
        if (panel == listingPanel) {
            try {
                ((ListingPanel) listingPanel).refreshUsersData();
                paintPanel(panel);
            } catch (UserSearchException e) {
                displayError(e.toString());
            }
        } else if (panel == researchCommunity) {
            try {
                ((ResearchCommunity) researchCommunity).refreshData();
                paintPanel(panel);
            } catch (CommunityDAOException e) {
                displayError(e.toString());
            }

        } else if (panel == researchPrivateMessage) {
            try {
                ((ResearchPrivateMessage) researchPrivateMessage).refreshData();
                paintPanel(panel);
            } catch (UserSearchException e) {
                displayError(e.toString());
            }
        } else paintPanel(panel);
    }

    private void paintPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public JPanel getHomePanel() {
        return homePanel;
    }

    public JPanel getListingPanel() {
        return listingPanel;
    }

    public JPanel getJobTaskAgePanel() {
        return jobTaskAgePanel;
    }

    public JPanel getJobTaskCountryPanel() {
        return jobTaskCountryPanel;
    }

    public JPanel getResearchPrivateMessage() {
        return researchPrivateMessage;
    }

    public JPanel getResearchLike() {
        return researchLike;
    }

    public JPanel getResearchCommunity() {
        return researchCommunity;
    }

    public JPanel getThreadPanel() {
        return threadPanel;
    }
}
