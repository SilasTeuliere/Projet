/**
 * Description de chaque Membre du club (adresse, Nom prenom, email...)
 */
package membre;

public class Membre {
	private int id;
	private String nomPrenom;
	private String email;
	private String adresse;
	private String numTel;
	private Statut statut;				//MEMBRE, SECRETAIRE, PRESIDENT, TRESORIER
	private int anneeInscr;
	private int derAnneeParticipation;
	public Membre(int id, String nomPrenom, String email, String adresse, String numTel, Statut statut, int anneeInscr, int derAnneeParticipation) {
		this.id = id;
		this.nomPrenom = nomPrenom;
		this.email = email;
		this.adresse = adresse;
		this.numTel = numTel;
		this.statut = statut;
		this.anneeInscr = anneeInscr;
		this.derAnneeParticipation = derAnneeParticipation;
	}
	
	public int getId() {
		return id;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public Statut getStatut() {
		return statut;
	}

	public String getEmail() {
		return email;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getNumTel() {
		return numTel;
	}

	public int getAnneeInscr() {
		return anneeInscr;
	}

	public int getDerAnneeParticipation() {
		return derAnneeParticipation;
	}

	/**
	 * Methode inutile faisant de la redefinition
	 */
	public String suppressionMembrePossible() {
		return "Suppression membre possible.";
	}
	
	/**
	 * Changer le statut d'un membre
	 * @param statut
	 */
	public Membre changerStatut(Statut statut) {
		switch (statut) {
		case PRESIDENT:
			return new President(id, nomPrenom, email, adresse, numTel, anneeInscr, derAnneeParticipation);
		case SECRETAIRE:
			return new Secretaire(id, nomPrenom, email, adresse, numTel, anneeInscr, derAnneeParticipation);
		case TRESORIER:
			return new Tresorier(id, nomPrenom, email, adresse, numTel, anneeInscr, derAnneeParticipation);
		default:
			return new Membre(id, nomPrenom, email, adresse, numTel, statut, anneeInscr, derAnneeParticipation);
		}
	}
}
