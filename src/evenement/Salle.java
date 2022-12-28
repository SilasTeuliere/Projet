package evenement;

public enum Salle {
	PETITESALLE("","",,,),
	MOYENNESALLE(),
	GRANDESALLE();
	private String nom;
	private String adresse;
	private double prix;
	private double nbPersonneMin;
	private double nbPersonneMax;
	private Salle(String nom, String adresse, double prix, double nbPersonneMin, double nbPersonneMax) {
		this.nom = nom;
		this.adresse = adresse;
		this.prix = prix;
		this.nbPersonneMin = nbPersonneMin;
		this.nbPersonneMax = nbPersonneMax;
	}
	
}
