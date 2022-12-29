package club;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import evenement.Evenement;
import evenement.InscritEven;
import membre.Membre;
import membre.President;
import membre.Secretaire;
import membre.Statut;
import membre.Tresorier;

public class Club {
	private String nom = "La 3eme Mi-Temps Toulousaine";
	private String email = "3emeMiTempsTls@gmail.com";
	private String numTel = "06.10.14.01.01";
	private String adresse = "1 All. Gabriel Biénès, 31000 Toulouse";
	private Membre membres[] = new Membre[0];

	
	
	public Membre[] getMembres() {
		return membres;
	}

	public String getNom() {
		return nom;
	}


	public String getEmail() {
		return email;
	}


	public String getNumTel() {
		return numTel;
	}


	public String getAdresse() {
		return adresse;
	}


	public String Publicité(String nom, String email, String numTel, String adresse) {
		return nom + email + numTel + adresse;
	}
	
	
	//---------------------------------------------------------Membre----------------------
	
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
		List<Membre> listeMembres = Arrays.asList(membres);
		int nbMembres = membres.length + 1;
		int annee = LocalDate.now().getYear();
		Membre membre;
/*		switch (statut) {
		case PRESIDENT:
			membre = (Membre) new President(nbMembres, "George Gomez", "", "", "", annee, annee);
			break;
		case SECRETAIRE:
			membre = (Membre) new Secretaire(nbMembres, "Michel Polaref", "", "", "", annee, annee);
			break;
		case TRESORIER:
			membre = (Membre) new Tresorier(nbMembres, "Jonathan Paleton", "", "", "", annee, annee);
			break;
		default:*/
			membre = new Membre(nbMembres, "Nicolas Aliagas", "", "", "", Statut.MEMBRE, annee, annee);
		//}
		listeMembres.add(membre); 
		membres = ((Membre[]) listeMembres.toArray());
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
		List<Membre> listeMembres = Arrays.asList(membres);
		int nbMembres = membres.length + 1;
		int annee = LocalDate.now().getYear();
		listeMembres.add(new Membre(nbMembres, "Nicolas Aliagas", "", "", "", Statut.MEMBRE, annee, annee));
		membres = ((Membre[]) listeMembres.toArray());
	}

	
	public Membre[] suppMembre(Membre membre){
		int i = 0;
		int nbMembres = membres.length;
		
		while(membre.getId() != membres[i].getId()) {
			i++;
		}
		for(; i < nbMembres - 1; i++) {
			membres[i] = membres[i + 1];
		}
		nbMembres--;
		return membres;
	}
	
	public Membre trouverMembre(int id) {
		return membres[id];
	}
	
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
	
	
}