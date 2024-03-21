package com.sms.beans;

public class Filiere {
	
	private String nom;
	private Departement departement;
	
	
	
	public Filiere() {
		super();
	}
	public Filiere(String nom, Departement departement) {
		super();
		this.nom = nom;
		this.departement = departement;
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
	
	

}
