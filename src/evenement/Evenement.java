package evenement;

import java.time.LocalDate;

public class Evenement {
	private LocalDate date ;
	private String description;
	private InscritEven[] inscrits;
	private FournitureEven[] fournitures = new FournitureEven[Produit.getNombreProduitDifferent()];
	private Salle lieu;
	
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
		boolean bool;
		if(Salle.PETITE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.PETITE_SALLE.getNbPersonneMax()) {
			lieu = Salle.PETITE_SALLE;
			bool = true;
		}
		else if(Salle.MOYENNE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.MOYENNE_SALLE.getNbPersonneMax()) {
			lieu = Salle.MOYENNE_SALLE;
			bool = true;
		}
		else if(Salle.GRANDE_SALLE.getNbPersonneMin() <= nbInscrit && nbInscrit <= Salle.GRANDE_SALLE.getNbPersonneMax()) {
			lieu = Salle.GRANDE_SALLE;
			bool = true;
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
		double budjetEven = lieu.getPrix();
		int i = 0;
		double produitParEven;
		double prixProduitEven;
		for (Produit produit : Produit.values()) { 
			produitParEven = nbInscrit*produit.getParPersonne();
			if(produitParEven != Math.floor(produitParEven)) {
				produitParEven = Math.floor(produitParEven) + 1;
			}
			prixProduitEven = produitParEven * produit.getPrix();
			budjetEven += prixProduitEven;
			FournitureEven fourniture = new FournitureEven(produit, (int)produitParEven,  );
		    fournitures[i].;
		}
		return true;
	}
}
