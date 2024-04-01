package com.sms.service;

import java.util.List;
import java.util.Map;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;
import com.sms.persistence.MySqlPersistence;
import com.sms.persistence.Persistence;

public class DefaultService implements Service {
	
	private Persistence persistence;
	static Service defaultService = null;
	
	public static Service getServiceInstance() {
		if( defaultService == null) {
			defaultService = new DefaultService(new MySqlPersistence());
		}
		
		return defaultService;
	}

	public DefaultService(Persistence persistence) {
		super();
		this.persistence = persistence;
	}

	@Override
	public List<Etudiant> getAllStudents() {
		// TODO Auto-generated method stub
		List<Etudiant> etudiants = persistence.getAllStudents();
		if(etudiants != null && etudiants.size() >=1) {
			return etudiants;
		}
		return null;
	}

	@Override
	public Etudiant findStudentByFullName(String fullName) {
		// TODO Auto-generated method stub
		if( !fullName.isBlank()) return persistence.findStudentByFullName(fullName);
		return null;
	}

	@Override
	public boolean insertStudent(Etudiant et) {
		// TODO Auto-generated method stub
		if(et != null && et.attributsAreValid()) {
			return persistence.insertStudent(et);
		}
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return persistence.deleteStudent(id);
	}

	@Override
	public boolean updateStudent(int id, Etudiant newData) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Filiere> getAllMajors() {
		List<Filiere> filieres = persistence.getAllMajors();
		if(filieres != null && filieres.size() >=1) {
			return filieres;
		}
		return null;
	}

	@Override
	public boolean insertMajor(Filiere fl) {
		if(fl != null && !fl.getNom().isBlank() && fl.getDepartement().getId()>0) {
			return persistence.insertMajor(fl);
		}
		return false;
	}

	@Override
	public boolean deleteMajor(int id) {
		
		return persistence.deleteMajor(id);
		
	}

	@Override
	public boolean updateMajor(int id, Filiere newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Departement> getAllDepartements() {
		List<Departement> departements = persistence.getAllDepartements();
		if(departements != null && departements.size() >=1) {
			return departements;
		}
		return null;
	}

	@Override
	public boolean insertDepartement(Departement depart) {
		if(depart != null && !depart.getNom().isBlank()) {
			return persistence.insertDepartement(depart);
		}
		return false;
	}

	@Override
	public boolean deleteDepartement(int id) {
		
		return persistence.deleteDepartement(id);
		
	}

	@Override
	public boolean updateDepartement(int id, Departement newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, List<Filiere>> getAllDepartementsWithMajors() {
		// TODO Auto-generated method stub
		return persistence.getAllDepartementsWithMajors();
	}

	@Override
	public Etudiant findStudentByCNE(int cne) {
		// TODO Auto-generated method stub
		if( cne > 0 ) return persistence.findStudentByCNE(cne);
		return null;
	}

	@Override
	public Departement findDepartByName(String name) {
		if( !name.isBlank()) return persistence.findDepartByName(name);
		return null;
	}

	@Override
	public Departement findDepartById(int id) {
		if(id>0) return persistence.findDepartById(id);
		return null;
	}

	@Override
	public Filiere findMajorByName(String name) {
		if( !name.isBlank()) return persistence.findMajorByName(name);
		return null;
	}

	@Override
	public Filiere findMajorById(int id) {
		if(id>0) return persistence.findMajorById(id);
		return null;
	}
	
	 

}
