package com.sms.beans;

public class Etudiant {
	
	private int id;
	private String nom;
	private String prenom;
	private Filiere filiere;
	private String tel;
	private int cne;
	
	
	public Etudiant() {
		
	}
	
	public Etudiant(int id, String nom, String prenom, String tel, int cne) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.cne = cne;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getCne() {
		return cne;
	}
	public void setCne(int cne) {
		this.cne = cne;
	}

	
	
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public boolean attributsAreValid() {
		// TODO Auto-generated method stub
		if( this.cne < 1 || this.nom.isBlank() || this.prenom.isBlank() || this.filiere.getId()<1 || this.tel.isBlank())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", filiere=" + filiere.getNom() + ", tel=" + tel
				+ ", cne=" + cne + "]";
	}

	
	
	
	

}
