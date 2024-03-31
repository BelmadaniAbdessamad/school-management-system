package com.sms.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
				ResultSet resultat = stmt.executeQuery(
						"SELECT etudiants.id,cne,nom,prenom,tel,filieres.filiere,departements.departement " + ""
								+ "  FROM etudiants,filieres,departements"
								+ " WHERE etudiants.filiere=filieres.id AND filieres.departement = departements.id ;");

				while (resultat.next()) {
					Etudiant et = new Etudiant();
					et.setId(resultat.getInt("id"));
					et.setNom(resultat.getString("nom"));
					et.setPrenom(resultat.getString("prenom"));
					et.setCne(resultat.getInt("cne"));
					et.setTel(resultat.getString("tel"));
					et.setFiliere(resultat.getString("filiere"));
					et.setDepartement(resultat.getString("departement"));
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

		for (Etudiant et : getAllStudents()) {
			String etFullName1 = et.getNom() + " " + et.getPrenom();
			String etFullName2 = et.getPrenom() + " " + et.getNom();
			if (etFullName1.equalsIgnoreCase(fullName) || etFullName2.equalsIgnoreCase(fullName))
				return et;
		}
		return null;
	}

	@Override
	public boolean insertStudent(Etudiant et) {
		// TODO Auto-generated method stub

		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO etudiants (cne,nom, prenom,filiere,tel) VALUES (?,?,?,?,?)");
			pstmt.setInt(1, et.getCne());
			pstmt.setString(2, et.getNom());
			pstmt.setString(3, et.getPrenom());
			pstmt.setInt(4, et.getFiliereId());
			pstmt.setString(5, et.getTel());

			int rowsAffected = pstmt.executeUpdate(); // Use executeUpdate for INSERT, UPDATE, DELETE queries

			pstmt.close();
			conn.close();

			return rowsAffected > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM etudiants WHERE id = ?;");
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate(); // Use executeUpdate for INSERT, UPDATE, DELETE queries
			pstmt.close();
			conn.close();

			return rowsAffected > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

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
	public Map<String, List<Filiere>> getAllDepartementsWithMajors() {

		Map<String, List<Filiere>> departmentMajorMap = new LinkedHashMap<>();
		String query = "SELECT d.id,d.departement, f.id, f.filiere " + "FROM departements d "
				+ "LEFT JOIN filieres f ON d.id =f.departement " + "ORDER BY d.id";
		try {
			Connection conn = getConnection();
			if (conn != null) {
				PreparedStatement statement = conn.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();
				String currentDepartment = null;
				List<Filiere> majors = new ArrayList<>();

				while (resultSet.next()) {
					String departmentName = resultSet.getString("departement");
					//System.out.println("department :" + departmentName);
					int majorId = resultSet.getInt("f.id");
					String majorName = resultSet.getString("filiere");
					//System.out.println("filiere :" + majorName);

					// Check if it's a new department
					if (currentDepartment == null || !currentDepartment.equals(departmentName)) {
						if (currentDepartment != null) {
							departmentMajorMap.put(currentDepartment, majors); // Add to map
						}

						currentDepartment = new String(departmentName);
						majors = new ArrayList<>();
						// Reset majors list for the new department
					}

					// Add major to the majors list
					majors.add(new Filiere(majorName, majorId));
				}

				// Add the last department and its majors to the map
				if (currentDepartment != null) {
					departmentMajorMap.put(currentDepartment, majors);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return departmentMajorMap;

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
		/*
		 * Etudiant et = new Etudiant(); et.setNom("Bou3boula");
		 * et.setPrenom("Debloum"); et.setCne(2020); et.setTel("20202020");
		 */

		// if(test.insertStudent(et)) System.out.println("Student Insrted
		// Successfully");
		/*
		 * if (test.deleteStudent(17)) { System.out.println("deleted succesfully"); }
		 * List<Etudiant> ets = test.getAllStudents();
		 * 
		 * for (Etudiant ett : ets) { System.out.println(ett.toString()); }
		 */

		// Print the map contents (for testing)
		for (Map.Entry<String, List<Filiere>> entry : test.getAllDepartements().entrySet()) {
		    String department = entry.getKey();
		    List<Filiere> majors = entry.getValue();

		    // Print department information
		    System.out.println("Department: " + department);

		    // Print majors for the department
		    for (Filiere major : majors) {
		        System.out.println("- Major: " + major.getNom() + ", ID: " + major.getId());
		    }
		}

		/*
		 * Etudiant e = test.findStudentByFullName("belmadani abdessamad");
		 * 
		 * if(e != null) { System.out.println(e.toString()); }else {
		 * System.out.println("no result found"); }
		 */

	}

	@Override
	public List<Departement> getAllDepartements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Etudiant findStudentByCNE(int cne) {
		for (Etudiant et : getAllStudents()) {
			
			if (et.getCne() == cne)
				return et;
		}
		return null;
	}

}
