package interfaceAppli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NouveauMembre {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel label;

    private JTextField nomPrenomField;
    private JTextField adresseField;
    private JTextField emailField;
    private JTextField telephoneField;
    private JTextField statutField;

    public NouveauMembre(JPanel cardPanel, CardLayout cardLayout, JLabel label) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.label = label;

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Création de la fenêtre
        JFrame fenetre = new JFrame("Nouveau Membre");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Création du panneau pour le formulaire
        JPanel formulairePanel = new JPanel(new GridLayout(5, 2));

        // Champ Nom et Prénom
        JLabel nomPrenomLabel = new JLabel("Nom et Prénom:");
        nomPrenomField = new JTextField(20);
        formulairePanel.add(nomPrenomLabel);
        formulairePanel.add(nomPrenomField);

        // Champ Adresse
        JLabel adresseLabel = new JLabel("Adresse:");
        adresseField = new JTextField(20);
        formulairePanel.add(adresseLabel);
        formulairePanel.add(adresseField);

        // Champ Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        formulairePanel.add(emailLabel);
        formulairePanel.add(emailField);

        // Champ Numéro de téléphone
        JLabel telephoneLabel = new JLabel("Numéro de téléphone:");
        telephoneField = new JTextField(20);
        formulairePanel.add(telephoneLabel);
        formulairePanel.add(telephoneField);

        // Champ Statut
        JLabel statutLabel = new JLabel("Statut:");
        statutField = new JTextField(20);
        formulairePanel.add(statutLabel);
        formulairePanel.add(statutField);

        fenetre.getContentPane().add(formulairePanel, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation
        JPanel boutonsPanel = new JPanel(new FlowLayout());

        JButton boutonConfirmer = new JButton("Confirmer");
        boutonConfirmer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomPrenom = nomPrenomField.getText();
                String adresse = adresseField.getText();
                String email = emailField.getText();
                String telephone = telephoneField.getText();
                String statut = statutField.getText();

                // Traiter les informations du nouveau membre ici...

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

