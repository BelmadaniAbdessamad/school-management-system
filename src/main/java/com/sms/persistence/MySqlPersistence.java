package com.sms.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;

public class MySqlPersistence implements Persistence {

	private Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/annuaire_ens";
		String utilisateur = "root";
		String pswd = "";
		return DriverManager.getConnection(url, utilisateur, pswd);
	}

	@Override
	public List<Etudiant> getAllStudents() {
		// TODO Auto-generated method stub
		List<Etudiant> etudiants = new ArrayList<Etudiant>();

		try {
			Connection conn = this.getConnection();

			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet resultat = stmt.executeQuery("SELECT etudiants.id,cne,nom,prenom,tel,filieres.filiere " + ""
						+ "  FROM etudiants,filieres" + " WHERE etudiants.filiere=filieres.id ;");

				while (resultat.next()) {
					Etudiant et = new Etudiant();
					et.setId(resultat.getInt("id"));
					et.setNom(resultat.getString("nom"));
					et.setPrenom(resultat.getString("prenom"));
					et.setCne(resultat.getInt("cne"));
					et.setTel(resultat.getString("tel"));
					et.setFiliere(resultat.getString("filiere"));

					etudiants.add(et);
				}
				stmt.close();
				resultat.close();
			}

			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return etudiants;
	}

	@Override
	public Etudiant findStudentByFullName(String fullName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertStudent(Etudiant et) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateStudent(int id, Etudiant newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Filiere> getAllMajors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertMajor(Filiere fl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteMajor(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateMajor(int id, Filiere newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Departement> getAllDepartements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertDepartement(Departement depart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteDepartement(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateDepartement(int id, Departement newData) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		MySqlPersistence test = new MySqlPersistence();
		List<Etudiant> ets = test.getAllStudents();

		for (Etudiant et : ets) {
			System.out.println(et.toString());
		}

	}

}
