package membre;

import evenement.Evenement;
import evenement.InscritEven;

public class Secretaire extends Membre {
	private Membre[] membres;
	private Evenement evenement;
	private int nbInscrits = 0;
	private int nbInscritMaximum = 200;
	private InscritEven inscrits[] = new InscritEven[nbInscritMaximum];
	private String[] mails;

	public Secretaire(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.SECRETAIRE, anneeInscr, derAnneeParticipation);
	}
	
	public String[] ecritMail(int nbMembre){
		for(int i = 0; i < nbMembre ; i++) {
			mails[i] = "Bonjour " + membres[i].getNomPrenom() + ", \n "
					+ "nous vous invitons à vous inscrire si vous le souhaitez à l'évènement du " + evenement.getDate() + ". \n"
							+ "Cette évènement sera " + evenement.getDescription() + ". \n"
									+ "Si vous souhaitez vous inscrire renvoyez un mail d'ici maximum une semaine que nous puissions reserverla salle. \n"
									+ "Cordialement \n"
									+ this.getNomPrenom();
			
		}
		return mails;
	}
	
	public InscritEven[] ajoutInscrit(InscritEven inscrit) {
		if(nbInscrits < nbInscritMaximum) {
			inscrits[nbInscrits-1] = inscrit;
			nbInscrits ++;
		}
		return inscrits;
	}
	
	public InscritEven[] suppInscrit(InscritEven inscrit){
		int i = 0;
		while(!inscrit.getMembre().equals(inscrits[i].getMembre())) {
			i++;
		}
		for(; i < nbInscrits - 1; i++) {
			inscrits[i] = inscrits[i + 1];
		}
		nbInscrits--;
		return inscrits;
	}
	
	
	public void afficherInscritCourant () {
		for(int i  = 0; nbInscrits > i; i++) {
			System.out.println("- Nom Prenom : "+ inscrits[i].getMembre().getNomPrenom() + ",  Fourniture apporté  : " + inscrits[i].getFourniture() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
}
