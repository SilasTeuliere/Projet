
package controler.membre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.evenement.Evenement;

public class ControlerPresident  {
	
	public 
	
	/**
	 * ajoute un evenement (presente du danger si elle est utilisée car necessite une taille de tableau suffisante)
	 * @param evenements
	 * @param evenement
	 * @param nbEven
	 * @return
	 */
	public Evenement[] ajoutEven(Evenement[] evenements, Evenement evenement, int nbEven) {
		if(nbEven < evenements.length) {
			evenements[nbEven-1] = evenement;
		}
		return evenements;
	}
	
	// polymorphisme : un meme nom de fonction avec des paramêtres diffêrents
	/**
	 * ajoute un evenement au tableau des evenements en passant par une liste pour pas perdre de place
	 * @param evenements
	 * @param dateEven
	 * @param detail
	 * @return
	 */
	public Evenement[] ajoutEven(Evenement[] evenements, LocalDateTime dateEven, String detail) {
		List<Evenement> listeEvenement = new ArrayList<>();
		if (evenements != null) {
			listeEvenement = new ArrayList<>(Arrays.asList(evenements));
		}
	    listeEvenement.add(new Evenement(dateEven, detail));
	    Evenement[] evenementsSortie = new Evenement[listeEvenement.size()];
		return listeEvenement.toArray(evenementsSortie);
	}

	/**
	 * Ne doit plus marcher depuis l'optimisation de place avec l'usage de liste
	 * (eventuellement a refaire avec l'usage de liste)
	 * @param evenements
	 * @param evenement
	 * @param nbEven
	 * @return
	 */
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
		
	/**
	 * Methode inutile faisant de la redefinition
	 */
	public String suppressionMembrePossible() {
		return "Suppression membre Président imposible sans remplacement.";
	}
	

	/** 
	 * Change le statut de l'ancien president
	 * Redefinition de la methode de membre avec des avertissements
	 * @param statut nouveau statut de l'ancien president du club
	 */
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
