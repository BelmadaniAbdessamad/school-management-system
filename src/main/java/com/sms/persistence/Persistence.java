package com.sms.persistence;

import java.util.List;
import java.util.Map;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;

public interface Persistence {
	
	List<Etudiant> getAllStudents();
	Etudiant findStudentByFullName(String fullName);
	Etudiant findStudentByCNE(int cne);
	boolean insertStudent(Etudiant et);
	boolean deleteStudent(int id);
	boolean updateStudent(int id,Etudiant newData);
	
	List<Filiere> getAllMajors();
	boolean insertMajor(Filiere fl);
	void deleteMajor(int id);
	boolean updateMajor(int id,Filiere newData);
	
	Map<String, List<Filiere>> getAllDepartementsWithMajors();
	List<Departement> getAllDepartements();
	boolean insertDepartement(Departement depart);
	void deleteDepartement(int id);
	boolean updateDepartement(int id,Departement newData);
	

}
