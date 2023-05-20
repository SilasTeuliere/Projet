package interfaceAppli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NouvelEvenement {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel label;

    private JTextField dateField;
    private JTextArea descriptionArea;

    public NouvelEvenement(JPanel cardPanel, CardLayout cardLayout, JLabel label) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.label = label;

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Création de la fenêtre
        JFrame fenetre = new JFrame("Nouvel Événement");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Création du panneau pour le formulaire
        JPanel formulairePanel = new JPanel(new GridLayout(2, 2));

        // Champ Date
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JTextField(20);
        formulairePanel.add(dateLabel);
        formulairePanel.add(dateField);

        // Champ Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea(10, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formulairePanel.add(descriptionLabel);
        formulairePanel.add(scrollPane);

        fenetre.getContentPane().add(formulairePanel, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation
        JPanel boutonsPanel = new JPanel(new FlowLayout());

        JButton boutonConfirmer = new JButton("Confirmer");
        boutonConfirmer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String description = descriptionArea.getText();

                // Traiter les informations du nouvel événement ici...

                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                fenetre.dispose();
            }
        });
        boutonsPanel.add(boutonConfirmer);

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                fenetre.dispose();
            }
        });
        boutonsPanel.add(boutonAnnuler);

        fenetre.getContentPane().add(boutonsPanel, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        fenetre.pack();
        fenetre.setVisible(true);
    }
}

