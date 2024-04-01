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
								+ " WHERE etudiants.filiere=filieres.id AND filieres.departement = departements.id ORDER BY filieres.filiere;");

				while (resultat.next()) {
					Etudiant et = new Etudiant();
					et.setId(resultat.getInt("id"));
					et.setNom(resultat.getString("nom"));
					et.setPrenom(resultat.getString("prenom"));
					et.setCne(resultat.getInt("cne"));
					et.setTel(resultat.getString("tel"));
					
					Filiere fl = new Filiere();
					fl.setNom(resultat.getString("filiere"));
					
					Departement depart = new Departement();
					depart.setNom(resultat.getString("departement"));
					
					fl.setDepartement(depart);
					
					et.setFiliere(fl);
					
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
			pstmt.setInt(4, et.getFiliere().getId());
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
		List<Filiere> filieres = new ArrayList<Filiere>();

		try {
			Connection conn = this.getConnection();

			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet resultat = stmt.executeQuery("SELECT m.id, m.filiere ,d.departement,"
						+ "                    COUNT(DISTINCT s.id) AS student_count "
						+ "                    FROM filieres m LEFT JOIN departements d ON m.departement = d.id "
						+ "                    LEFT JOIN etudiants s ON m.id = s.filiere "
						+ "                   GROUP BY m.id, m.filiere, m.departement;");

				while (resultat.next()) {
					Filiere major = new Filiere();
					major.setId(resultat.getInt("id"));
					major.setNom(resultat.getString("filiere"));
					major.setEtudiantCount(resultat.getInt("student_count"));
					
					Departement depart = new Departement();
					depart.setNom(resultat.getString("departement"));
					
					major.setDepartement(depart);
					filieres.add(major);
				}
				stmt.close();
				resultat.close();
			}

			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return filieres;
	}

	@Override
	public boolean insertMajor(Filiere fl) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO filieres (filiere,departement) VALUES (?,?)");
			
			pstmt.setString(1, fl.getNom());
			pstmt.setInt(2, fl.getDepartement().getId());

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
	public boolean deleteMajor(int id) {
		
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM filieres WHERE id = ?;");
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
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO departements (departement) VALUES (?)");
			
			pstmt.setString(1, depart.getNom());

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
	public boolean deleteDepartement(int id) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departements WHERE id = ?;");
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
	public boolean updateDepartement(int id, Departement newData) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		MySqlPersistence test = new MySqlPersistence();
		
		/*  Filiere et = new Filiere();
		  et.setNom("Test");
		  Departement dp =new Departement();
		  dp.setId(1);
		  et.setDepartement(dp);
		  
		 

		// if(test.insertMajor(et)) System.out.println("Major Insrted Successfully");
		
		
		
		  if (test.deleteMajor(9)) { System.out.println("deleted succesfully"); }
		 
		List<Filiere> ets = test.getAllMajors();
		  
		  for (Filiere ett : ets) { System.out.println(ett.toString()); }
		/*

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
		List<Departement> departements = new ArrayList<Departement>();

		try {
			Connection conn = this.getConnection();

			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet resultat = stmt.executeQuery("SELECT d.id, d.departement ,\r\n"
						+ "                    COUNT(DISTINCT s.id) AS student_count,\r\n"
						+ "                    COUNT(DISTINCT m.id) AS major_count \r\n"
						+ "                    FROM departements d\r\n"
						+ "                    LEFT JOIN filieres m ON d.id = m.departement \r\n"
						+ "                    LEFT JOIN etudiants s ON m.id = s.filiere \r\n"
						+ "                   GROUP BY d.id, d.departement;");

				while (resultat.next()) {
					Departement depart = new Departement();
					depart.setId(resultat.getInt("id"));
					depart.setNom(resultat.getString("departement"));
					depart.setEtudiantCount(resultat.getInt("student_count"));
					depart.setFiliereCount(resultat.getInt("major_count"));
					departements.add(depart);
				}
				stmt.close();
				resultat.close();
			}

			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return departements;
	}

	@Override
	public Etudiant findStudentByCNE(int cne) {
		for (Etudiant et : getAllStudents()) {
			
			if (et.getCne() == cne)
				return et;
		}
		return null;
	}

	@Override
	public Filiere findMajorByName(String name) {
		
         for (Filiere major : getAllMajors()) {
			
			if (major.getNom().equalsIgnoreCase(name) )
				return major;
		}
		return null;
	}

	@Override
	public Filiere findMajorById(int id) {
		
    for (Filiere major : getAllMajors()) {
			
			if (major.getId()==id  )
				return major;
		}
		return null;
	}

	@Override
	public Departement findDepartByName(String name) {
		
		for (Departement depart : getAllDepartements()) {
			
			if (depart.getNom().equalsIgnoreCase(name) )
				return depart;
		}
		return null;
	}

	@Override
	public Departement findDepartById(int id) {
        for (Departement depart : getAllDepartements()) {
			
			if (depart.getId()==id )
				return depart;
		}
		return null;
	}

}
