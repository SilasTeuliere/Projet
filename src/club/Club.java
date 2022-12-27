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
	
	public Membre[] trieTableauParId(int nbMembres) {
        int i = 0;
        Membre aux = membres[0];
        boolean trie = true;
        while(trie){
            trie = false;
            i = 0;
            while(i < nbMembres - 1){
                if(membres[i].getId() > membres[i + 1].getId()){
                    trie = true;
                    aux = membres[i];
                    membres[i] = membres[i + 1];
                    membres[i + 1] = aux;
                }
                i++;
            }
            nbMembres--;
        }
        return membres;
	}

	public Membre[] ajoutMembre(Membre membre, int nbMembres){
		if(nbMembres < nbMembreMaximum) {
			membres[nbMembres-1] = membre;
			nbMembres ++;
		}
		return membres;
	}
	
	public Membre[] suppMembre(Membre membre, int nbMembres){
		for(int i = membre.getId(); i != nbMembres-2 ;i++) {
			membres[i] = membres[i++];
		}
		nbMembres --;
		return membres;
	}
	
	public Membre trouverMembre(int Id) {
		return membres[Id];
	}
	
	public void afficherMembres() {
		for(int i  = 0; nbMembres > i; i++) {
			System.out.println("- Id : "+ membres[i].getId() + ", Nom Prenom : " + membres[i].getNomPrenom() );
			if(membres[i].getStatue().equals("president") 
					|| membres[i].getStatue().equals("secretaire")
					|| membres[i].getStatue().equals("tresorier")) {
				System.out.println(" : " + membres[i].getStatue() + " du club");
			}
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		Club club = new Club();
		Membre membre1 = new Membre(0, "George Gomez", "", "", "", "president", 2018, 2022);
		Membre membre2 = new Membre(1, "Michel Polaref", "", "", "", "secretaire", 2019, 2022);
		Membre membre3 = new Membre(2, "Jonathan Paleton", "", "", "", "tresorier", 2020, 2022);
		Membre membre4 = new Membre(3, "Nicolas Aliagas", "", "", "", "membre", 2020, 2021);
		club.ajoutMembre(membre2, 1);
		club.ajoutMembre(membre4, 2);
		club.ajoutMembre(membre3, 3);
		club.ajoutMembre(membre1, 4);
		club.afficherMembres();
		club.trieTableauParId(4);
		club.afficherMembres();
		Membre membre = club.trouverMembre(2);
		System.out.println(membre);
	}
}
