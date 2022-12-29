package evenement;

import java.time.LocalDate;

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
		if(!choixSalle(nbInscrit)) {
			return false;
		}
		this.budgetEven = lieu.getPrix();
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
			FournitureEven fourniture = new FournitureEven(produit, (int)produitParEven, prixProduitEven );
		    fournitures[i] = fourniture;
		}
		
		double budgetParPersonne = this.budgetEven / nbInscrit;
		
		return true;
	}
}
