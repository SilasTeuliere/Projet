package membre;

public enum Statut {
	PRESIDENT("Président"), TRESORIER("Trésorier"), SECRETAIRE("Secrétaire"), MEMBRE("Membre");
	private String nom;
	
	private Statut(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return nom;
	}
}
