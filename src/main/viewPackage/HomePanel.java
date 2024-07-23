package main.viewPackage;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private JLabel welcomeText;

    public HomePanel() {
        setLayout(new BorderLayout());

        welcomeText = new JLabel(
                "<html>Bienvenue dans l'application simulant un réseau social <br> <br>" +
                        "Voici les fonctionnalités de base : <br>" +
                        "- Opérations CRUD <br>" +
                        "- Recherches <br>" +
                        "- Tâche métier <br>" +
                        "- Animation de like (Thread) <br> <br>" +
                        "Pour commencer, sélectionnez une option dans le menu" +
                        "</html>"
        );
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeText, BorderLayout.CENTER);
    }
}
