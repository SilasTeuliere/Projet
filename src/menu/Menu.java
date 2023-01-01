package menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import club.Club;
import evenement.Evenement;
import membre.Membre;
import membre.President;
import membre.Secretaire;
import membre.Statut;


public class Menu {
	private static Scanner sc = new Scanner(System.in);
	
	public static void fermeScanner() {
		sc.close();
	}
	
	/**
	 * Saisie des événements
	 * @param club
	 * @param sc
	 */
	public static String choisirAction() {
		
		System.out.println("Action à réaliser : \n - 'T' pour test\n - 'B' pour initialiser la liste du bureau et des membres  - ne faire qu'une fois si pas passé par test\n"
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
				+ " - 'retour chariot' pour sortir"
				);
				
		return sc.nextLine();
	}


	/**
	 * Saisie le bureau et tous les membres de l'association
	 * @param club
	 * @param param
	 */
	public static void saisirMembres(Club club, char param) {
		// pour compléter il faudrait prévoir aussi la gestion d'un mot de passe - en particulier pour les membres du bureau
		String nomPrenom = "";
		String email = "";
		String adresse = "";
		String numTel = "";
		String[] libelleMembre = new String[] {"Veuillez saisir le Président de l'association :", 
		           "Veuillez saisir le Secrétaire de l'association :", 
		           "Veuillez saisir le Trésorier de l'association :",
		           "Veuillez saisir un autre membre (\"Entrée\" quand terminé) :"};
		int i;
		if (param == 'I') {
			i = 0;
			club.initMembres();
		} else {
			i = 3;
		}
		do {
			if (i > 3) i = 3;
			System.out.println(libelleMembre[i]);
			System.out.println("Prénom et nom du membre :");
			nomPrenom = sc.nextLine();
			if (i<3 || !nomPrenom.equals("")) {
				System.out.println("Adresse mail :");
				email = sc.nextLine();
				System.out.println("Adresse postale :");
				adresse = sc.nextLine();
				System.out.println("Numéro de téléphone :");
				numTel = sc.nextLine();
				sauvegarderMembres(club, nomPrenom, email, adresse, numTel, i);
			}
			i++;
			// Saisie minimum un président, un secrétaire et trésorier - même si nomPrénom est non valorisé...
		} while (i<3 || !nomPrenom.equals(""));
	}

	/**
	 * Enregistrement d'un membre de l'association
	 * @param club
	 * @param nomPrenom
	 * @param email
	 * @param adresse
	 * @param numTel
	 * @param i
	 */
	private static void sauvegarderMembres(Club club, String nomPrenom, String email, String adresse, String numTel,
			int i) {
		switch (i) {
		case 0:
			if (nomPrenom.equals("")) {
				nomPrenom = Statut.PRESIDENT.toString();
			}
			club.ajoutMembre(nomPrenom, email, adresse, numTel, Statut.PRESIDENT);
			break;
		case 1:
			if (nomPrenom.equals("")) {
				nomPrenom = Statut.SECRETAIRE.toString();
			}
			club.ajoutMembre(nomPrenom, email, adresse, numTel, Statut.SECRETAIRE);
			break;
		case 2:
			if (nomPrenom.equals("")) {
				nomPrenom = Statut.TRESORIER.toString();
			}
			club.ajoutMembre(nomPrenom, email, adresse, numTel, Statut.TRESORIER);
			break;
		default:
			club.ajoutMembre(nomPrenom, email, adresse, numTel);
		}
	}

	/**
	 * Saisie des événements
	 * @param club
	 */
	public static void saisirEvenements(Club club) {
		
		// Qui est le président - qui seul peut créer un événement
		// pour compléter il faudrait faire saisir son nom prénom et son mot de passe au président
		President president = (President) club.rechercherStatut(Statut.PRESIDENT);
		
		System.out.println("---------------------------------------------------------");
		LocalDateTime dateEvenement;
		String description = "";
		String dateHeure = "";
		
		do {
			System.out.println("Ajout d'un nouvel événement :");
			System.out.println("Description (retour chariot pour sortir):");
			description = sc.nextLine();
			if (!description.equals("")) {
				System.out.println("Date de l'évenement au format 'aaaa-mm-jj :");
				dateHeure = sc.nextLine();
				System.out.println("Heure de l'évenement au format 'hh:mm' :");
				dateHeure += "T" + sc.nextLine() + ":00";
				try {
				  dateEvenement = LocalDateTime.parse(dateHeure);
				  club.setEvenements(president.ajoutEven(club.getEvenements(), dateEvenement, description)) ;
				} catch (DateTimeParseException e) {
				  System.out.println("Erreur sur la date ou l'heure saisie - ressaisir l'événement");
				}
							}
		} while (!description.equals(""));
	}

	

