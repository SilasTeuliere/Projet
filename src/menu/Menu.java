package menu;

import java.time.LocalDateTime;
import java.util.Scanner;

import club.Club;
import membre.President;
import membre.Statut;

public class Menu {
	
	//public static void main(String[] args) {
	public static void mainTest(String[] args) {
		Club club = new Club();
		Scanner sc = new Scanner(System.in);
		
		// saisie des membres
		saisirMembres(club, sc);
		
		// Saisir événements
		saisirEvenements(club, sc);
		
		// Saisir inscriptions
		saisirInscriptions(club, sc);
		
		System.out.println("Fin de saisie - Au revoir");
		
		sc.close();
	}

	/**
	 * Saisie des membres de l'association
	 * @param club
	 * @param sc
	 */
	private static void saisirMembres(Club club, Scanner sc) {
		String nomPrenom = "";
		String email = "";
		String adresse = "";
		String numTel = "";
		String[] libelleMembre = new String[] {"Veuillez saisir le Président de l'association :", 
		           "Veuillez saisir le Secrétaire de l'association :", 
		           "Veuillez saisir le Trésorier de l'association :",
		           "Veuillez saisir un autre membre (\"Entrée\" quand terminé) :"};
		int i = 0;
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
				adresse = sc.nextLine();
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
	 * 
	 * @param club
	 * @param sc
	 */
	private static void saisirEvenements(Club club, Scanner sc) {
		
		// Qui est le président - qui seul peut créer un événement
		President president = (President) club.rechercherStatut(Statut.PRESIDENT);
		
		System.out.println("---------------------------------------------------------");
		LocalDateTime dateEvenement;
		String description = "";
		String dateHeure = "";
		
		do {
			System.out.println("Ajout d'un nouvel événement :");
			System.out.println("Description :");
			description = sc.nextLine();
			if (!description.equals("")) {
				System.out.println("Date de l'évenement au format 'aaaa-mm-jj :");
				dateHeure = sc.nextLine();
				System.out.println("Heure de l'évenement au format 'hh:mm' :");
				dateHeure += "T" + sc.nextLine() + ":00";
				dateEvenement = LocalDateTime.parse(dateHeure);
				president.ajoutEven(club.getEvenements(), dateEvenement, description) ;
			}
		} while (!description.equals(""));
	}

	
	private static void saisirInscriptions(Club club, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

}
