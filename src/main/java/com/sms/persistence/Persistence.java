package com.sms.persistence;

import java.util.List;
import java.util.Map;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;

public interface Persistence {
	
	List<Etudiant> getAllStudents(String [] filters);
	Etudiant findStudentByFullName(String fullName);
	Etudiant findStudentByCNE(int cne);
	boolean insertStudent(Etudiant et);
	boolean deleteStudent(int id);
	boolean updateStudent(int id,Etudiant newData);
	
	List<Filiere> getAllMajors(String [] filters);
	boolean insertMajor(Filiere fl);
	boolean deleteMajor(int id);
	boolean updateMajor(int id,Filiere newData);
	Filiere findMajorByName(String name);
	Filiere findMajorById(int id);
	
	Map<String, List<Filiere>> getAllDepartementsWithMajors();
	List<Departement> getAllDepartements(String [] filters);
	boolean insertDepartement(Departement depart);
	boolean deleteDepartement(int id);
	boolean updateDepartement(int id,Departement newData);
	Departement findDepartByName(String name);
	Departement findDepartById(int id);
	

}
