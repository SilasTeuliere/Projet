
package boundary.main;


import boundary.club.Registre;
import boundary.menu.Menu;
import commun.Statut;
import control.ControlerClub;
import entity.membre.Secretaire;
import entity.membre.Tresorier;

/**
 * @title 3eme mi-temps
 * @author TEULIERE Silas 22003086
 * @version 1.0.0 30 Decemebre 2022
 */
public class Main {

	public static void main(String[] args) {
		
		// Pour l'instant, j'instantie le club avec les informations du constructeur - la persistance de ces informations sera faite ultérieurement
		final ControlerClub club = new ControlerClub();
		String saisie;
		do {
			saisie = Menu.choisirAction();
			if (!saisie.equals("")) {
				testerAction(club, saisie);
			}
		} while (!saisie.equals(""));
	
		System.out.println("Fin de saisie - Au revoir");
		
		Menu.fermeScanner();
	}

	/**
	 * Teste et exécute les différentes action possible - ajouter des actions au besoin comme ajouter membre, changer bureau, supprimer membre, évenement ou inscrit
	 * @param club
	 * @param saisie
	 */
	private static void testerAction(final ControlerClub club, String saisie) {
		switch (saisie.toUpperCase().charAt(0)) {
		case 'T':
			// appel de toutes les méthodes en test sans persistance
			Registre.test(club);
			break;
		case 'B':
			// saisie du bureau et de membres - ne faire qu'une fois si pas passé par test
			Menu.saisirMembres(club, 'I');
			break;
		case 'M':
			// saisie des membres
			Menu.saisirMembres(club, 'A');
			break;
		case 'E':
			// Saisir événements
			Menu.saisirEvenements(club);
			break;
		case '1':
			// Envoyer mail d'information par le secrétaire pour le premier événement
			Secretaire secretaire1 = (Secretaire) club.rechercherStatut(Statut.SECRETAIRE);
			secretaire1.ecritMailMembre(club.getMembres(), club.getEvenements()[0]);
			break;
		case 'I':
			// Saisir inscriptions
			Menu.saisirInscriptions(club);
			break;
		case '2':
			// Envoyer mail de commande par le secrétaire pour le premier événement
			Secretaire secretaire2 = (Secretaire) club.rechercherStatut(Statut.SECRETAIRE);
			secretaire2.ecritMailInscrit(club.getEvenements()[0]);
			break;
		case '3':
			// reste à faire par le trésorier pour le premier événement
			Tresorier tresorier = (Tresorier) club.rechercherStatut(Statut.TRESORIER);
			tresorier.listerAchatRestantLocation(club.getEvenements()[0]);
			break;
		case 'L':
			// liste diverses - le deuxième caractère donne la liste à obtenir 
			testerListe(club, saisie);
			break;
		case 'S':
			// suprimer un membre 
			Menu.suprimerMembre(club);
			break;
		case 'C':
			// changer le statut d'un membre 
			Menu.changerStatut(club);
			break;
		}
		
	}

	/**
	 * sélection de la liste à afficher
	 * @param club
	 * @param saisie
	 */
	private static void testerListe(final ControlerClub club, String saisie) {
		switch (saisie.toUpperCase().charAt(1)) {
		case '1':
			club.afficherMembres();
			break;
		case '2':
			club.afficherEvenements();
			break;
		}
	}


}
