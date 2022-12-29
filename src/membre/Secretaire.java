package membre;

import evenement.Evenement;
import evenement.FournitureInscrit;
import evenement.InscritEven;

public class Secretaire extends Membre {
	private Evenement evenement;
	private String[] mailsMembre;
	private String[] mailsInscrit;

	public Secretaire(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation, Evenement evenement) {
		super(id, nomPrenom, email, adresse, numTel, Statut.SECRETAIRE, anneeInscr, derAnneeParticipation);
		this.evenement = evenement;
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
	
	
	public String[] ecritMailInscrit(int nbInscrit){
		for(int i = 0; i < nbInscrit ; i++) {
			mailsInscrit[i] = "Bonjour " + evenement.getInscrits()[i].getMembre().getNomPrenom() + ", \n"
					+ "Merci d'amener les fournitures suivantes :  \n";
				for(FournitureInscrit fournitureInscrit: evenement.getInscrits()[i].getFournitures()) {
					if (fournitureInscrit.getSalle() == null) {
						mailsInscrit[i] = " - " + fournitureInscrit.getNbr() + " de " + fournitureInscrit.getProduit().getNom() + " pour un prix de " + fournitureInscrit.getPrix() + " euros \n";
					} else {
						mailsInscrit[i] = " - " + "participation aux frais de location de la salle pour " + fournitureInscrit.getPrix() + " euros \n";
					}
				}
			mailsInscrit[i] += "Cordialement \n"
									+ this.getNomPrenom();
		}
		return mailsInscrit;
	}
	
	public int ajoutInscrit(InscritEven inscrit, int nbInscrits) {
		if(nbInscrits < nbInscrits) {
			evenement.getInscrits()[nbInscrits-1] = inscrit;
			nbInscrits ++;
		}
		return nbInscrits;
	}
	
	public int suppInscrit(InscritEven inscrit, int nbInscrits){
		int i = 0;
		while(!inscrit.getMembre().equals(evenement.getInscrits()[i].getMembre())) {
			i++;
		}
		for(; i < nbInscrits - 1; i++) {
			evenement.getInscrits()[i] = evenement.getInscrits()[i + 1];
		}
		nbInscrits--;
		return nbInscrits;
	}
	
	
	public void afficherInscritCourant (int nbInscrits) {
		for(int i  = 0; nbInscrits > i; i++) {
			System.out.println("- Nom Prenom : "+ evenement.getInscrits()[i].getMembre().getNomPrenom() + ",  Fourniture apporté  : " + evenement.getInscrits()[i].getFournitures() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}
	
	
}