	/**
	 * Saisie des inscrits pour un événement
	 * @param club
	 */
	public static void saisirInscriptions(Club club) {
		
		// Qui est le secrétaire - qui seul peut inscrire à un événement
		// pour compléter il faudrait faire saisir son nom prénom et son mot de passe au secrétaire
		Secretaire secretaire = (Secretaire) club.rechercherStatut(Statut.SECRETAIRE);
		
		System.out.println("---------------------------------------------------------");
		
		//Dans cet exercice je ne prévois la saisie que pour un seul des événements, le premier 
		// (sinon je demanderais d'entrer la date et heure de l'événement
		Evenement evenement = club.getEvenements()[0];

		int numeroId;
		double montantPrevu = 0;
		String saisie = "";
		do {
			System.out.println("Ajout d'un nouvel inscrit pour l'événement du " 
		+ evenement.getDate().getDayOfMonth() + "/" + (evenement.getDate().getMonthValue()) + "/" + evenement.getDate().getYear() + " :");
			System.out.println("Numéro membre (retour chariot pour sortir) :");
			saisie = sc.nextLine();
			try {
			   numeroId = Integer.parseInt(saisie);
			} catch (NumberFormatException e) {
				System.out.println("Saisir un nombre entier Positif :");
				numeroId = 999999;
			}
			if (numeroId >= 0 && numeroId < 999999) {
				Membre membre = club.trouverMembre(numeroId); 
				if (membre == null) {
					System.out.println("Personne non trouvée saisir un autre n°");
				} else {
					System.out.println("Membre concerné : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
					saisie = sc.nextLine();
					if (saisie.length() > 0 && saisie.toUpperCase().charAt(0) == 'O') {
						System.out.println("Montant prévu :");
						try {
						  montantPrevu = Double.parseDouble(sc.nextLine());
						  secretaire.ajoutInscrit(evenement, membre, montantPrevu);
						} catch (Exception e) {
						  System.out.println("erreur sur le montant, ressaisir l'inscription...");
						  numeroId = 999999;
						}
					}
				}				
			}
		} while (!saisie.equals(""));
	}

	/**
	 * supprime un membre du club
	 * @param club
	 */
	public static void suprimerMembre(Club club) {
		String saisie = "";
		int numeroId;
		System.out.println("Suppression d'un membre");
		System.out.println("Numéro membre (retour chariot pour sortir) :");
		saisie = sc.nextLine();
		try {
			numeroId = Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			System.out.println("Saisir un nombre entier Positif :");
			return;
		}
		if (numeroId >= 0 ) {
			Membre membre = club.trouverMembre(numeroId); 
			if (membre == null) {
				System.out.println("Personne non trouvée saisir un autre n°");
			} else {
				System.out.println("Membre concerné : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
				saisie = sc.nextLine();
				if (saisie.length() > 0 && saisie.toUpperCase().charAt(0) == 'O') {
				  club.suppMembre(numeroId);
				}
			}				
		}		
	}

	/**
	 * Change le statut d'un membre
	 * @param club
	 */
	public static void changerStatut(Club club) {
		String saisie = "";
		int numeroId;
		System.out.println("Changement du statut d'un membre");
		System.out.println("Numéro membre (retour chariot pour sortir) :");
		saisie = sc.nextLine();
		try {
			numeroId = Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			System.out.println("Saisir un nombre entier Positif :");
			return;
		}
		if (numeroId >= 0 ) {
			Membre membre = club.trouverMembre(numeroId); 
			if (membre == null) {
				System.out.println("Personne non trouvée saisir un autre n°");
			} else {
				System.out.println("Membre concerné : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
				saisie = sc.nextLine();
				if (saisie.length() > 0 && saisie.toUpperCase().charAt(0) == 'O') {
					System.out.println("Nouveau statut :");
					saisie = sc.nextLine();
					// Le statut saisie doit être un de libéllé des statuts possibles
					for (Statut statutTheo : Statut.values()) {
			        	if (statutTheo.toString().equalsIgnoreCase(saisie)) {
			        		club.changerStatut(numeroId, statutTheo);
			        		return;
			        	}
			        }
				    
				}
			}				
		}		

		
	}

}
