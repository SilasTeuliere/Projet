package club;

import membre.Membre;
import membre.President;

public class Club {
	private String nom = "La 3eme Mi-Temps Toulousaine";
	private String email = "3emeMiTempsTls@gmail.com";
	private String numTel = "";
	private String adresse = "1 All. Gabriel Biénès, 31000 Toulouse";
	private President pres;
	private int nbMembres = 0;
	private int nbMembreMaximum = 500;
	private Membre membres[] = new Membre[nbMembreMaximum];

	public void setPres(President pres) {
		this.pres = pres;
	}

	public Membre[] ajoutMembre(Membre membre, int nbMembres){
		if(nbMembres < nbMembreMaximum) {
			membres[nbMembres] = membre;
			nbMembres ++;
		}
		return membres;
	}
	
	public Membre[] suppMembre(Membre membre, int nbMembres){
		return membres;
	}
	
	public void afficherMembres() {
		for(int i  = 0; nbMembres > i; i++) {
			System.out.println("- Id : "+ membres[i].getId() + ", Nom Prenom : " + membres[i].getNomPrenom() );
			if(membres[i].getStatue().equals("president") || membres[i].getStatue().equals("secretaire") ) {
				System.out.println(" : " + membres[i].getStatue() + " du club");
			}
			System.out.println("\n");
		}
	}
}
