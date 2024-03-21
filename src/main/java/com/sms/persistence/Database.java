package com.sms.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void main(String[] args) {
		
	
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/annuaire_ens";
		String utilisateur = "root";
		Connection connexion = null;

		try {
			connexion = DriverManager.getConnection(url, utilisateur,"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(connexion == null) {
			System.out.println("Connection is null");
			return;
		}
		
		//select all student
		

		try {
			Statement stmt = connexion.createStatement();
			ResultSet resultat = stmt.executeQuery("SELECT *  FROM etudiants;" );
			
			
			while(resultat.next()) {
				System.out.println(resultat.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
