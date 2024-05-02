package controleur;

public class Intervention {
	private int idintervention;
	private String description;
	private float prixInter;
	private String dateInter;
	private int idproduit, idtechnicien;
	
	public Intervention(int idintervention, String description, float prixInter, String dateInter, int idproduit,
			int idtechnicien) {
		
		this.idintervention = idintervention;
		this.description = description;
		this.prixInter = prixInter;
		this.dateInter = dateInter;
		this.idproduit = idproduit;
		this.idtechnicien = idtechnicien;
	}

	public Intervention( String description, float prixInter, String dateInter, int idproduit, int idtechnicien) {
		this.idintervention = 0;
		this.description = description;
		this.prixInter = prixInter;
		this.dateInter = dateInter;
		this.idproduit = idproduit;
		this.idtechnicien = idtechnicien;
	}

	public int getIdintervention() {
		return idintervention;
	}

	public void setIdintervention(int idintervention) {
		this.idintervention = idintervention;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrixInter() {
		return prixInter;
	}

	public void setPrixInter(float prixInter) {
		this.prixInter = prixInter;
	}

	public String getDateInter() {
		return dateInter;
	}

	public void setDateInter(String dateInter) {
		this.dateInter = dateInter;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getIdtechnicien() {
		return idtechnicien;
	}

	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
	}
	
	
	
	

}
