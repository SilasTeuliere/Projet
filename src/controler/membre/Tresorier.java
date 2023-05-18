
package controler.membre;

import entity.evenement.Evenement;
import entity.evenement.FournitureEven;
import entity.evenement.FournitureInscrit;
import entity.evenement.InscritEven;

/**
 * Classe heritant de membre concernant le tresorier du club
 */
public class Tresorier extends Membre{

	public Tresorier(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.TRESORIER, anneeInscr, derAnneeParticipation);
	}
	
	/**
	 * Methode inutile faisant de la redefinition
	 */
	public String suppressionMembrePossible() {
		return "Suppression membre Tresorier imposible sans remplacement.";
	}

	
	/**
	 * Lister les action que doit réaliser le tresorier avant l'evenement (Les achats manquants, Louer la salle et verifier le solde de l'evenement)
	 * @param evenement evenement pour lequelle il fait les verifications
	 */
	public void listerAchatRestantLocation(Evenement evenement) {
		// Recherche de ce qui reste à acheter
		System.out.println("Listing des fournitures manquantes :");
		double prixManquant;
		double totalPrixManquant = 0;
		for (FournitureEven fourniture : evenement.getFournitures()) {
			int resteAcheter = fourniture.getNbrTotal() - fourniture.getNbrAchete();
			prixManquant = Math.round(resteAcheter * fourniture.getProduit().getPrix() * 100.00) / 100.00;
			if (resteAcheter > 0) {
				System.out.println("- " +  resteAcheter + " " + fourniture.getProduit().getNom()
						+ " manquent pour " + prixManquant + " euros");
			}
			totalPrixManquant += prixManquant;
		}
		//prix de la salle
		System.out.println("- location de la salle pour " + evenement.getLieu().getPrix()
				+ " euros");
		
		// Total des liquidités pour payer la salle
		double cumulLiquide = 0;
		for (InscritEven inscrit : evenement.getInscrits()) {
			for (FournitureInscrit fourniture : inscrit.getFournitures()) {
				if (fourniture.getSalle() != null) {
					cumulLiquide += fourniture.getPrix();
					
				}
			}
		}
		
		System.out.println("Disponibilité en liquide : " + Math.round(cumulLiquide * 100.00) / 100.00);
		//solde de l'evenement
		System.out.println("Solde pour l'événement : " + Math.round((cumulLiquide - evenement.getLieu().getPrix() - totalPrixManquant) * 100.00) / 100.00);
		
	}
}
