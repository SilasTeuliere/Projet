package membre;

public class Tresorier extends Membre{

	public Tresorier(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.TRESORIER, anneeInscr, derAnneeParticipation);
	}

}
