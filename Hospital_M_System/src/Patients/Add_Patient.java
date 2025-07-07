package Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Add_Patient {
	// connection interface from java.util
	private Connection connection;
	private Scanner scanner;

	// Parametrised constructor
	public Add_Patient(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void addPatient() {

		System.out.print("Enter Patient's Name:  ");
		String name = scanner.next();
		System.out.print("Enter Patient's Age:  ");
		int age = scanner.nextInt();
		System.out.print("Enter Patient's Gender:  ");
		String gender = scanner.next();
		System.out.print("Enter Patient's Address:  ");
		String location = scanner.next();

		try {
			String query = "insert into patients(name, age, gender, location) VALUES (?, ?, ?, ?)"; // added patient
																									// details
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setString(4, location);

			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Patients Details added Succesfully");
			} else {
				System.err.println("ERROR Failed to add Pateint>>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			scanner.close();
		}

	}
}
