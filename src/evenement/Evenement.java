package evenement;

import java.time.LocalDate;

public class Evenement {
	private LocalDate date ;
	private String description;
	private InscritEven[] participants;
	private FournitureEven[] fournitures;
	private Salle lieu;
	
	public Evenement(LocalDate date, String description) {
		super();
		this.date = date;
		this.description = description;
	}
	
	public InscritEven[] getParticipants() {
		return participants;
	}
	
	public void setParticipants(InscritEven[] participants) {
		this.participants = participants;
	}
	
	public FournitureEven[] getFournitures() {
		return fournitures;
	}
	
	public void setFournitures(FournitureEven[] fournitures) {
		this.fournitures = fournitures;
	}
	
	public Salle getLieu() {
		return lieu;
	}
	
	public void setLieu(Salle lieu) {
		this.lieu = lieu;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
