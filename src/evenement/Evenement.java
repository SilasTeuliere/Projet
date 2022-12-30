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
	
	/**
	 * effectue automatiquement le choix de la salle en fonction du nombre d'inscrit
	 * @param nbInscrit
	 * @return true si salle trouvé false si l'evenement trop ou trop peu de participant (à ameliorer)
	 */
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
	
	/**
	 * etablissement de la liste des fournitures necessaires par rapport au nombres d'inscrit
	 * prevoit pour chaque membre ce qu'il doit fournir 
	 * @return
	 */
	public boolean etablissementTabFourniture() {
		int nbInscrit = inscrits.length;
		// détermination de la salle et de son prix
		if(!choixSalle(nbInscrit)) {
			return false;
		}
		this.budgetEven = lieu.getPrix();
		constituerListeFournituresEvenement(nbInscrit);
		
		// détermination du budget à prévoir par chaque inscrits
		double budgetParPersonne = this.budgetEven / nbInscrit;
		constituerListeFournituresParInscrit(budgetParPersonne);
		
		return true;
	}

	/**
	 * constitue la liste des fournitures pour chaque inscrit
	 * @param budgetParPersonne
	 */
	private void constituerListeFournituresParInscrit(double budgetParPersonne) {
		double budgetIndividuRestant;
		// Traité dans l'ordre des différents produits triés par ordre décroissants des prix
		for (InscritEven inscrit: inscrits) {
			budgetIndividuRestant = Math.round(budgetParPersonne * 100.00) / 100.00;
			final List<FournitureInscrit> tableauFourniture = new ArrayList<FournitureInscrit>();
			budgetIndividuRestant = determinerFournitureAAcheter(budgetIndividuRestant, tableauFourniture);
			
			//Si tout le budget d'une personne n'est pas utilisé le reste sert à payer la salle
			if (budgetIndividuRestant > 0) {
				final FournitureInscrit fournitureInscrit = 
						new FournitureInscrit (lieu, null, 1, Math.round(budgetIndividuRestant * 100.00) / 100.00);
				tableauFourniture.add(fournitureInscrit);
			}
			FournitureInscrit[] fournituresTab = new FournitureInscrit[tableauFourniture.size()];
			inscrit.setFournitures(tableauFourniture.toArray(fournituresTab));
		}
	}

	/**
	 * determine pour un inscrit ce qu'il doit acheter
	 * @param budgetIndividuRestant
	 * @param tableauFourniture
	 * @return
	 */
	private double determinerFournitureAAcheter(double budgetIndividuRestant,
			final List<FournitureInscrit> tableauFourniture) {
		int nbProd;
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
				fourniture.setNbrAchete(fourniture.getNbrAchete() + nbProd);
			}
		}
		return budgetIndividuRestant;
	}

	/**
	 * constitue la liste des fournitures pour l'evenement en fonction du nombre d'inscrit
	 * @param nbInscrit
	 */
	private void constituerListeFournituresEvenement(int nbInscrit) {
		int i = 0;
		double produitParEven;
		double prixProduitEven;
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
	}
}
