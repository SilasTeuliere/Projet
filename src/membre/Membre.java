package membre;

public class Membre {
	private int id;
	private String nomPrenom;
	private String email;
	private String adresse;
	private String numTel;
	private String statut;				//membre, secretaire, president, tresorier
	private int anneeInscr;
	private int derAnneeParticipation;
	public Membre(int id, String nomPrenom, String email, String adresse, String numTel, String statut, int anneeInscr, int derAnneeParticipation) {
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

	public String getStatut() {
		return statut;
	}

	public String suppressionMembre() {
		return "Membre Supprimé.";
	}
}
