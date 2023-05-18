package boundary.club;

import java.time.LocalDateTime;

import commun.Statut;
import control.ControlerClub;
import entity.membre.Membre;
import entity.membre.President;
import entity.membre.Secretaire;
import entity.membre.Tresorier;

/**				
 * Classe principale pour les tests de chaque Methode et la generation du OCAML...							
 * 										DISLAIMER
 * Toutes les données utilisées pour la creation du Club ou des membres sont des données de test.
 * Que ce soit numéro de telephone, email, adresse ou autre...
 * */

public class Registre {
	
		public static void test(ControlerClub club) {
		club.initMembres();
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
		club.setEvenements(membrePresident.ajoutEven(club.getEvenements(), LocalDateTime.parse("2023-02-20T12:15:00"), "Match des 1/8 de finale de la heineken cup"));
		club.setEvenements(membrePresident.ajoutEven(club.getEvenements(), LocalDateTime.parse("2023-02-28T20:15:00"), "Match des 1/4 de finale de la heineken cup"));
		membreSecretaire.ecritMailMembre(club.getMembres(), club.getEvenements()[1]);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[0], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[3], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[4], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[6], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[7], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[8], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[9], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[11], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[10], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[14], 20);
		membreSecretaire.ajoutInscrit(club.getEvenements()[1], club.getMembres()[15], 20);
		membreSecretaire.ecritMailInscrit(club.getEvenements()[1]);		
		club.afficherMembres();
		club.trieTableauParId();
		club.afficherMembres();
		club.suppMembre(club.getMembres()[3].getId());
		club.afficherMembres();
		Membre membre = club.trouverMembre(1);
		System.out.println(membre.getNomPrenom());
		System.out.println(club.getMembres()[0].suppressionMembrePossible());
		System.out.println(club.getMembres()[1].suppressionMembrePossible());
		System.out.println(club.getMembres()[2].suppressionMembrePossible());
		System.out.println(club.getMembres()[3].suppressionMembrePossible());
		membreTresorier.listerAchatRestantLocation(club.getEvenements()[1]);
		club.changerStatut(club.getMembres()[0].getId(), Statut.MEMBRE);
		if (club.rechercherStatut(Statut.PRESIDENT) == null) {
			System.out.println("Plus de président dans le club");
		}
		club.changerStatut(club.getMembres()[14].getId(), Statut.PRESIDENT);
		President nouveauPresident = (President) club.rechercherStatut(Statut.PRESIDENT);
		nouveauPresident.changerStatut(Statut.PRESIDENT);
		System.out.println("Président = " + nouveauPresident.getNomPrenom());
		System.out.println(club.extraireInstructionsCamlMembre());
		System.out.println(club.getEvenements()[1].instructionOcamlFourniture());
		System.out.println(club.getEvenements()[1].extraireInstructionsCamlInscrit());
		
	}
	
}
