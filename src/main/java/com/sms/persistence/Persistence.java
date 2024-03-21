package com.sms.persistence;

import java.util.List;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;

public interface Persistence {
	
	List<Etudiant> getAllStudents();
	Etudiant findStudentByFullName(String fullName);
	boolean insertStudent(Etudiant et);
	void deleteStudent(int id);
	boolean updateStudent(int id,Etudiant newData);
	
	List<Filiere> getAllMajors();
	boolean insertMajor(Filiere fl);
	void deleteMajor(int id);
	boolean updateMajor(int id,Filiere newData);
	
	List<Departement> getAllDepartements();
	boolean insertDepartement(Departement depart);
	void deleteDepartement(int id);
	boolean updateDepartement(int id,Departement newData);
	

}
