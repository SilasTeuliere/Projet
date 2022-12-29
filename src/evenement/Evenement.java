package evenement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evenement {
	private LocalDate date ;
	private String description;
	private FournitureEven[] fournitures = new FournitureEven[Produit.getNombreProduitDifferent()];
	private Salle lieu;
	private int nbInscritMaximum = 200;
	private InscritEven[] inscrits = new InscritEven[nbInscritMaximum];
	private double budgetEven;
	
	public Evenement(LocalDate date, String description) {
		super();
		this.date = date;
		this.description = description;
	}
	
	public InscritEven[] getInscrits() {
		return inscrits;
	}
	
	public void setInscrits(InscritEven[] inscrits) {
		this.inscrits = inscrits;
	}
	
	public FournitureEven[] getFournitures() {
		return fournitures;
	}
	
	public void setFournitures(FournitureEven[] fournitures) {
		this.fournitures = fournitures;
	}
	
	public Salle getLieu() {
		return lieu;
	}
	
	public void setLieu(Salle lieu) {
		this.lieu = lieu;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean choixSalle(int nbInscrit) {
		boolean bool = true;
		if(Salle.PETITE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.PETITE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.PETITE_SALLE;
		}
		else if(Salle.MOYENNE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.MOYENNE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.MOYENNE_SALLE;
		}
		else if(Salle.GRANDE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.GRANDE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.GRANDE_SALLE;
		}
		else {
			bool = false;
		}
		return bool;
	}
	
	public boolean etablissementTabFourniture(int nbInscrit) {
		// détermination de la salle et de son prix
		if(!choixSalle(nbInscrit)) {
			return false;
		}
		this.budgetEven = lieu.getPrix();
		int i = 0;
		double produitParEven;
		double prixProduitEven;
		// établissement de la liste des produits à acheter
		for (Produit produit : Produit.values()) { 
			produitParEven = nbInscrit*produit.getParPersonne();
			if(produitParEven != Math.floor(produitParEven)) {
				produitParEven = Math.floor(produitParEven) + 1;
			}
			prixProduitEven = produitParEven * produit.getPrix();
			this.budgetEven += prixProduitEven;
			final FournitureEven fourniture = new FournitureEven(produit, (int)produitParEven, prixProduitEven );
		    fournitures[i] = fourniture;
		}
		
		// détermination du budget à prévoir par chaque inscrits
		double budgetParPersonne = this.budgetEven / nbInscrit;
		double budgetIndividuRestant = Math.round(budgetParPersonne * 100.00) / 100.00;
		// détermination de la liste des produit à ramener par chaque inscrit
		// Traité dans l'ordre des différents produits triés par ordre décroissants des prix
		int nbProd;
		for (InscritEven inscrit: inscrits) {
			final List<FournitureInscrit> tableauFourniture = new ArrayList<FournitureInscrit>();
			for (FournitureEven fourniture: fournitures) {
				nbProd = 1;
				while (nbProd <= (fourniture.getNbrTotal() - fourniture.getNbrAchete()) &&
						fourniture.getProduit().getPrix() * nbProd <= budgetIndividuRestant) {
					// il reste au moins nbprod de ce produit à acheter 
					// et l'achat de nbprod de ce produit reste dans mon budget restant
					nbProd++;
				}
				nbProd--;
				if (nbProd > 0) {
					// J'achète nbprod de ce produit là
					final FournitureInscrit fournitureInscrit = new FournitureInscrit (null, fourniture.getProduit(), nbProd, nbProd * fourniture.getProduit().getPrix());
					tableauFourniture.add(fournitureInscrit);
					budgetIndividuRestant -= nbProd * fourniture.getProduit().getPrix();
				}
				
			}
			
			//Si tout le budget n'est pas utilisé le reste sert à payer la salle

			inscrit.setFournitures((FournitureInscrit[]) tableauFourniture.toArray());
		}
		
		return true;
	}
}
