package Controller.Agent;

import Utils.Database.DatabaseConnection;
import Model.Entities.Agent;
import Model.Enums.AgentStatus;
import Model.Enums.UserRole;
import Utils.Response.InsertResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentController {


    public InsertResult insertAgent(Agent agent) {
        System.out.println("controller agent: " + agent.toString());

        String userSQL = "INSERT INTO users (email, password, role, name) VALUES (?, ?, ?, ?)";
        String agentSQL = "INSERT INTO agents (user_id, warehouse_id, vehicle_id, nic, telephone, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int userId;
            try (PreparedStatement userStmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, agent.getEmail());
                userStmt.setString(2, agent.getPassword());
                userStmt.setString(3, agent.getRole().name());
                userStmt.setString(4, agent.getName());
                userStmt.executeUpdate();

                try (ResultSet rs = userStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return new InsertResult(false, "Failed to retrieve user ID after insertion.");
                    }
                }
            }

            try (PreparedStatement agentStmt = conn.prepareStatement(agentSQL)) {
                agentStmt.setInt(1, userId);
                agentStmt.setInt(2, agent.getWarehouseId());
                agentStmt.setInt(3, agent.getVehicleId());
                agentStmt.setString(4, agent.getNIC());
                agentStmt.setString(5, agent.getTelephone());
                agentStmt.setString(6, agent.getAgentStatus().name());

                agentStmt.executeUpdate();
            }

            conn.commit();
            return new InsertResult(true, "Agent registered successfully.");

        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("nic")) {
                return new InsertResult(false, "NIC already exists.");
            } else if (e.getMessage().contains("email")) {
                return new InsertResult(false, "Email already exists.");
            }
            return new InsertResult(false, "Integrity constraint violation: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Database error occurred: " + e.getMessage());
        }
    }


    public List<Agent> getAllAgents() {
        List<Agent> agents = new ArrayList<>();
        String query = "SELECT a.*, u.email, u.password, u.name, u.role " +
                       "FROM agents a JOIN users u ON a.user_id = u.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agent agent = new Agent(
                        rs.getInt("warehouse_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("nic"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("name")
                );
                
                agent.setAgentStatus(AgentStatus.valueOf(rs.getString("status")));
                agents.add(agent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agents;
    }
      public List<Agent> getAgentsByDistrict(String district) {
    List<Agent> agents = new ArrayList<>();
    String query = "SELECT a.*, u.email, u.password, u.name, u.role " +
                   "FROM agents a " +
                   "JOIN users u ON a.user_id = u.id " +
                   "JOIN warehouses w ON a.warehouse_id = w.id " +
                   "WHERE w.district = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, district);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Agent agent = new Agent(
                    rs.getInt("id"),
                    rs.getInt("warehouse_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("nic"),
                    rs.getString("telephone"),
                    AgentStatus.valueOf(rs.getString("status")),
                    rs.getString("email"),
                    rs.getString("password"),
                    UserRole.valueOf(rs.getString("role")),
                    rs.getString("name")
                );
                agents.add(agent);
                System.out.println("Agents from conttroller " + agent);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return agents;
}
   

    public List<Agent> getAgentsByWarehouseId(int warehouseId) {
        List<Agent> agents = new ArrayList<>();
        String query = "SELECT a.*, u.email, u.password, u.name, u.role " +
                       "FROM agents a " +
                       "JOIN users u ON a.user_id = u.id " +
                       "WHERE a.warehouse_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, warehouseId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agent agent = new Agent(
                        rs.getInt("id"),
                        rs.getInt("warehouse_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("nic"),
                        rs.getString("telephone"),
                        AgentStatus.valueOf(rs.getString("status")),
                        rs.getString("email"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("name")
                    );
                    agents.add(agent);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agents;
    }
    
    public Agent getAgentByUserId(int userId) {
        String query = "SELECT a.*, u.email, u.password, u.name, u.role " +
                       "FROM agents a " +
                       "JOIN users u ON a.user_id = u.id " +
                       "WHERE u.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agent agent = new Agent(
                        rs.getInt("id"),
                        rs.getInt("warehouse_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("nic"),
                        rs.getString("telephone"),
                        AgentStatus.valueOf(rs.getString("status")),
                        rs.getString("email"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("name")
                    );
                    return agent;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if not found or error
    }

    public InsertResult deleteAgentById(int userId) {
        String deleteAgentSQL = "DELETE FROM agents WHERE user_id = ?";
        String deleteUserSQL = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement agentStmt = conn.prepareStatement(deleteAgentSQL)) {
                agentStmt.setInt(1, userId);
                int affectedRows = agentStmt.executeUpdate();

                if (affectedRows == 0) {
                    conn.rollback();
                    return new InsertResult(false, "Agent not found or already deleted.");
                }
            }

            try (PreparedStatement userStmt = conn.prepareStatement(deleteUserSQL)) {
                userStmt.setInt(1, userId);
                userStmt.executeUpdate();
            }

            conn.commit();
            return new InsertResult(true, "Agent deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Error deleting agent: " + e.getMessage());
        }
    }


}
