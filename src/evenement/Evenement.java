package evenement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evenement {
	private LocalDateTime date ;
	private String description;
	private FournitureEven[] fournitures = new FournitureEven[Produit.getNombreProduitDifferent()];
	private Salle lieu;
	private InscritEven[] inscrits = null;
	private double budgetEven;
	
	public Evenement(LocalDateTime date, String description) {
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
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean choixSalle(int nbInscrit) {
		boolean bool = true;
		if (Salle.PETITE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.PETITE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.PETITE_SALLE;
		}
		else if (Salle.MOYENNE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.MOYENNE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.MOYENNE_SALLE;
		}
		else if (Salle.GRANDE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.GRANDE_SALLE.getNbPersonneMax()) {
			this.lieu = Salle.GRANDE_SALLE;
		}
		else {
			bool = false;
		}
		return bool;
	}
	
	public boolean etablissementTabFourniture() {
		// d�termination de la salle et de son prix
		int nbInscrit = inscrits.length;
		if(!choixSalle(nbInscrit)) {
			return false;
		}
		this.budgetEven = lieu.getPrix();
		int i = 0;
		double produitParEven;
		double prixProduitEven;
		// �tablissement de la liste des produits � acheter
		for (Produit produit : Produit.values()) { 
			produitParEven = nbInscrit*produit.getParPersonne();
			if(produitParEven != Math.floor(produitParEven)) {
				produitParEven = Math.floor(produitParEven) + 1;
			}
			prixProduitEven = produitParEven * produit.getPrix();
			this.budgetEven += prixProduitEven;
			final FournitureEven fourniture = new FournitureEven(produit, (int)produitParEven, prixProduitEven );
		    fournitures[i] = fourniture;
		    i++;
		}
		
		// d�termination du budget � pr�voir par chaque inscrits
		double budgetParPersonne = this.budgetEven / nbInscrit;
		double budgetIndividuRestant;
		// d�termination de la liste des produit � ramener par chaque inscrit
		// Trait� dans l'ordre des diff�rents produits tri�s par ordre d�croissants des prix
		int nbProd;
		for (InscritEven inscrit: inscrits) {
			budgetIndividuRestant = Math.round(budgetParPersonne * 100.00) / 100.00;
			final List<FournitureInscrit> tableauFourniture = new ArrayList<FournitureInscrit>();
			for (FournitureEven fourniture: fournitures) {
				nbProd = 1;
				while (nbProd <= (fourniture.getNbrTotal() - fourniture.getNbrAchete()) &&
						fourniture.getProduit().getPrix() * nbProd <= budgetIndividuRestant) {
					// il reste au moins nbprod de ce produit � acheter 
					// et l'achat de nbprod de ce produit reste dans mon budget restant
					nbProd++;
				}
				nbProd--;
				if (nbProd > 0) {
					// J'ach�te nbprod de ce produit l�
					final FournitureInscrit fournitureInscrit = new FournitureInscrit (null, fourniture.getProduit(), nbProd, nbProd * fourniture.getProduit().getPrix());
					tableauFourniture.add(fournitureInscrit);
					budgetIndividuRestant -= nbProd * fourniture.getProduit().getPrix();
					fourniture.setNbrAchete(fourniture.getNbrAchete() + nbProd);
				}
			}
			
			//Si tout le budget d'une personne n'est pas utilis� le reste sert � payer la salle
			if (budgetIndividuRestant > 0) {
				final FournitureInscrit fournitureInscrit = 
						new FournitureInscrit (lieu, null, 1, Math.round(budgetIndividuRestant * 100.00) / 100.00);
				tableauFourniture.add(fournitureInscrit);
			}
			FournitureInscrit[] fournituresTab = new FournitureInscrit[tableauFourniture.size()];
			inscrit.setFournitures(tableauFourniture.toArray(fournituresTab));
		}
		
		return true;
	}
}
