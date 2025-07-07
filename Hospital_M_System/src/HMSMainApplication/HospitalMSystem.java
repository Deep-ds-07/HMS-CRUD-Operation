package HMSMainApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Doctorss.Doctor;
import HMSBookAppointment.Appointment;
import Patients.Add_Patient;
import Patients.Delete_Patients;
import Patients.View_Patient;

public class HospitalMSystem {

	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String username = "root";
	private static final String Password = "2002";

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);

		try {
			Connection connection = DriverManager.getConnection(url, username, Password);
			Add_Patient AP = new Add_Patient(connection, scanner);
			View_Patient VP = new View_Patient(connection);
			Doctor doctor = new Doctor(connection);
			Appointment appointment = new Appointment();
			Delete_Patients DP = new Delete_Patients(connection);
			while (true) {
				System.out.println("<<<<< HOSPITAL MANAGEMENT SYSTEM >>>>>");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patient");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appointment");
				System.out.println("5. Delete Patient #ONLY FOR DOCTOR'S ACCESS)");
				System.out.println("6. Exit");
				System.out.println("Enter Your Choice  NUMBER:  ");
				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					// Add Patient
					AP.addPatient();
					System.out.println();
					break;

				case 2:
					// view Patient
					VP.viewPatient();
					System.out.println();
					break;

				case 3:
					// View Doctors
					doctor.viewDoctors();
					System.out.println();
					break;

				case 4:
					// Book Appointment
					appointment.BookApointment(VP, doctor, connection, scanner);
					System.out.println();
					break;

				case 5:
					//Delete Patient
					DP.deletePatient();
					System.out.println();
					break;
					
				case 6:
					return;

				default:
					System.err.println("Please Enter Valid Choice.....:");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		scanner.close();
	}

}
