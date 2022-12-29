package membre;

import club.Club;
import evenement.Evenement;
import evenement.InscritEven;

public class Secretaire extends Membre {
	private Evenement evenement;
	private String[] mailsMembre;
	private String[] mailsInscrit;

	public Secretaire(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.SECRETAIRE, anneeInscr, derAnneeParticipation);
	}
	
	public String[] ecritMailMembre(int nbMembre, Membre[] membres){
		for(int i = 0; i < nbMembre ; i++) {
			mailsMembre[i] = "Bonjour " + membres[i].getNomPrenom() + ", \n "
					+ "nous vous invitons à vous inscrire si vous le souhaitez à l'évènement du " + evenement.getDate() + ". \n"
							+ "Cette évènement sera " + evenement.getDescription() + ". \n"
									+ "Si vous souhaitez vous inscrire renvoyez un mail d'ici maximum une semaine pour que nous puissions reserver la salle. \n"
									+ "Et nous indiquer le Budjet que vous souhaitez y mettre."
									+ "Cordialement \n"
									+ this.getNomPrenom();
			
		}
		return mailsMembre;
	}
	
	
	public String[] ecritMailInscrit(int nbInscrit, InscritEven inscrits[]){
		for(int i = 0; i < nbInscrit ; i++) {
			mailsInscrit[i] = "Bonjour " + inscrits[i].getMembre().getNomPrenom() + ", \n"
					+ "Merci d'amener les fournitures suivantes :  ";
				for(int j = 0; j < nbInscrit; j++) {
					
				}
			mailsInscrit[i] += "Cordialement \n"
									+ this.getNomPrenom();
		}
		return mailsInscrit;
	}
	
	public InscritEven[] ajoutInscrit(InscritEven inscrit, int nbInscrits) {
		if(nbInscrits < nbInscritMaximum) {
			inscrits[nbInscrits-1] = inscrit;
			nbInscrits ++;
		}
		return inscrits;
	}
	
	public InscritEven[] suppInscrit(InscritEven inscrit, int nbInscrits){
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
	
	
	public void afficherInscritCourant (int nbInscrits) {
		for(int i  = 0; nbInscrits > i; i++) {
			System.out.println("- Nom Prenom : "+ inscrits[i].getMembre().getNomPrenom() + ",  Fourniture apporté  : " + inscrits[i].getFournitures() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	
}
