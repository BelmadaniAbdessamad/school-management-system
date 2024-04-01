package com.sms.beans;

public class Filiere {
	
	private String nom;
	private Departement departement;
	private int id;
	private int etudiantCount;
	
	
	public Filiere() {
		super();
	}

	public Filiere(String nom, Departement departement, int id) {
		super();
		this.nom = nom;
		this.departement = departement;
		this.id = id;
	}

	public Filiere(String nom, int id) {
		super();
		this.nom = nom;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getEtudiantCount() {
		return etudiantCount;
	}

	public void setEtudiantCount(int etudiantCount) {
		this.etudiantCount = etudiantCount;
	}

	@Override
	public String toString() {
		return "Filiere [nom=" + nom + ", id=" + id + ", etudiantCount=" + etudiantCount + "]";
	}
	
	
	

}
