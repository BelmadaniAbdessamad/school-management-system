package com.sms.beans;

public class Departement {
	
	private String nom;
    private int id;
    private int filiereCount;
    private int EtudiantCount;
	
	public Departement() {
		super();
	}

	public Departement(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFiliereCount() {
		return filiereCount;
	}

	public void setFiliereCount(int filiereCount) {
		this.filiereCount = filiereCount;
	}

	public int getEtudiantCount() {
		return EtudiantCount;
	}

	public void setEtudiantCount(int etudiantCount) {
		EtudiantCount = etudiantCount;
	}

	@Override
	public String toString() {
		return "Departement [nom=" + nom + ", id=" + id + ", filiereCount=" + filiereCount + ", EtudiantCount="
				+ EtudiantCount + "]";
	}
	
	
	
	
   
}
