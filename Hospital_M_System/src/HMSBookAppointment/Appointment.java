package HMSBookAppointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Doctorss.Doctor;
import Patients.View_Patient;

public class Appointment {
	

	public void BookApointment(View_Patient VP, Doctor doctor, Connection connection, Scanner scanner) {

		System.out.println("Enter Patients ID: ");
		int patientId = scanner.nextInt();
		System.out.println("Enter Doctors ID: ");
		int doctorId = scanner.nextInt();
		System.out.println("Enter Appointment Date (YYYY-MM-DD):  ");
		String AppointmenDate = scanner.next();

		if (VP.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
			if (checkDoctorAvailability(doctorId, AppointmenDate, connection)) {
				String appointmentquery = "INSERT INTO Appointment(patient_id, doctor_id,appointment_date) VALUES (?, ?, ?)";
				try {
					PreparedStatement pst = connection.prepareStatement(appointmentquery);
					pst.setInt(1, patientId);
					pst.setInt(2, doctorId);
					pst.setString(3, AppointmenDate);

					int rowAffected = pst.executeUpdate();
					if (rowAffected > 0) {
						System.out.println("..YOUR APPOINTMENT IS BOOKED..");
					} else {
						System.err.println("Failed to Book Appointment ");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
					//System.out.println();
			} else {
				System.err.println("Sorry Doctor Not Available on this Date Please select Other DATE...");
			}
		} else {
			System.err.println("Doctor OR Patient not EXISTS in our Hospital...");
		}
	}

	public static boolean checkDoctorAvailability(int doctorId, String AppointmenDate, Connection connection) {

		String query = "SELECT COUNT(*) FROM Appointment where doctor_id = ? AND appointment_date = ? ";
		try {
			PreparedStatement pstd = connection.prepareStatement(query);
			pstd.setInt(1, doctorId);
			pstd.setString(2, AppointmenDate);

			ResultSet Rs = pstd.executeQuery();
			if (Rs.next()) {
				int count = Rs.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
