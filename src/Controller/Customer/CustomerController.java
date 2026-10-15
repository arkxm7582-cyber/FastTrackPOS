/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer;

import static Controller.Authetication.CustomerSession.setCurrentCustomer;
import Utils.Database.DatabaseConnection;
import Utils.Response.InsertResult;
import java.sql.Connection;
import Model.Entities.Customer;
import Model.Enums.UserRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class CustomerController {
        public InsertResult insertCustomer(Customer customer) {
        String userSQL = "INSERT INTO users (email, password, role, name) VALUES (?, ?, ?, ?)";
        String customerSQL = "INSERT INTO customers (user_id, address, telephone, closest_warehouse) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int userId;

            try (PreparedStatement userStmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, customer.getEmail());
                userStmt.setString(2, customer.getPassword());
                userStmt.setString(3, customer.getRole().name());
                userStmt.setString(4, customer.getName());
                userStmt.executeUpdate();

                try (ResultSet rs = userStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return new InsertResult(false, "Failed to create user.");
                    }
                }
            }

            // Insert into customers table
            try (PreparedStatement custStmt = conn.prepareStatement(customerSQL)) {
                custStmt.setInt(1, userId);
                custStmt.setString(2, customer.getAddress());
                custStmt.setString(3, customer.getTelephone());
                custStmt.setInt(4, customer.getClosestWarehouseId());
                custStmt.executeUpdate();
            }

            conn.commit();
            setCurrentCustomer(customer);
            return new InsertResult(true, "Customer registered successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            return new InsertResult(false, "Duplicate email or data constraint failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Database error: " + e.getMessage());
        }
    }
        
    public Integer getClosestWarehouseForUser(String email) {
        String getUserSQL = "SELECT id FROM users WHERE email = ?";
        String getCustomerSQL = "SELECT closest_warehouse FROM customers WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(getUserSQL)) {

            // Step 1: Get user ID from email
            userStmt.setString(1, email);
            try (ResultSet userRs = userStmt.executeQuery()) {
                if (userRs.next()) {
                    int userId = userRs.getInt("id");

                    // Step 2: Get customer's closest warehouse using user_id
                    try (PreparedStatement customerStmt = conn.prepareStatement(getCustomerSQL)) {
                        customerStmt.setInt(1, userId);
                        try (ResultSet customerRs = customerStmt.executeQuery()) {
                            if (customerRs.next()) {
                                return customerRs.getInt("closest_warehouse");
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found or error
    }

    public Customer getCustomerByUserId(int userId) {
        String sql = "SELECT c.id, u.email, u.password, u.role, u.name, c.address, c.telephone, c.closest_warehouse " +
                     "FROM customers c " +
                     "JOIN users u ON c.user_id = u.id " +
                     "WHERE c.user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        rs.getInt("closest_warehouse"),
                        rs.getString("email"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found or error
    }
    
    public String getUserEmailByCustomerId(int customerId) {
        String sql = "SELECT s.email " +
                     "FROM users s " +
                     "JOIN customers c ON c.user_id = s.id " +
                     "WHERE c.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs + " ddd");
                    return rs.getString("email");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found or error
    }


}
