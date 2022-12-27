package membre;

public class Membre {
	private String id;
	private String nomPrenom;
	private String email;
	private String adresse;
	private String numTel;
	private String statue;				//membre, secretaire, president, tresorier
	private int anneeInscr;
	private int derAnneeParticipation;
	public Membre(String id, String nomPrenom, String email, String adresse, String numTel, String statue, int anneeInscr, int derAnneeParticipation) {
		this.id = id;
		this.nomPrenom = nomPrenom;
		this.email = email;
		this.adresse = adresse;
		this.numTel = numTel;
		this.statue = statue;
		this.anneeInscr = anneeInscr;
		this.derAnneeParticipation = derAnneeParticipation;
	}
	
	public String suppressionMembre() {
		return "Membre Supprimé.";
	}
}
