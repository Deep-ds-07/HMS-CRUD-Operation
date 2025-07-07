package Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Patient {
	
	public Connection connection;

	 public View_Patient(Connection connection) {
	        this.connection = connection;
	    }

	public void viewPatient() {
		String query = "SELECT * From Patients";
		try {
			PreparedStatement PS = connection.prepareStatement(query);
			
			ResultSet RS  = PS.executeQuery();     // ResultSet it is interface
			System.out.println("Patients :  ");
			System.out.println("+------------+--------------------+------+---------+---------------------+"); // this is for print data in table format
			System.out.println("| Patient ID | Name               | Age  | Gender  | Location            |");
			System.out.println("+------------+--------------------+------+---------+---------------------+");
			
			while(RS.next()) {
				int id = RS.getInt("id");
				String name = RS.getString("name");
				int age = RS.getInt("age");
				String gender = RS.getString("gender");
				String Address = RS.getString("location");
				
				System.out.printf("|%-12s|%-20s|%-6s|%-9s|%-21s|\n ",id, name, age, gender,Address);
				System.out.println("+------------+--------------------+------+---------+---------------------+");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean getPatientById(int id) {
		String query = "Select * from patients WHERE id = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet RS = ps.executeQuery();
			if(RS.next()) {
				return true;
			}else {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
