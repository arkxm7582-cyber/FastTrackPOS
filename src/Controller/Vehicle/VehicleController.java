/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vehicle;

import Utils.Database.DatabaseConnection;
import Model.Entities.Vehicle;
import Model.Enums.VehicleType;
import Utils.Response.InsertResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleController {
        public InsertResult insertVehicle(Vehicle vehicle) {
            String insertSQL = "INSERT INTO vehicles (vehicle_type, registration_number, model) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, vehicle.getVehicleType().name());
                stmt.setString(2, vehicle.getNumberPlate());
                stmt.setString(3, vehicle.getModel());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows == 0) {
                    return new InsertResult(false, "Inserting vehicle failed, no rows affected.");
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vehicle.setId(generatedKeys.getInt(1));
                    }
                }

            return new InsertResult(true, "Vehicle inserted successfully");

            } catch (SQLIntegrityConstraintViolationException dupEx) {
                // This handles duplicate registration numbers
                return new InsertResult(false, "Registration number already exists.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new InsertResult(false, "Database error occurred: " + ex.getMessage());
            }
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                    rs.getString("model"),
                    rs.getString("registration_number"),
                    VehicleType.valueOf(rs.getString("vehicle_type"))
                );
                vehicle.setId(rs.getInt("id"));
                vehicles.add(vehicle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vehicles;
    }
    
    public int getVehicleIdByNumberPlate(String numberPlate) {
    String query = "SELECT id FROM vehicles WHERE registration_number = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, numberPlate);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return -1; // Not found or error
}

}


