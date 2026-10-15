/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Authetication;

import Model.Entities.User;
import Utils.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Enums.UserRole;
import Utils.Response.LoginResult;
import java.sql.SQLException;

public class AuthenticationController {
    
        public LoginResult authenticate(String email, String password) {
            String query = "SELECT * FROM users WHERE email = ?";
            System.out.println("E" + email + password);
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, email);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String dbPassword = rs.getString("password");

                        if (!dbPassword.equals(password)) {
                            return new LoginResult(false, "Incorrect password.", null);
                        }

                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        UserRole role = UserRole.valueOf(rs.getString("role"));

                        User user = new User(id, email, password, role, name);
                        return new LoginResult(true, "Login successful.", user);
                    } else {
                        return new LoginResult(false, "User not found with provided email.", null);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return new LoginResult(false, "Database error occurred during login.", null);
            }
    }

    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    String name = rs.getString("name");

                    return new User(userId, email, password, UserRole.valueOf(role), name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // User not found
    }
}
