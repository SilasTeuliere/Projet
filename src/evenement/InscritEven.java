package evenement;

import membre.Membre;

public class InscritEven {
	private Membre membre;
	private double budjetPrevisionnel;
	private double budjetReel;
	private FournitureInscrit[] fourniture;
	
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

	public FournitureInscrit[] getFourniture() {
		return fourniture;
	}

	public void setFourniture(FournitureInscrit[] fourniture) {
		this.fourniture = fourniture;
	}

	public Membre getMembre() {
		return membre;
	}

	public double getBudjetPrevisionnel() {
		return budjetPrevisionnel;
	}
	
	
}
