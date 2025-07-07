package Doctorss;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	// connection interface from java.util
	private Connection connection;

	// Parametrised constructor
	public Doctor(Connection connection) {
		this.connection = connection;

	}

	public void viewDoctors() {
		String query = "SELECT * From doctors";

		try {
			PreparedStatement PS = connection.prepareStatement(query);
			ResultSet RS = PS.executeQuery(); // ResultSet it is interface
			System.out.println("Doctors :  ");
			System.out.println("+------------+-----------------------+---------------------------+"); // this is for print data in table format
			System.out.println("| Doctors ID | Name                  | specialization            |");
			System.out.println("+------------+-----------------------+---------------------------+");
			
			while (RS.next()) {
				int id = RS.getInt("id");
				String name = RS.getString("name");
				String specialization = RS.getString("specialization");

				System.out.printf("|%-12s|%-23s|%-27s|\n", id, name, specialization);
				System.out.println("+------------+-----------------------+---------------------------+");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getDoctorById(int id) {
		String query = "Select * from doctors WHERE id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet RS = ps.executeQuery();
			if (RS.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}