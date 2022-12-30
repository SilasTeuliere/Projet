/**
 * Classe decrivant le club : son adresse et ses membres...
 */
package club;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import membre.Membre;
import membre.President;
import membre.Secretaire;
import membre.Statut;
import membre.Tresorier;

public class Club {
	private String nomClub = "La 3eme Mi-Temps Toulousaine";
	private String emailClub = "3emeMiTempsTls@gmail.com";
	private String numTelClub = "06.10.14.01.01";
	private String adresse = "1 All. Gabriel Bicnus, 31000 Toulouse";
	private Membre membres[] = null;
	private int NumeroIDNouveau = 0;
	
	public Membre[] getMembres() {
		return membres;
	}

	public String getNomClub() {
		return nomClub;
	}

	public String getEmailClub() {
		return emailClub;
	}

	public String getNumTelClub() {
		return numTelClub;
	}

	public String getAdresse() {
		return adresse;
	}

	public String Publicite() {
		return nomClub + emailClub + numTelClub + adresse;
	}
	
	/**
	 * Trie le tableau Id du membre(Quand c'est pas trié de base)
	 * @return
	 */
	public Membre[] trieTableauParId() {
		int i = 0;
		int nbMembres = membres.length;
		
		int nbMemb = nbMembres;
        Membre aux = membres[0];
        boolean trie = true;
        while(trie){
            trie = false;
            i = 0;
            while(i < nbMemb - 1){
                if(membres[i].getId() > membres[i + 1].getId()){
                    trie = true;
                    aux = membres[i];
                    membres[i] = membres[i + 1];
                    membres[i + 1] = aux;
                }
                i++;
            }
            nbMemb--;
        }
        return membres;
	}

	// polymorphisme : un même nom de fonction avec des paramètres différents
	/**
	 * inscription d'un nouveau membre à la date du jour
	 * @param nomPrenom
	 * @param email
	 * @param adresse
	 * @param numTel
	 * @param statut
	 */
	public Membre ajoutMembre(String nomPrenom, String email, String adresse, String numTel, Statut statut) {
		NumeroIDNouveau++;
		int annee = LocalDate.now().getYear();
		Membre membre;
		
		switch (statut) {
		case PRESIDENT:
			membre = new President(NumeroIDNouveau, nomPrenom, email, adresse, numTel, annee, annee);
			break;
		case SECRETAIRE:
			membre = new Secretaire(NumeroIDNouveau, nomPrenom, email, adresse, numTel, annee, annee);
			break;
		case TRESORIER:
			membre = new Tresorier(NumeroIDNouveau, nomPrenom, email, adresse, numTel, annee, annee);
			break;
		default:
			membre = new Membre(NumeroIDNouveau, nomPrenom, email, adresse, numTel, statut, annee, annee);
		}
		List<Membre> listeMembres = new ArrayList<>();
		if (membres != null) {
			listeMembres = new ArrayList<>(Arrays.asList(membres));
		}
		listeMembres.add(membre);
		Membre[] membresNouveaux = new Membre[listeMembres.size()];
		membres = listeMembres.toArray(membresNouveaux);
		
		return membre;
	}

	/**
	 * inscription d'un nouveau membre à la date du jour - sans transmettre le statut => membre simple
	 * @param nomPrenom
	 * @param email
	 * @param adresse
	 * @param numTel
	 */
	public void ajoutMembre(String nomPrenom, String email, String adresse, String numTel) {
		List<Membre> listeMembres = new ArrayList<>();
		if (membres != null) {
			listeMembres = new ArrayList<>(Arrays.asList(membres));
		}
		NumeroIDNouveau++;
		int annee = LocalDate.now().getYear();
		listeMembres.add(new Membre(NumeroIDNouveau, nomPrenom, email, adresse, numTel, Statut.MEMBRE, annee, annee));
		Membre[] membresNouveaux = new Membre[listeMembres.size()];
		membres = listeMembres.toArray(membresNouveaux);
	}

	/**
	 * suppression d'un membre
	 * @param membre
	 */
	public void suppMembre(Membre membre){
		//passage par une liste pour supprimer physiquement le membre concerné
		List<Membre> listeMembres = new ArrayList<>(Arrays.asList(membres));
		listeMembres.removeIf(membreLu -> membreLu.getId() == membre.getId());
		Membre[] membresNouveaux = new Membre[listeMembres.size()];
		membres = listeMembres.toArray(membresNouveaux);
	}
	
	/**
	 * trouve le membre dont l'id est mis en entrée 
	 * @param id
	 * @return
	 */
	public Membre trouverMembre(int id) {
		return membres[id];
	}
	
	/**
	 * restitue la liste de tout les membres faisant parties du club en indiquant quand ils appartiennent au bureau du Club
	 */
	public void afficherMembres () {
		int nbMembres = membres.length;
		for(int i  = 0; nbMembres > i; i++) {
			System.out.println("- Id : "+ membres[i].getId() + ", Nom Prenom : " + membres[i].getNomPrenom() );
			if(membres[i].getStatut().equals(Statut.PRESIDENT) 
					|| membres[i].getStatut().equals(Statut.SECRETAIRE)
					|| membres[i].getStatut().equals(Statut.TRESORIER)) {
				System.out.println("	- " + membres[i].getStatut().toString() + " du club");
			}
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	/**
	 * Recherche de la première personne ayant un statut donné
	 * @param statut
	 * @return
	 */
	public Membre rechercherStatut(Statut statut) {
		for (Membre membre: membres) {
			if (membre.getStatut().equals(statut)) {
				return membre;
			}
		}
		return null;
	}
}