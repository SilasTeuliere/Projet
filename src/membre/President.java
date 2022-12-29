package membre;

import evenement.Evenement;

public class President extends Membre {
	private int nbEvenMaximum = 10;
	private Evenement[] evenements = new Evenement[nbEvenMaximum];
	public President(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.PRESIDENT, anneeInscr, derAnneeParticipation);
		
	}
	
	public Evenement[] ajoutEven(Evenement evenement, int nbEven) {
		if(nbEven < nbEvenMaximum) {
			evenements[nbEven-1] = evenement;
			nbEven ++;
		}
		return evenements;
	}
	
	public Evenement[] suppEven(Evenement evenement, int nbEven){
		int i = 0;
		while(!evenement.getDescription().equals(evenements[i].getDescription())) {
			i++;
		}
		for(; i < nbEven - 1; i++) {
			evenements[i] = evenements[i + 1];
		}
		nbEven--;
		return evenements;
	}
	
	
	public void afficherEvenCourant (int nbEven) {
		for(int i  = 0; nbEven > i; i++) {
			System.out.println("- Date : "+ evenements[i].getDate() + ",  Description : " + evenements[i].getDescription() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
}
