package interfaceAppli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static JLabel label;

    private static NouveauMembre nouveauMembre;
    private static NouvelEvenement nouvelEvenement;

    public static void t2main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Création de la fenêtre
        JFrame fenetre = new JFrame("Mon Interface Swing");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Création du label
        label = new JLabel("Menu");
        label.setHorizontalAlignment(JLabel.CENTER);
        fenetre.getContentPane().add(label, BorderLayout.NORTH);

        // Création du CardLayout et du panel pour les cartes
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Création de la première carte (Menu)
        JPanel carteMenu = new JPanel(new BorderLayout());
        JTextArea zoneLecture = new JTextArea(5, 20);
        zoneLecture.append("Action à réaliser : \n - 'T' pour test\n - 'B' pour initialiser la liste du bureau et des membres  - ne faire qu'une fois si pas passé par test\n"
        		+ " - 'M' pour ajouter des membres\n"
        		+ " - 'E' pour créer des événements\n"
        		+ " - '1' pour préparer mail d'information sur l'événement\n"
        		+ " - 'I' pour inscrire des membres à l'événement\n"
        		+ " - '2' pour préparer mail d'information sur les fournitures à amener\n"
        		+ " - '3' pour finaliser la préparation - check list reste à faire pour le trésorier\n"
        		+ " - 'L1' pour lister les membres\n"
        		+ " - 'L2' pour lister les événements\n"
        		+ " - 'S' pour lister supprimer un membre\n"
        		+ " - 'C' pour changer le statut d'un membre\n"
        		+ " - 'retour chariot' pour sortir");
        zoneLecture.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(zoneLecture);
        carteMenu.add(scrollPane, BorderLayout.CENTER);

        // Création du panneau pour la zone de saisie et le bouton
        JPanel panneauSaisie = new JPanel(new FlowLayout());

        // Création du label "Texte à saisir"
        JLabel labelSaisie = new JLabel("Texte à saisir :");
        panneauSaisie.add(labelSaisie);

        // Création de la zone de texte pour la saisie de texte
        JTextField zoneSaisie = new JTextField(20);
        panneauSaisie.add(zoneSaisie);

        // Création du bouton de confirmation
        JButton bouton = new JButton("Confirmer");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texteSaisi = zoneSaisie.getText();
                zoneLecture.append(texteSaisi + "\n");
                zoneSaisie.setText("");

                if (texteSaisi.contains("M")) {
                    cardLayout.show(cardPanel, "NouveauMembre");
                    label.setText("Nouveau Membre");
                } else if (texteSaisi.contains("E")) {
                    cardLayout.show(cardPanel, "NouvelEvenement");
                    label.setText("Nouvel Événement");
                }
            }
        });
        panneauSaisie.add(bouton);

        carteMenu.add(panneauSaisie, BorderLayout.SOUTH);

        // Ajout de la première carte (Menu) au panel des cartes
        cardPanel.add(carteMenu, "Menu");

        fenetre.getContentPane().add(cardPanel, BorderLayout.CENTER);

        // Création des instances des autres classes
        nouveauMembre = new NouveauMembre(cardPanel, cardLayout, label);
        nouvelEvenement = new NouvelEvenement(cardPanel, cardLayout, label);

        // Affichage de la fenêtre
        fenetre.pack();
        fenetre.setVisible(true);
    }
}

