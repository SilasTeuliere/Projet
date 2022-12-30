package membre;

import evenement.Evenement;
import evenement.FournitureEven;
import evenement.FournitureInscrit;
import evenement.InscritEven;

public class Tresorier extends Membre{

	public Tresorier(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.TRESORIER, anneeInscr, derAnneeParticipation);
	}

	public String suppressionMembrePossible() {
		return "Suppression membre Tresorier imposible sans remplacement.";
	}

	public void listerAchatRestantLocation(Evenement evenement) {
		// Recherche de ce qui reste à acheter et paiement de la salle - solde
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
		System.out.println("Solde pour l'événement : " + Math.round((cumulLiquide - evenement.getLieu().getPrix() - totalPrixManquant) * 100.00) / 100.00);
		
	}
}
