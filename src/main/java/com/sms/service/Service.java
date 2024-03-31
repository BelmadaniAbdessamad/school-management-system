package com.sms.service;

import java.util.List;
import java.util.Map;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;

public interface Service {
	
	List<Etudiant> getAllStudents();
	Etudiant findStudentByFullName(String fullName);
	boolean insertStudent(Etudiant et);
	boolean deleteStudent(int id);
	boolean updateStudent(int id,Etudiant newData);
	
	List<Filiere> getAllMajors();
	boolean insertMajor(Filiere fl);
	void deleteMajor(int id);
	boolean updateMajor(int id,Filiere newData);
	
	List<Departement> getAllDepartements();
	Map<String, List<Filiere>> getAllDepartementsWithMajors();
	boolean insertDepartement(Departement depart);
	void deleteDepartement(int id);
	boolean updateDepartement(int id,Departement newData);

}
