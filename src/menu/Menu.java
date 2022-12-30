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
		
		// Saisir �v�nements
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
		String[] libelleMembre = new String[] {"Veuillez saisir le Pr�sident de l'association :", 
		           "Veuillez saisir le Secr�taire de l'association :", 
		           "Veuillez saisir le Tr�sorier de l'association :",
		           "Veuillez saisir un autre membre (\"Entr�e\" quand termin�) :"};
		int i = 0;
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
				adresse = sc.nextLine();
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
	 * 
	 * @param club
	 * @param sc
	 */
	private static void saisirEvenements(Club club, Scanner sc) {
		
		// Qui est le pr�sident - qui seul peut cr�er un �v�nement
		President president = (President) club.rechercherStatut(Statut.PRESIDENT);
		
		System.out.println("---------------------------------------------------------");
		LocalDateTime dateEvenement;
		String description = "";
		String dateHeure = "";
		
		do {
			System.out.println("Ajout d'un nouvel �v�nement :");
			System.out.println("Description :");
			description = sc.nextLine();
			if (!description.equals("")) {
				System.out.println("Date de l'�venement au format 'aaaa-mm-jj :");
				dateHeure = sc.nextLine();
				System.out.println("Heure de l'�venement au format 'hh:mm' :");
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
