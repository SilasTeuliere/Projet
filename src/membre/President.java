package membre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import evenement.Evenement;

public class President extends Membre {
	public President(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.PRESIDENT, anneeInscr, derAnneeParticipation);
		
	}
	
	public Evenement[] ajoutEven(Evenement[] evenements, Evenement evenement, int nbEven) {
		if(nbEven < evenements.length) {
			evenements[nbEven-1] = evenement;
		}
		return evenements;
	}
	
	// polymorphisme : un meme nom de fonction avec des param�tres diff�rents
	public Evenement[] ajoutEven(Evenement[] evenements, LocalDateTime dateEven, String detail) {
		List<Evenement> listeEvenement = new ArrayList<>(Arrays.asList(evenements));
	    listeEvenement.add(new Evenement(dateEven, detail));
	    Evenement[] evenementsSortie = new Evenement[listeEvenement.size()];
		return listeEvenement.toArray(evenementsSortie);
	}

	public Evenement[] suppEven(Evenement[] evenements, Evenement evenement, int nbEven){
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
	
	
	public void afficherEvenCourant (Evenement[] evenements, int nbEven) {
		for(int i  = 0; nbEven > i; i++) {
			System.out.println("- Date : "+ evenements[i].getDate() + ",  Description : " + evenements[i].getDescription() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	public String suppressionMembrePossible() {
		return "Suppression membre Président imposible sans remplacement.";
	}
	
	public Membre changerStatut(Statut statut) {
		if (statut.equals(Statut.PRESIDENT)) {
			System.out.println("Aucun changement action inutile");
			return this;
		} else {
		    System.out.println("Attention à avoir un président d'association");
		    return super.changerStatut(statut);
		}
	}
}
