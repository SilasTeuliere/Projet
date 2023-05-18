
package control;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.membre.President;
import commun.Statut;
import entity.Club;
import entity.evenement.Evenement;
import entity.membre.Membre;

/**
 * Classe decrivant le club : son adresse et ses membres...
 */
public class ControlerClub {
	private Club club;
	public ControlerClub(Club club) {
		this.club = club;
	}
	

	/**
	 * Restitue la liste de tous les membres avec leur statut
	 * @return
	 */
	public List<String> listerMembres() {
		StringBuilder str;
		final Membre[] membres = club.getMembres();
		int nbMembres = membres.length;
		final List<String> liste = new ArrayList<String>() ;
		for(int i  = 0; nbMembres > i; i++) {
			str = new StringBuilder();
			str.append("- Id : ").append(membres[i].getId()).append(", Nom Prenom : ").append(membres[i].getNomPrenom());
			if(membres[i].getStatut().equals(Statut.PRESIDENT) 
					|| membres[i].getStatut().equals(Statut.SECRETAIRE)
					|| membres[i].getStatut().equals(Statut.TRESORIER)) {
				str.append("	- ").append(membres[i].getStatut().toString()).append(" du club");
			}
			liste.add(str.toString());
		}
		return liste;
	}
	
	
	/**
	 * Restitue la liste de tous les evenements
	 * @return
	 */
	public List<String> listerEvenement(){
		StringBuilder str;
		final Evenement[] evenements = club.getEvenements();
		final List<String> liste = new ArrayList<String>() ;
		for(int i  = 0; evenements.length > i; i++) {
			str = new StringBuilder();
			str.append("- Date : ").append(evenements[i].getDate()).append(",  Description : ").append(evenements[i].getDescription());
			liste.add(str.toString());
		}
		return liste;
	}

	
	

	/**
	 * Trie le tableau Id du membre(Quand c'est pas trié de base)
	 * @return
	 */
	public Membre[] trieTableauParId() {
		int i = 0;
		final Membre[] membres = club.getMembres();
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
		return club.ajoutMembre(nomPrenom, email, adresse, numTel, statut);
	}

	/**
	 * inscription d'un nouveau membre à la date du jour - sans transmettre le statut => membre simple
	 * @param nomPrenom
	 * @param email
	 * @param adresse
	 * @param numTel
	 */
	public void ajoutMembre(String nomPrenom, String email, String adresse, String numTel) {
		club.ajoutMembre(nomPrenom, email, adresse, numTel);
	}

	/**
	 * suppression d'un membre
	 * @param id - identifiant du membre
	 */
	public void suppMembre(int id){
		club.suppMembre(id);
	}
	
	/**
	 * trouve le membre dont l'id est mis en entrée 
	 * @param id
	 * @return
	 */
	public Membre trouverMembre(int id) {
		return club.trouverMembre(id);
	}
	
	/**
	 * Recherche de la première personne ayant un statut donné
	 * @param statut
	 * @return
	 */
	public Membre rechercherStatut(Statut statut) {
		return club.rechercherStatut(statut);
	}

	/**
	 * creer programme Ocaml liste des membres
	 * @return
	 */
	public String extraireInstructionsCamlMembre() {
		final Membre[] membres = club.getMembres();
		int nbMembres = membres.length;
		String texte = " let membres = [\n";
		for(int i = 0; i < nbMembres; i++) {
			if(i == nbMembres-1) {
				texte = texte  + membres[i].getId() +", \"" + membres[i].getNomPrenom() +"\", \"" + membres[i].getEmail() 
						+ "\", \"" + membres[i].getAdresse() +"\", \"" + membres[i].getNumTel() +"\", \"" + membres[i].getStatut()+ "\"\n";
			}
			else {
				texte = texte + membres[i].getId() +", \"" + membres[i].getNomPrenom() +"\", \"" + membres[i].getEmail() 
						+ "\", \"" + membres[i].getAdresse() +"\", \"" + membres[i].getNumTel() +"\", \"" + membres[i].getStatut()+ "\";\n";
			}
		}
		texte = texte + "]\n";
		return texte;
	}
	
	/**
	 * change le status d'un membre du club
	 * @param idMembre
	 * @param statut
	 */
	public void changerStatut(int idMembre, Statut statut) {
		club.changerStatut(idMembre, statut);
	}


	/**
	 * réinitialise la liste des membres
	 */
	public void initMembres() {
		club.initMembres();
	}
	
	public void creerEvenement(LocalDateTime dateEven, String detail) {
		// Qui est le président - qui seul peut créer un événement
		// pour compléter il faudrait faire saisir son nom prénom et son mot de passe au président
		President president = (President) club.rechercherStatut(Statut.PRESIDENT);
		club.setEvenements(president.ajoutEven(club.getEvenements(), dateEven, detail));
	}
}