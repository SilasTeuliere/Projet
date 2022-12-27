package membre;

public class Membre {
	private String id;
	private String nomPrenom;
	private String email;
	private String adresse;
	private String statue;				//membre, secretaire, president 
	private int anneeInscr;
	private int derAnneeParticipation;
	public Membre(String id, String nomPrenom, String email, String adresse, String statue, int anneeInscr,
			int derAnneeParticipation) {
		super();
		this.id = id;
		this.nomPrenom = nomPrenom;
		this.email = email;
		this.adresse = adresse;
		this.statue = statue;
		this.anneeInscr = anneeInscr;
		this.derAnneeParticipation = derAnneeParticipation;
	}
	
	public String suppressionMembre() {
		return "Membre Supprimé.";
	}
}
