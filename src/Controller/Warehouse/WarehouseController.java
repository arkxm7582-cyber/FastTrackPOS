package Controller.Warehouse;

import java.sql.ResultSet;
import Utils.Database.DatabaseConnection;
import java.sql.Connection;
import Model.Entities.Warehouse;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class WarehouseController {
    
        public void insertWarehouseAndUser(Warehouse warehouse) {
            Connection conn = null;
            PreparedStatement userStmt = null;
            PreparedStatement warehouseStmt = null;
            ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String insertUserSQL = "INSERT INTO users (email, password, role, name) VALUES (?, ?, ?, ?)";
            userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, warehouse.getEmail());
            userStmt.setString(2, warehouse.getPassword());
            userStmt.setString(3, warehouse.getRole().name());
            userStmt.setString(4, warehouse.getName());

            System.out.println("USER INSERT" + insertUserSQL);

            int userAffected = userStmt.executeUpdate();
            if (userAffected == 0) {
                throw new SQLException("User insertion failed, no rows affected.");
            }

            generatedKeys = userStmt.getGeneratedKeys();
            int userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve user ID.");
            }

            String insertWarehouseSQL = "INSERT INTO warehouses (user_id, address, district) VALUES (?, ?, ?)";
            warehouseStmt = conn.prepareStatement(insertWarehouseSQL);
            warehouseStmt.setInt(1, userId);
            warehouseStmt.setString(2, warehouse.getAddress());
            warehouseStmt.setString(3, warehouse.getDistrict());

            int warehouseAffected = warehouseStmt.executeUpdate();
            if (warehouseAffected == 0) {
                throw new SQLException("Warehouse insertion failed.");
            }

            conn.commit(); 

            JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try { if (generatedKeys != null) generatedKeys.close(); } catch (SQLException ex) {}
            try { if (userStmt != null) userStmt.close(); } catch (SQLException ex) {}
            try { if (warehouseStmt != null) warehouseStmt.close(); } catch (SQLException ex) {}
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }
    
    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouseList = new ArrayList<>();
        String query = "SELECT w.id AS warehouse_id, w.user_id, w.address, w.district, "
             + "FROM warehouses w ";
        
            String query2 = "SELECT u.id AS user_id, u.email, u.password, u.role, u.name, " +
                   "w.id AS warehouse_id, w.address, w.district " +
                   "FROM users u " +
                   "JOIN warehouses w ON u.id = w.user_id " +
                   "WHERE u.role = 'WAREHOUSE'";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query2);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Warehouse warehouse = new Warehouse(
                    rs.getString("address"),
                    rs.getString("district"),
                    rs.getString("email"),
                    rs.getString("password"),
                    Model.Enums.UserRole.valueOf(rs.getString("role")),
                    rs.getString("name")
                );
                warehouse.setId(rs.getInt("warehouse_id"));
                warehouse.setDistrict(rs.getString("district"));
                warehouseList.add(warehouse);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return warehouseList;
    }
    
    public List<String> getAllDistricts() {
    List<String> districts = new ArrayList<>();
    String query = "SELECT DISTINCT district FROM warehouses";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            districts.add(rs.getString("district"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return districts;
    }
    
    public Warehouse getWarehouseByUserId(int userId) {
        String query = "SELECT u.id AS user_id, u.email, u.password, u.role, u.name, " +
                       "w.id AS warehouse_id, w.address, w.district " +
                       "FROM users u " +
                       "JOIN warehouses w ON u.id = w.user_id " +
                       "WHERE u.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Warehouse warehouse = new Warehouse(
                        rs.getString("address"),
                        rs.getString("district"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Model.Enums.UserRole.valueOf(rs.getString("role")),
                        rs.getString("name")
                    );
                    warehouse.setId(rs.getInt("warehouse_id"));
                    warehouse.setDistrict(rs.getString("district"));
                    return warehouse;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null; // Return null if no warehouse found for the given user ID
    }

}
