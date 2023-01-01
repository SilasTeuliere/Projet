/**
 * Classe decrivant le club : son adresse et ses membres...
 */
package club;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.AccesDonnees;
import evenement.Evenement;
import membre.Membre;
import membre.President;
import membre.Secretaire;
import membre.Statut;
import membre.Tresorier;

public class Club {
	private static String FICHIER_MEMBRE = "membre";
	private String nomClub = "La 3eme Mi-Temps Toulousaine";
	private String emailClub = "3emeMiTempsTls@gmail.com";
	private String numTelClub = "06.10.14.01.01";
	private String adresse = "1 All. Gabriel Bicnus, 31000 Toulouse";
	private Membre membres[] = null;
	private Evenement[] evenements = null;
	
	private int NumeroIDNouveau = 0;
	
	public Club() {
		// vérifier s'il existe un fichier de membres et si oui le charger
		List<String> listeMembres = AccesDonnees.LireDonnees(FICHIER_MEMBRE);
		if (listeMembres != null) {
			transcoDeCSV(listeMembres);
		}
		
		// reste à faire : charger les événements - charger les inscrits - un fichier par événement avec la date dans le nom de fichier
	}
	
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
	
	public Evenement[] getEvenements() {
		return evenements;
	}

	public void setEvenements(Evenement[] evenements) {
		this.evenements = evenements;
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
		AccesDonnees.ecrireDonnees(transcoEnCSV(), FICHIER_MEMBRE);
		
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
		AccesDonnees.ecrireDonnees(transcoEnCSV(), FICHIER_MEMBRE);
	}

	/**
	 * Transcodage de la liste des membres en CSV
	 * @return liste des membres transcodé en CSV
	 */
	private List<String> transcoEnCSV() {
		List<String> sortie = new ArrayList<>();
		for (Membre membre: membres) {
			String nouveauMembre = Integer.toString(membre.getId())+";"
		                            + membre.getNomPrenom() + ";"
		                            + membre.getEmail() + ";"
		                            + membre.getAdresse() + ";"
		                            + membre.getNumTel() + ";"
		                            + membre.getStatut().toString() + ";"
		                            + membre.getAnneeInscr() + ";"
		                            + membre.getDerAnneeParticipation();
			sortie.add(nouveauMembre);
		}
		return sortie;
	}


	/**
	 * Transcodage d'une String CSV en Membre
	 * @return liste des membres formaté en membre
	 */
	private void transcoDeCSV(List<String> membresCSV) {
		List<Membre> listeMembres = new ArrayList<>();
		int maxId = 0;
		int id;
		String nomPrenom;
        String email;
        String adresse;
        String numTel; 
        Statut statut = null;
        int anneeInscription;
        int anneeDer;
		String[] donneeLue = null;
		for (String membreCSV: membresCSV) {
			donneeLue = membreCSV.split(";");
			id = Integer.parseInt(donneeLue[0]);
			if (id > maxId) {
				maxId = id;
			}
			nomPrenom = donneeLue[1];
	        email = donneeLue[2];
	        adresse = donneeLue[3];
	        numTel = donneeLue[4]; 
	        for (Statut statutTheo : Statut.values()) {
	        	if (statutTheo.toString().equals(donneeLue[5])) {
	        		statut = statutTheo;
	        	}
	        }
	        anneeInscription = Integer.parseInt(donneeLue[6]);
	        anneeDer = Integer.parseInt(donneeLue[7]);
	        Membre membre;
	        switch (statut) {
			case PRESIDENT:
				membre = new President(id, nomPrenom, email, adresse, numTel, anneeInscription, anneeDer);
				break;
			case SECRETAIRE:
				membre = new Secretaire(id, nomPrenom, email, adresse, numTel, anneeInscription, anneeDer);
				break;
			case TRESORIER:
				membre = new Tresorier(id, nomPrenom, email, adresse, numTel, anneeInscription, anneeDer);
				break;
			default:
				membre = new Membre(id, nomPrenom, email, adresse, numTel, statut, anneeInscription, anneeDer);
			}
			listeMembres.add(membre);
		}
		Membre[] membresNouveaux = new Membre[listeMembres.size()];
		membres = listeMembres.toArray(membresNouveaux);
		NumeroIDNouveau = maxId;
	}

	/**
	 * suppression d'un membre
	 * @param id - identifiant du membre
	 */
	public void suppMembre(int id){
		//passage par une liste pour supprimer physiquement le membre concerné
		List<Membre> listeMembres = new ArrayList<>(Arrays.asList(membres));
		listeMembres.removeIf(membreLu -> membreLu.getId() == id);
		Membre[] membresNouveaux = new Membre[listeMembres.size()];
		membres = listeMembres.toArray(membresNouveaux);
		AccesDonnees.ecrireDonnees(transcoEnCSV(), FICHIER_MEMBRE);
	}
	
	/**
	 * trouve le membre dont l'id est mis en entrée 
	 * @param id
	 * @return
	 */
	public Membre trouverMembre(int id) {
		int i = 0;
		while (i < membres.length && membres[i].getId() != id) {
			i++;
		}
		if (i >= membres.length) {
			return null; 
		}
		return membres[i];
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

	/**
	 * affiche le tableau des evenements
	 */
	public void afficherEvenements() {
		for(int i  = 0; evenements.length > i; i++) {
			System.out.println("- Date : "+ evenements[i].getDate() + ",  Description : " + evenements[i].getDescription() + "\n");
		}
		System.out.println("-----------------");
	}

	/**
	 * creer programme Ocaml liste des membres
	 * @return
	 */
	public String extraireInstructionsCamlMembre() {
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
	
	public void changerStatut(int idMembre, Statut statut) {

		int i = 0;
		while (i < membres.length && membres[i].getId() != idMembre) {
			i++;
		}
		if (i >= membres.length) {
			System.out.println("Membre inconnu");
			return;
		}
		
		if (membres[i].getStatut().equals(statut)) {
			System.out.println("Statut pré-existant");
			return;
		}
		
		Membre membre = membres[i];
		
		switch (statut) {
		case PRESIDENT:
			membres[i] = new President(membre.getId(), membre.getNomPrenom(), membre.getEmail(), membre.getAdresse(), 
					membre.getNumTel(), membre.getAnneeInscr(), membre.getDerAnneeParticipation());
			break;
		case SECRETAIRE:
			membres[i] = new Secretaire(membre.getId(), membre.getNomPrenom(), membre.getEmail(), membre.getAdresse(), 
					membre.getNumTel(), membre.getAnneeInscr(), membre.getDerAnneeParticipation());
			break;
		case TRESORIER:
			membres[i] = new Tresorier(membre.getId(), membre.getNomPrenom(), membre.getEmail(), membre.getAdresse(), 
					membre.getNumTel(), membre.getAnneeInscr(), membre.getDerAnneeParticipation());
			break;
		default:
			membres[i] = new Membre(membre.getId(), membre.getNomPrenom(), membre.getEmail(), membre.getAdresse(), 
					membre.getNumTel(), statut, membre.getAnneeInscr(), membre.getDerAnneeParticipation());
			break;
		}
		AccesDonnees.ecrireDonnees(transcoEnCSV(), FICHIER_MEMBRE);
	}


	public void initMembres() {
		membres = null;
		NumeroIDNouveau = 0;
	}
}