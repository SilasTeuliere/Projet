package membre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import evenement.Evenement;
import evenement.FournitureInscrit;
import evenement.InscritEven;

public class Secretaire extends Membre {

	private List<String> mailsMembre = new ArrayList<>();
	private List<String> mailsInscrit = new ArrayList<>();;

	public Secretaire(int id, String nomPrenom, String email, String adresse, String numTel, int anneeInscr, int derAnneeParticipation) {
		super(id, nomPrenom, email, adresse, numTel, Statut.SECRETAIRE, anneeInscr, derAnneeParticipation);
	}
	
	public List<String> ecritMailMembre(Membre[] membres, Evenement evenement){
		int nbMembre = membres.length;
		for(int i = 0; i < nbMembre ; i++) {
			final String mailMembre = "Bonjour " + membres[i].getNomPrenom() + ", \n "
					+ "nous vous invitons � vous inscrire si vous le souhaitez � l'�v�nement du " + evenement.getDate() + ". \n"
							+ "Cet �v�nement sera " + evenement.getDescription() + ". \n"
									+ "Si vous souhaitez vous inscrire renvoyez un mail d'ici maximum une semaine pour que nous puissions reserver la salle. \n"
									+ "Et nous indiquer le Budjet que vous souhaitez y mettre. \n"
									+ "Cordialement \n"
									+ this.getNomPrenom();
			System.out.println(mailMembre);
			mailsMembre.add(mailMembre);
		}
		
		return mailsMembre;
	}
	
	
	public List<String> ecritMailInscrit(Evenement evenement){
		int nbInscrit = evenement.getInscrits().length;
		for(int i = 0; i < nbInscrit ; i++) {
			String mailInscrit = "Bonjour " + evenement.getInscrits()[i].getMembre().getNomPrenom() + ", \n"
					+ "Merci d'amener les fournitures suivantes :  \n";
				for(FournitureInscrit fournitureInscrit: evenement.getInscrits()[i].getFournitures()) {
					if (fournitureInscrit.getSalle() == null) {
						mailInscrit += " - " + fournitureInscrit.getNbr() + " de " + fournitureInscrit.getProduit().getNom() + " pour un prix de " + fournitureInscrit.getPrix() + " euros \n";
					} else {
						mailInscrit += " - " + "participation aux frais de location de la salle pour " + fournitureInscrit.getPrix() + " euros \n";
					}
				}
			mailInscrit += "Cordialement \n"
									+ this.getNomPrenom();
			System.out.println(mailInscrit);
			mailsInscrit.add(mailInscrit);
		}
		return mailsInscrit;
	}
	
	public int ajoutInscrit(InscritEven inscrit, int nbInscrits, Evenement evenement) {
		if(nbInscrits < nbInscrits) {
			evenement.getInscrits()[nbInscrits-1] = inscrit;
			nbInscrits ++;
		}
		return nbInscrits;
	}
	
	
	// polymorphisme : un meme nom de fonction avec des parametres differents
	public void ajoutInscrit(Evenement evenement, Membre membre, double budjetPrevisionnel) {
		List<InscritEven> listeInscrit = new ArrayList<>();
		if (evenement.getInscrits() != null) {
			listeInscrit = new ArrayList<>(Arrays.asList(evenement.getInscrits()));
		}
	    listeInscrit.add(new InscritEven(membre, budjetPrevisionnel));
	    InscritEven[] inscrits = new InscritEven[listeInscrit.size()];
	    evenement.setInscrits(listeInscrit.toArray(inscrits));
	}

	
	public int suppInscrit(InscritEven inscrit, int nbInscrits, Evenement evenement){
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
	
	
	public void afficherInscritCourant (int nbInscrits, Evenement evenement) {
		for(int i  = 0; nbInscrits > i; i++) {
			System.out.println("- Nom Prenom : "+ evenement.getInscrits()[i].getMembre().getNomPrenom() + ",  Fourniture apport�  : " + evenement.getInscrits()[i].getFournitures() );
			System.out.println("\n");
		}
		System.out.println("-----------------");
	}

	public String suppressionMembrePossible() {
		return "Suppression membre Secrétaire imposible sans remplacement.";
	}

}
