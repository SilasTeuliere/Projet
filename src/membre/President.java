package membre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	// polymorphisme : un même nom de fonction avec des paramètres différents
	public Evenement[] ajoutEven(Evenement[] evenements, LocalDateTime dateEven, String detail) {
		List<Evenement> listeEvenement = new ArrayList<>();
	    listeEvenement = Arrays.asList(evenements);
	    listeEvenement.add(new Evenement(dateEven, detail));
		return (Evenement[]) listeEvenement.toArray();
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
