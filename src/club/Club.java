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

	public Membre[] ajouterHabitant(Membre membre, int nbMembres){
		if(nbMembres < nbMembreMaximum) {
			membres[nbMembres] = membre;
			nbMembres ++;
		}
		return membres;
	}
	
	public Membre[] suppriméHabitant(Membre membre, int nbMembres){
		return membres;
	}
}
