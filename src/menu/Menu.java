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
	 * Saisie des �v�nements
	 * @param club
	 * @param sc
	 */
	public static String choisirAction() {
		
		System.out.println("Action � r�aliser : \n - 'T' pour test\n - 'B' pour initialiser la liste du bureau et des membres  - ne faire qu'une fois si pas pass� par test\n"
				+ " - 'M' pour ajouter des membres\n"
				+ " - 'E' pour cr�er des �v�nements\n"
				+ " - '1' pour pr�parer mail d'information sur l'�v�nement\n"
				+ " - 'I' pour inscrire des membres � l'�v�nement\n"
				+ " - '2' pour pr�parer mail d'information sur les fournitures � amener\n"
				+ " - '3' pour finaliser la pr�paration - check list reste � faire pour le tr�sorier\n"
				+ " - 'L1' pour lister les membres\n"
				+ " - 'L2' pour lister les �v�nements\n"			
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
		// pour compl�ter il faudrait pr�voir aussi la gestion d'un mot de passe - en particulier pour les membres du bureau
		String nomPrenom = "";
		String email = "";
		String adresse = "";
		String numTel = "";
		String[] libelleMembre = new String[] {"Veuillez saisir le Pr�sident de l'association :", 
		           "Veuillez saisir le Secr�taire de l'association :", 
		           "Veuillez saisir le Tr�sorier de l'association :",
		           "Veuillez saisir un autre membre (\"Entr�e\" quand termin�) :"};
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
			System.out.println("Pr�nom et nom du membre :");
			nomPrenom = sc.nextLine();
			if (i<3 || !nomPrenom.equals("")) {
				System.out.println("Adresse mail :");
				email = sc.nextLine();
				System.out.println("Adresse postale :");
				adresse = sc.nextLine();
				System.out.println("Num�ro de t�l�phone :");
				numTel = sc.nextLine();
				sauvegarderMembres(club, nomPrenom, email, adresse, numTel, i);
			}
			i++;
			// Saisie minimum un pr�sident, un secr�taire et tr�sorier - m�me si nomPr�nom est non valoris�...
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
	 * Saisie des �v�nements
	 * @param club
	 */
	public static void saisirEvenements(Club club) {
		
		// Qui est le pr�sident - qui seul peut cr�er un �v�nement
		// pour compl�ter il faudrait faire saisir son nom pr�nom et son mot de passe au pr�sident
		President president = (President) club.rechercherStatut(Statut.PRESIDENT);
		
		System.out.println("---------------------------------------------------------");
		LocalDateTime dateEvenement;
		String description = "";
		String dateHeure = "";
		
		do {
			System.out.println("Ajout d'un nouvel �v�nement :");
			System.out.println("Description (retour chariot pour sortir):");
			description = sc.nextLine();
			if (!description.equals("")) {
				System.out.println("Date de l'�venement au format 'aaaa-mm-jj :");
				dateHeure = sc.nextLine();
				System.out.println("Heure de l'�venement au format 'hh:mm' :");
				dateHeure += "T" + sc.nextLine() + ":00";
				try {
				  dateEvenement = LocalDateTime.parse(dateHeure);
				  club.setEvenements(president.ajoutEven(club.getEvenements(), dateEvenement, description)) ;
				} catch (DateTimeParseException e) {
				  System.out.println("Erreur sur la date ou l'heure saisie - ressaisir l'�v�nement");
				}
							}
		} while (!description.equals(""));
	}

	

	/**
	 * Saisie des inscrits pour un �v�nement
	 * @param club
	 */
	public static void saisirInscriptions(Club club) {
		
		// Qui est le secr�taire - qui seul peut inscrire � un �v�nement
		// pour compl�ter il faudrait faire saisir son nom pr�nom et son mot de passe au secr�taire
		Secretaire secretaire = (Secretaire) club.rechercherStatut(Statut.SECRETAIRE);
		
		System.out.println("---------------------------------------------------------");
		
		//Dans cet exercice je ne pr�vois la saisie que pour un seul des �v�nements, le premier 
		// (sinon je demanderais d'entrer la date et heure de l'�v�nement
		Evenement evenement = club.getEvenements()[0];

		int numeroId;
		double montantPrevu = 0;
		String saisie = "";
		do {
			System.out.println("Ajout d'un nouvel inscrit pour l'�v�nement du " 
		+ evenement.getDate().getDayOfMonth() + "/" + (evenement.getDate().getMonthValue()) + "/" + evenement.getDate().getYear() + " :");
			System.out.println("Num�ro membre (retour chariot pour sortir) :");
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
					System.out.println("Personne non trouv�e saisir un autre n�");
				} else {
					System.out.println("Membre concern� : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
					saisie = sc.nextLine();
					if (saisie.length() > 0 && saisie.toUpperCase().charAt(0) == 'O') {
						System.out.println("Montant pr�vu :");
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
		System.out.println("Num�ro membre (retour chariot pour sortir) :");
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
				System.out.println("Personne non trouv�e saisir un autre n�");
			} else {
				System.out.println("Membre concern� : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
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
		System.out.println("Num�ro membre (retour chariot pour sortir) :");
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
				System.out.println("Personne non trouv�e saisir un autre n�");
			} else {
				System.out.println("Membre concern� : " + membre.getNomPrenom() + " (Confirmer en tapant 'o' ou 'O')");
				saisie = sc.nextLine();
				if (saisie.length() > 0 && saisie.toUpperCase().charAt(0) == 'O') {
					System.out.println("Nouveau statut :");
					saisie = sc.nextLine();
					// Le statut saisie doit �tre un de lib�ll� des statuts possibles
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
