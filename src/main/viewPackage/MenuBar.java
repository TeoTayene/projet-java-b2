package main.viewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    private MainWindow mainWindow;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menuCRUD;
    private JMenu menuResearch;
    private JMenu menuBusinessTask;
    private JMenu menuThread;
    private JMenuItem home;
    private JMenuItem listing;
    private JMenuItem exit;
    private JMenuItem JobTaskCountry;
    private JMenuItem JobTaskAge;
    private JMenuItem researchLike;
    private JMenuItem researchCommunity;
    private JMenuItem researchPrivateMessage;
    private JMenuItem likeAnimation;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuCRUD = new JMenu("CRUD");
        menuResearch = new JMenu("Recherches");
        menuBusinessTask = new JMenu("Tâche métier");
        menuThread = new JMenu("Thread");

        home = new JMenuItem("Retour à l'acceuil");
        home.addActionListener(this);

        listing = new JMenuItem("Liste des utilisateurs");
        listing.addActionListener(this);

        researchLike = new JMenuItem("Combien de like entre deux dates ?");
        researchLike.addActionListener(this);

        researchCommunity = new JMenuItem("Qui est dans la communauté ?");
        researchCommunity.addActionListener(this);

        researchPrivateMessage = new JMenuItem("Qui a envoyé un message privé à qui ?");
        researchPrivateMessage.addActionListener(this);

        JobTaskAge = new JMenuItem("Age moyen des utilisateurs");
        JobTaskAge.addActionListener(this);

        JobTaskCountry = new JMenuItem("Nombre d'utilisateurs par pays");
        JobTaskCountry.addActionListener(this);

        likeAnimation = new JMenuItem("Animation de like (Thread)");
        likeAnimation.addActionListener(this);

        exit = new JMenuItem("Quitter le programme");
        exit.addActionListener(this);

        menu.add(home);
        menu.addSeparator();
        menu.add(exit);

        menuCRUD.add(listing);

        menuResearch.add(researchLike);
        menuResearch.addSeparator();
        menuResearch.add(researchCommunity);
        menuResearch.addSeparator();
        menuResearch.add(researchPrivateMessage);

        menuBusinessTask.add(JobTaskAge);
        menuBusinessTask.addSeparator();
        menuBusinessTask.add(JobTaskCountry);

        menuThread.add(likeAnimation);

        menuBar.add(menu);
        menuBar.add(menuCRUD);
        menuBar.add(menuResearch);
        menuBar.add(menuBusinessTask);
        menuBar.add(menuThread);

        this.mainWindow.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            mainWindow.switchPanel(mainWindow.getHomePanel());
        } else if (e.getSource() == JobTaskCountry) {
            mainWindow.switchPanel(mainWindow.getJobTaskCountryPanel());
        } else if (e.getSource() == JobTaskAge) {
            mainWindow.switchPanel(mainWindow.getJobTaskAgePanel());
        } else if (e.getSource() == listing) {
            mainWindow.switchPanel(mainWindow.getListingPanel());
        } else if (e.getSource() == researchLike) {
            mainWindow.switchPanel(mainWindow.getResearchLike());
        } else if (e.getSource() == researchCommunity) {
            mainWindow.switchPanel(mainWindow.getResearchCommunity());
        } else if (e.getSource() == researchPrivateMessage) {
            mainWindow.switchPanel(mainWindow.getResearchPrivateMessage());
        } else if (e.getSource() == likeAnimation) {
            mainWindow.switchPanel(mainWindow.getThreadPanel());
        } else if (e.getSource() == exit) {
            mainWindow.exit();
        }
    }
}
