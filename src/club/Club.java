package club;


import membre.Membre;
import membre.President;

public class Club {
	private String nom = "La 3eme Mi-Temps Toulousaine";
	private String email = "3emeMiTempsTls@gmail.com";
	private String numTel = "";
	private String adresse = "1 All. Gabriel Biénès, 31000 Toulouse";
	private int nbMembres = 0;
	private int nbMembreMaximum = 500;
	private Membre membres[] = new Membre[nbMembreMaximum];
	
	public String Publicité(String nom, String email, String numTel, String adresse) {
		return nom + email + numTel + adresse;
	}
	
	public Membre[] trieTableauParId(int nbMembres) {
		int i = 0;
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

	public Membre[] ajoutMembre(Membre membre, int nbMembres){
		if(nbMembres < nbMembreMaximum) {
			membres[nbMembres-1] = membre;
			nbMembres ++;
		}
		return membres;
	}
	
	public Membre[] suppMembre(Membre membre, int nbMembres){
		int i = 0;
		while(membre.getId() != membres[i].getId()) {
			i++;
		}
		for(; i < nbMembres - 1; i++) {
			membres[i] = membres[i++];
		}
		nbMembres--;
		return membres;
	}
	
	public Membre trouverMembre(int id) {
		return membres[id];
	}
	
	public void afficherMembres (int nbMembres) {
		for(int i  = 0; nbMembres > i; i++) {
			System.out.println("- Id : "+ membres[i].getId() + ", Nom Prenom : " + membres[i].getNomPrenom() );
			if(membres[i].getStatut().equals("president") 
					|| membres[i].getStatut().equals("secretaire")
					|| membres[i].getStatut().equals("tresorier")) {
				System.out.println("	- " + membres[i].getStatut() + " du club");
			}
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	public static void main(String[] args) {
		Club club = new Club();
		President membre1 = new President(0, "George Gomez", "", "", "", 2018, 2022);
		Membre membre2 = new Membre(1, "Michel Polaref", "", "", "", "secretaire", 2019, 2022);
		Membre membre3 = new Membre(2, "Jonathan Paleton", "", "", "", "tresorier", 2020, 2022);
		Membre membre4 = new Membre(3, "Nicolas Aliagas", "", "", "", "membre", 2020, 2021);
		club.ajoutMembre(membre2, 1);
		club.ajoutMembre(membre4, 2);
		club.ajoutMembre(membre3, 3);
		club.ajoutMembre(membre1, 4);
		club.afficherMembres(4);
		club.trieTableauParId(4);
		club.afficherMembres(4);
		club.suppMembre(membre3, 4);
		club.afficherMembres(3);
		Membre membre = club.trouverMembre(0);
		System.out.println(membre.getNomPrenom());
	}
}