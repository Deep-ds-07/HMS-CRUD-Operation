package Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete_Patients {

	private Connection connection;
	
	public Delete_Patients(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("resource")
	public void deletePatient() {
		Scanner scanner = new Scanner(System.in);

		final String DOCTOR_PASSWORD = "doctor123"; // Doctor's password
		int attempts = 0;
		boolean accessGranted = false;

		// Loop to allow up to three password attempts
		while (attempts < 3 && !accessGranted) {
			System.out.println("Only for Doctor's Use........");
			System.out.print("Enter Doctor's Password: ");
			String password = scanner.next();

			if (DOCTOR_PASSWORD.equals(password)) {
				accessGranted = true;
			} else {
				System.err.println("Access Denied: Incorrect Password");
				attempts++;
				if (attempts < 3) {
					System.out.println("Please try again. Attempts left: " + (3 - attempts));
				}
			}
		}

		if (!accessGranted) {
			System.err.println("Access Denied: Maximum attempts reached");
			return;
		}

		System.out.println("How would you like to delete the patient?");
		System.out.println("1. By ID");
		System.out.println("2. By Name");
		System.out.print("Enter your choice (1 or 2): ");
		int choice = scanner.nextInt();;
		
		String query = null;
		PreparedStatement ps = null;

		try {
			
		if (choice == 1) {
			// Deletion by ID
			System.out.print("Enter Patient's ID to Delete: ");
			int patientId = scanner.nextInt();

			query = "DELETE FROM patients WHERE id = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, patientId);

		} else if (choice == 2) {
			// Deletion by Name
			System.out.print("Enter Patient's Name to Delete: ");
			String name = scanner.next();

			query = "DELETE FROM patients WHERE name = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, name);
			
		} else {
			System.err.println("Invalid choice. Please restart the process.");
			return;
		}
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Patient Details deleted successfully");
				 return;
			} else {
				System.err.println("ERROR: This patient not exists in our hospital");
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		scanner.close();
	}
}
