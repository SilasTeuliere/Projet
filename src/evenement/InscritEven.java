
package evenement;

import membre.Membre;

/**
 * classe d'un inscrit à un evenement
 */
public class InscritEven {
	private Membre membre;
	private double budjetPrevisionnel;
	private double budjetReel;
	private FournitureInscrit[] fournitures;
	
	public InscritEven(Membre membre, double budjetPrevisionnel) {
		super();
		this.membre = membre;
		this.budjetPrevisionnel = budjetPrevisionnel;
	}

	public double getBudjetReel() {
		return budjetReel;
	}

	public void setBudjetReel(double budjetReel) {
		this.budjetReel = budjetReel;
	}

	public FournitureInscrit[] getFournitures() {
		return fournitures;
	}

	public void setFournitures(FournitureInscrit[] fournitures) {
		this.fournitures = fournitures;
	}

	public Membre getMembre() {
		return membre;
	}

	public double getBudjetPrevisionnel() {
		return budjetPrevisionnel;
	}
	
	
}
