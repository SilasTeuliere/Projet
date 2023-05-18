package boundary.club;

import control.ControlerClub;

public class BoundaryClub {
	private ControlerClub controlerClub;
	public BoundaryClub(ControlerClub controlerClub) {
		super();
		this.controlerClub = controlerClub;
	}

	/**
	 * restitue la liste de tout les membres faisant parties du club en indiquant quand ils appartiennent au bureau du Club
	 */
	public void afficherMembres () {
		for(String membre : controlerClub.listerMembres()) {
			System.out.println(membre);
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	/**
	 * affiche le tableau des evenements
	 */
	public void afficherEvenements() {
		for(String evenement : controlerClub.listerEvenement()) {
			System.out.println(evenement);
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
}
