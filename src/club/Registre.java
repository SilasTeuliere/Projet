package club;

import java.time.LocalDateTime;

import evenement.Evenement;
import membre.Membre;
import membre.President;
import membre.Secretaire;
import membre.Statut;
import membre.Tresorier;

/*											DISLAIMER
 * Toute les donn�es utiliser pour la creation du Club ou des membres sont des donn�es de test.
 * QUe ce soit numero de Telephone,Email,Adresse ou autre.
 * */

public class Registre {
	public static void main(String[] args) {
		Club club = new Club();
		Evenement[] evenements = new Evenement[0];
			
    	President membrePresident = (President) club.ajoutMembre("George Gomez", "", "", "", Statut.PRESIDENT);
		Secretaire membreSecretaire  = (Secretaire) club.ajoutMembre("Michel Polaref", "", "", "", Statut.SECRETAIRE);
		Tresorier membreTresorier = (Tresorier) club.ajoutMembre("Jonathan Paleton", "", "", "", Statut.TRESORIER);
		club.ajoutMembre("Nicolas Aliagas", "", "", "");
		club.ajoutMembre("Nicola Aliagas", "", "", "");
		club.ajoutMembre("Nicol Aliagas", "", "", "");
		club.ajoutMembre("Nico Aliagas", "", "", "");
		club.ajoutMembre("Nic Aliagas", "", "", "");
		club.ajoutMembre("Ni Aliagas", "", "", "");
		club.ajoutMembre("N Aliagas", "", "", "");
		club.ajoutMembre("Nicolas Aliaga", "", "", "");
		club.ajoutMembre("Nicolas Aliag", "", "", "");
		club.ajoutMembre("Nicolas Alia", "", "", "");
		club.ajoutMembre("Nicolas Ali", "", "", "");
		club.ajoutMembre("Nicolas Al", "", "", "");
		club.ajoutMembre("Nicolas A", "", "", "");
		club.ajoutMembre("Nicolas Bliagas", "", "", "");
		club.ajoutMembre("Nicolas Cliagas", "", "", "");
		club.ajoutMembre("Nicolas Dliagas", "", "", "");
		club.ajoutMembre("Nicolas Eliagas", "", "", "");
		club.ajoutMembre("Nicolas Fliagas", "", "", "");
		evenements = membrePresident.ajoutEven(evenements, LocalDateTime.parse("2023-02-20T12:15:00"), "Match des 1/8 de finale de la heineken cup");
		evenements = membrePresident.ajoutEven(evenements, LocalDateTime.parse("2023-02-28T20:15:00"), "Match des 1/4 de finale de la heineken cup");
		membreSecretaire.ecritMailMembre(club.getMembres(), evenements[1]);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[0], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[3], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[4], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[6], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[7], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[8], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[9], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[11], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[10], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[14], 20);
		membreSecretaire.ajoutInscrit(evenements[1], club.getMembres()[15], 20);
		evenements[1].etablissementTabFourniture();
		membreSecretaire.ecritMailInscrit(evenements[1]);		
		club.afficherMembres();
		club.trieTableauParId();
		club.afficherMembres();
		club.suppMembre(club.getMembres()[3]);
		club.afficherMembres();
		Membre membre = club.trouverMembre(0);
		System.out.println(membre.getNomPrenom());
		System.out.println(club.getMembres()[0].suppressionMembrePossible());
		System.out.println(club.getMembres()[1].suppressionMembrePossible());
		System.out.println(club.getMembres()[2].suppressionMembrePossible());
		System.out.println(club.getMembres()[3].suppressionMembrePossible());
		membreTresorier.listerAchatRestantLocation(evenements[1]);
		club.getMembres()[0] = club.getMembres()[0].changerStatut(Statut.MEMBRE);
		if (club.rechercherStatut(Statut.PRESIDENT) == null) {
			System.out.println("Plus de président dans le club");
		}
		club.getMembres()[14] = club.getMembres()[14].changerStatut(Statut.PRESIDENT);
		President nouveauPresident = (President) club.rechercherStatut(Statut.PRESIDENT);
		nouveauPresident.changerStatut(Statut.PRESIDENT);
		System.out.println("Président = " + nouveauPresident.getNomPrenom());
		
	}
}
