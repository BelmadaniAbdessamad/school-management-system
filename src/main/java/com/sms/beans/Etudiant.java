package com.sms.beans;

public class Etudiant {
	
	private int id;
	private String nom;
	private String prenom;
	private String filiere;
	private String tel;
	private int cne;
	private String departement;
	
	
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
	public Etudiant(int id, String nom, String prenom, String filiere, String tel, int cne) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.filiere = filiere;
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
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
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

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", filiere=" + filiere + ", tel=" + tel
				+ ", cne=" + cne + "]";
	}

	
	
	

}
