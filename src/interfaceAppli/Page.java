package interfaceAppli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Page {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static JTextField nomPrenomField;
    private static JTextField adresseField;
    private static JTextField emailField;
    private static JTextField telephoneField;
    private static JTextField statutField;
    private static JTextField dateField;
    private static JTextArea descriptionArea;
    private static JTextField idMembreField;
    private static JTextField budgetField;
    private static JTextField idMembreSuppressionField;
    
   
    public static void t1main(String[] args) {
        // Création de la fenêtre
        JFrame fenetre = new JFrame("Ma Interface Swing");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Création du label
        JLabel label = new JLabel("Menu");
        label.setHorizontalAlignment(JLabel.CENTER);
        fenetre.getContentPane().add(label, BorderLayout.NORTH);

        // Création du CardLayout et du panel pour les cartes
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Création de la première carte (Menu)
        JPanel carteMenu = new JPanel(new BorderLayout());
        JTextArea zoneLecture = new JTextArea(5, 20);
        zoneLecture.append("Action à réaliser : \n"
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
                }else if (texteSaisi.equals("I")) {
                    cardLayout.show(cardPanel, "InscriptionEvenement");
                    label.setText("Inscription Événement");
                } else if (texteSaisi.contains("S")) {
                    cardLayout.show(cardPanel, "SuppressionMembre");
                    label.setText("Suppression de Membre");
                } else if (texteSaisi.equalsIgnoreCase("C")) {
                    cardLayout.show(cardPanel, "ChangerStatut");
                    label.setText("Changer de Statut");
                } else if (texteSaisi.equals("1")) {
                    JOptionPane.showMessageDialog(fenetre, "Le mail d'information a bien été préparé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else if (texteSaisi.equals("2")) {
                    JOptionPane.showMessageDialog(fenetre, "Le mail d'information sur les fournitures a bien été préparé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else if (texteSaisi.equals("3")) {
                    JOptionPane.showMessageDialog(fenetre, "Il reste tant à acheter pour tant d'euros", "Check List", JOptionPane.PLAIN_MESSAGE);
                } else if (texteSaisi.equals("L1")) {
                    //TEST
                    String[] membres = {"Membre 1", "Membre 2", "Membre 3"};

                    // Création du texte à afficher dans la boîte de dialogue
                    StringBuilder sb = new StringBuilder();
                    for (String membre : membres) {
                        sb.append(membre).append("\n");
                    }

                    // Affichage de la boîte de dialogue avec le contenu de la liste des membres
                    JOptionPane.showMessageDialog(fenetre, sb.toString(), "Liste des Membres", JOptionPane.PLAIN_MESSAGE);
                } else if (texteSaisi.equals("L2")) {
                    // Test
                    String[] evenements = {"Évènement 1", "Évènement 2", "Évènement 3"};

                    // Création du texte à afficher dans la boîte de dialogue
                    StringBuilder sb = new StringBuilder();
                    for (String evenement : evenements) {
                        sb.append(evenement).append("\n");
                    }

                    // Affichage de la boîte de dialogue avec le contenu de la liste des évènements
                    JOptionPane.showMessageDialog(fenetre, sb.toString(), "Liste des Évènements", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        panneauSaisie.add(bouton);

        carteMenu.add(panneauSaisie, BorderLayout.SOUTH);

        // Ajout de la première carte (Menu) au panel des cartes
        cardPanel.add(carteMenu, "Menu");

        // Création de la deuxième carte (NouveauMembre)
        JPanel carteNouveauMembre = new JPanel(new BorderLayout());
        JPanel formulairePanel = new JPanel(new GridLayout(6, 2));

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

        carteNouveauMembre.add(formulairePanel, BorderLayout.CENTER);

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
                clearFields();
            }
        });
        boutonsPanel.add(boutonConfirmer);

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                clearFields();
            }
        });
        boutonsPanel.add(boutonAnnuler);

        carteNouveauMembre.add(boutonsPanel, BorderLayout.SOUTH);

        // Ajout de la deuxième carte (NouveauMembre) au panel des cartes
        cardPanel.add(carteNouveauMembre, "NouveauMembre");

        // Création de la troisième carte (NouvelEvenement)
        JPanel carteNouvelEvenement = new JPanel(new BorderLayout());
        JPanel evenementPanel = new JPanel(new GridLayout(3, 2));

        // Champ Date
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JTextField(20);
        evenementPanel.add(dateLabel);
        evenementPanel.add(dateField);

        // Champ Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane scrollPane1 = new JScrollPane(descriptionArea);
        evenementPanel.add(descriptionLabel);
        evenementPanel.add(scrollPane1);

        carteNouvelEvenement.add(evenementPanel, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation
        JPanel boutonsPanelEvenement = new JPanel(new FlowLayout());

        JButton boutonConfirmerEvenement = new JButton("Confirmer");
        boutonConfirmerEvenement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String description = descriptionArea.getText();

                // Traiter les informations du nouvel événement ici...

                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                clearFieldsEvenement();
            }
        });
        boutonsPanelEvenement.add(boutonConfirmerEvenement);

        JButton boutonAnnulerEvenement = new JButton("Annuler");
        boutonAnnulerEvenement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                clearFieldsEvenement();
            }
        });
        boutonsPanelEvenement.add(boutonAnnulerEvenement);

        carteNouvelEvenement.add(boutonsPanelEvenement, BorderLayout.SOUTH);

        // Ajout de la troisième carte (NouvelEvenement) au panel des cartes
        cardPanel.add(carteNouvelEvenement, "NouvelEvenement");

        fenetre.getContentPane().add(cardPanel, BorderLayout.CENTER);
        
     // Création de la quatrième carte (InscriptionEvenement)
        JPanel carteInscriptionEvenement = new JPanel(new BorderLayout());
        JPanel inscriptionPanel = new JPanel(new GridLayout(2, 2));

        // Champ Id de Membre
        JLabel idMembreLabel = new JLabel("Id de Membre:");
        idMembreField = new JTextField(20); // Utilisation de la variable statique existante
        inscriptionPanel.add(idMembreLabel);
        inscriptionPanel.add(idMembreField);

        // Champ Budget
        JLabel budgetLabel = new JLabel("Budget:");
        budgetField = new JTextField(20); // Utilisation de la variable statique existante
        inscriptionPanel.add(budgetLabel);
        inscriptionPanel.add(budgetField);

        carteInscriptionEvenement.add(inscriptionPanel, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation
        JPanel boutonsPanelInscription = new JPanel(new FlowLayout());

        JButton boutonConfirmerInscription = new JButton("Confirmer");
        boutonConfirmerInscription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idMembre = Integer.parseInt(idMembreField.getText());
                float budget = Float.parseFloat(budgetField.getText());
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                clearFieldsInscription();
            }
        });
        boutonsPanelInscription.add(boutonConfirmerInscription);

        JButton boutonAnnulerInscription = new JButton("Annuler");
        boutonAnnulerInscription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                clearFieldsInscription();
            }
        });
        boutonsPanelInscription.add(boutonAnnulerInscription);

        carteInscriptionEvenement.add(boutonsPanelInscription, BorderLayout.SOUTH);

        // Ajout de la 5ieme carte (InscriptionEvenement) au panel des cartes
        cardPanel.add(carteInscriptionEvenement, "InscriptionEvenement");

        // Création de la quatrième carte (SuppressionMembre)
        JPanel carteSuppressionMembre = new JPanel(new BorderLayout());
        JPanel formulairePanelSuppressionMembre = new JPanel(new GridLayout(1, 2));

        // Champ ID Membre
        JLabel idMembreLabelSuppression = new JLabel("ID Membre:");
        JTextField idMembreFieldSuppression = new JTextField(20);
        formulairePanelSuppressionMembre.add(idMembreLabelSuppression);
        formulairePanelSuppressionMembre.add(idMembreFieldSuppression);

        carteSuppressionMembre.add(formulairePanelSuppressionMembre, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation
        JPanel boutonsPanelSuppressionMembre = new JPanel(new FlowLayout());

        JButton boutonConfirmerSuppressionMembre = new JButton("Confirmer");
        boutonConfirmerSuppressionMembre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idMembre = Integer.parseInt(idMembreFieldSuppression.getText());

                // Traiter la suppression du membre ici...

                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                idMembreFieldSuppression.setText("");
                clearFieldsSuppressionMembre();
            }
        });
        boutonsPanelSuppressionMembre.add(boutonConfirmerSuppressionMembre);

        JButton boutonAnnulerSuppressionMembre = new JButton("Annuler");
        boutonAnnulerSuppressionMembre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
                idMembreFieldSuppression.setText("");
                clearFieldsSuppressionMembre();
            }
        });
        boutonsPanelSuppressionMembre.add(boutonAnnulerSuppressionMembre);

        carteSuppressionMembre.add(boutonsPanelSuppressionMembre, BorderLayout.SOUTH);

        // Ajout de la 5ieme carte (SuppressionMembre) au panel des cartes
        cardPanel.add(carteSuppressionMembre, "SuppressionMembre");
        
        // Création de la 6ieme carte (ChangerStatut)
        JPanel carteChangerStatut = new JPanel(new BorderLayout());
        JPanel formulairePanelChangerStatut = new JPanel(new GridLayout(3, 2));

        // Champs de saisie pour l'ID du membre et le nouveau statut
        JLabel idMembreChangerStatutLabel = new JLabel("ID Membre:");
        idMembreField = new JTextField(10);
        formulairePanelChangerStatut.add(idMembreChangerStatutLabel);
        formulairePanelChangerStatut.add(idMembreField);

        JLabel statutChangerStatutLabel = new JLabel("Statut:");
        statutField = new JTextField(10);
        formulairePanelChangerStatut.add(statutChangerStatutLabel);
        formulairePanelChangerStatut.add(statutField);

        carteChangerStatut.add(formulairePanelChangerStatut, BorderLayout.CENTER);

        // Boutons de confirmation et d'annulation pour le formulaire ChangerStatut
        JPanel boutonsPanelChangerStatut = new JPanel();

        JButton boutonConfirmerChangerStatut = new JButton("Confirmer");
        boutonConfirmerChangerStatut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Traitement du changement de statut du membre
                clearFieldsChangerStatut();
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
            }
        });
        boutonsPanelChangerStatut.add(boutonConfirmerChangerStatut);

        JButton boutonAnnulerChangerStatut = new JButton("Annuler");
        boutonAnnulerChangerStatut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFieldsChangerStatut();
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Menu");
                label.setText("Menu");
            }
        });
        boutonsPanelChangerStatut.add(boutonAnnulerChangerStatut);

        carteChangerStatut.add(boutonsPanelChangerStatut, BorderLayout.SOUTH);

        // Ajout de la 6ieme carte (ChangerStatut) au panel des cartes
        cardPanel.add(carteChangerStatut, "ChangerStatut");
        
        // Affichage de la fenêtre
        fenetre.pack();
        fenetre.setVisible(true);
    }

    private static void clearFields() {
        nomPrenomField.setText("");
        adresseField.setText("");
        emailField.setText("");
        telephoneField.setText("");
        statutField.setText("");
    }

    private static void clearFieldsEvenement() {
        dateField.setText("");
        descriptionArea.setText("");
    }
    
    // Méthode pour vider les champs de saisie de l'inscription à l'événement
    private static void clearFieldsInscription() {
        idMembreField.setText("");
        budgetField.setText("");
    }
    
    private static void clearFieldsSuppressionMembre() {
        idMembreSuppressionField.setText("");
    }

    private static void clearFieldsChangerStatut() {
        idMembreField.setText("");
        statutField.setText("");
    }
}
