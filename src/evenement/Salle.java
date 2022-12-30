/**
 * enumeration de l'ensemble des salles qu'il est possible de louer
 */
package evenement;

public enum Salle {
	PETITE_SALLE("Tircrabe", "2 rue de la coquillette 31200 TOULOUSE", 50.00, 10, 20),
	MOYENNE_SALLE("Saint Exupery", "7 rue du Jambonnot 31200 TOULOUSE", 75.00, 21, 50),
	GRANDE_SALLE("Complèxe sportif Jean Bourdette", "87 Avenue Felix Armand 31200 TOULOUSE", 100.00, 51, 200);
	
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
	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public double getPrix() {
		return prix;
	}
	public double getNbPersonneMin() {
		return nbPersonneMin;
	}
	public double getNbPersonneMax() {
		return nbPersonneMax;
	}
	
	
	
}
