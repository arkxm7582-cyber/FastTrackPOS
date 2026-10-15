/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Shipment;

import Model.Entities.Shipment;
import Model.Enums.PackageStatus;
import Utils.Database.DatabaseConnection;
import Utils.Response.InsertResult;
import Utils.Response.ShipmentDetailsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipmentController {
         public InsertResult insertShipment(Shipment shipment, int customerId) {
        String sql = "INSERT INTO shipments (delivery_agent_id, pickup_agent_id, delivery_warehouse_id, pickup_warehouse_id, customer_id, package_content, pickup_time, delivery_time, status, price, created_at, updated_at, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, shipment.getDeliveryAgentId());
            stmt.setInt(2, shipment.getPickupAgentId());
            stmt.setInt(3, shipment.getDeliveryWarehouseId());
            stmt.setInt(4, shipment.getPickupWarehouseId());
            stmt.setInt(5, customerId);
            stmt.setString(6, shipment.getPackageContent());
            stmt.setTimestamp(7, new Timestamp(shipment.getPickupTime().getTime()));
            stmt.setTimestamp(8, new Timestamp(shipment.getDeliveryTime().getTime()));
            stmt.setString(9, shipment.getPackageStatus().name());
            stmt.setInt(10, shipment.getPrice());

            Timestamp now = new Timestamp(new Date().getTime());
            stmt.setTimestamp(11, now); // created_at
            stmt.setTimestamp(12, now); // updated_at
            stmt.setString(13, shipment.getAddress());

            int rows = stmt.executeUpdate();

             if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        System.out.println("GENERATED ID" + generatedId);
                        return new InsertResult(true, "Shipment inserted successfully. ID: " + generatedId, generatedId);
                    }
                }
            }
            return new InsertResult(false, "Shipment insert failed.");

        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Database error: " + e.getMessage());
        }
    }
         
    public List<Shipment> getShipmentsByCustomerId(int customerId) {
        String sql = "SELECT * FROM shipments WHERE customer_id = ?";
        List<Shipment> shipments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Shipment shipment = new Shipment(
                        rs.getInt("pickup_agent_id"),
                        rs.getInt("delivery_agent_id"),
                        rs.getInt("pickup_warehouse_id"),
                        rs.getInt("delivery_warehouse_id"),
                        rs.getString("package_content"),
                        rs.getTimestamp("pickup_time"),
                        rs.getTimestamp("delivery_time"),
                        PackageStatus.valueOf(rs.getString("status")),
                        rs.getInt("price"),
                        rs.getString("address")
                    );
                    shipment.setId(rs.getInt("id")); // If you have a shipment ID field
                    shipments.add(shipment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipments;
    }
    
    public Shipment getShipmentById(int id) {
        String sql = "SELECT * FROM shipments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Shipment shipment = new Shipment(
                        rs.getInt("pickup_agent_id"),
                        rs.getInt("delivery_agent_id"),
                        rs.getInt("pickup_warehouse_id"),
                        rs.getInt("delivery_warehouse_id"),
                        rs.getString("package_content"),
                        rs.getTimestamp("pickup_time"),
                        rs.getTimestamp("delivery_time"),
                        PackageStatus.valueOf(rs.getString("status")),
                        rs.getInt("price"),
                        rs.getString("address")
                    );
                    shipment.setId(rs.getInt("id")); // If applicable
                    return shipment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if not found or error
    }
    
    public List<Shipment> getShipmentsByDeliveryAgentId(int deliveryAgentId) {
        String sql = "SELECT * FROM shipments WHERE delivery_agent_id = ? || pickup_agent_id = ?";
        List<Shipment> shipments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deliveryAgentId);
            stmt.setInt(2, deliveryAgentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Shipment shipment = new Shipment(
                        rs.getInt("pickup_agent_id"),
                        rs.getInt("delivery_agent_id"),
                        rs.getInt("pickup_warehouse_id"),
                        rs.getInt("delivery_warehouse_id"),
                        rs.getString("package_content"),
                        rs.getTimestamp("pickup_time"),
                        rs.getTimestamp("delivery_time"),
                        PackageStatus.valueOf(rs.getString("status")),
                        rs.getInt("price"),
                        rs.getString("address")
                    );
                    shipment.setId(rs.getInt("id"));
                    shipments.add(shipment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipments;
    }
    
    public InsertResult updateShipment(Shipment shipment) {
        String sql = "UPDATE shipments SET " +
                     "pickup_agent_id = ?, " +
                     "delivery_agent_id = ?, " +
                     "pickup_warehouse_id = ?, " +
                     "delivery_warehouse_id = ?, " +
                     "package_content = ?, " +
                     "pickup_time = ?, " +
                     "delivery_time = ?, " +
                     "status = ?, " +
                     "price = ?, " +
                     "address = ?, " +
                     "updated_at = ? " +
                     "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shipment.getPickupAgentId());
            stmt.setInt(2, shipment.getDeliveryAgentId());
            stmt.setInt(3, shipment.getPickupWarehouseId());
            stmt.setInt(4, shipment.getDeliveryWarehouseId());
            stmt.setString(5, shipment.getPackageContent());
            stmt.setTimestamp(6, new Timestamp(shipment.getPickupTime().getTime()));
            stmt.setTimestamp(7, new Timestamp(shipment.getDeliveryTime().getTime()));
            stmt.setString(8, shipment.getPackageStatus().name());
            stmt.setInt(9, shipment.getPrice());
            stmt.setString(10, shipment.getAddress());
            stmt.setTimestamp(11, new Timestamp(new Date().getTime())); // updated_at
            stmt.setInt(12, shipment.getId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return new InsertResult(true, "Shipment updated successfully.");
            } else {
                return new InsertResult(false, "Shipment update failed. No rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Database error: " + e.getMessage());
        }
    }
    
    public List<Shipment> getShipmentsByWarehouseId(int warehouseId) {
    String sql = "SELECT * FROM shipments WHERE pickup_warehouse_id = ? OR delivery_warehouse_id = ?";
    List<Shipment> shipments = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, warehouseId);
        stmt.setInt(2, warehouseId);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Shipment shipment = new Shipment(
                rs.getInt("id"),
                rs.getInt("delivery_agent_id"),
                rs.getInt("pickup_agent_id"),
                rs.getInt("delivery_warehouse_id"),
                rs.getInt("pickup_warehouse_id"),
                rs.getInt("customer_id"),
                rs.getString("package_content"),
                rs.getTimestamp("pickup_time"),
                rs.getTimestamp("delivery_time"),
                PackageStatus.valueOf(rs.getString("status")),
                rs.getInt("price"),
                rs.getString("address")
            );


                // Set warehouse source flag
                int pickupWarehouseId = rs.getInt("pickup_warehouse_id");
                if (pickupWarehouseId == warehouseId) {
                    shipment.setFromPickupWarehouse(true);
                } else {
                    shipment.setFromPickupWarehouse(false);
                }

                shipments.add(shipment);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return shipments;
}

private static final String DETAILED_SHIPMENT_SQL =
    "SELECT s.id, s.package_content, s.status, s.price, s.address, s.pickup_time, s.delivery_time, " +
    "cu.id as customer_id, cu_user.email as customer_email, " +
    "da_user.name as delivery_agent_name, pa_user.name as pickup_agent_name, " +
    "dw_user.name as delivery_warehouse_name, pw_user.name as pickup_warehouse_name " +
    "FROM shipments s " +
    "LEFT JOIN customers cu ON s.customer_id = cu.id " +
    "LEFT JOIN users cu_user ON cu.user_id = cu_user.id " +
    "LEFT JOIN agents da ON s.delivery_agent_id = da.id " +
    "LEFT JOIN users da_user ON da.user_id = da_user.id " +
    "LEFT JOIN agents pa ON s.pickup_agent_id = pa.id " +
    "LEFT JOIN users pa_user ON pa.user_id = pa_user.id " +
    "LEFT JOIN warehouses dw ON s.delivery_warehouse_id = dw.id " +
    "LEFT JOIN users dw_user ON dw.user_id = dw_user.id " +  // 👈 warehouse name from users
    "LEFT JOIN warehouses pw ON s.pickup_warehouse_id = pw.id " +
    "LEFT JOIN users pw_user ON pw.user_id = pw_user.id " +  // 👈 warehouse name from users
    "WHERE s.id = ?";

    public ShipmentDetailsDTO getDetailedShipmentInfo(int shipmentId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DETAILED_SHIPMENT_SQL)) {

            stmt.setInt(1, shipmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ShipmentDetailsDTO dto = new ShipmentDetailsDTO();
                    dto.shipmentId = rs.getInt("id");
                    dto.packageContent = rs.getString("package_content");
                    dto.status = rs.getString("status");
                    dto.price = rs.getDouble("price");
                    dto.address = rs.getString("address");
                    dto.pickupTime = rs.getTimestamp("pickup_time");
                    dto.deliveryTime = rs.getTimestamp("delivery_time");
                    dto.customerEmail = rs.getString("customer_email");
                    dto.deliveryAgentName = rs.getString("delivery_agent_name");
                    dto.pickupAgentName = rs.getString("pickup_agent_name");
                    dto.deliveryWarehouseName = rs.getString("delivery_warehouse_name");
                    dto.pickupWarehouseName = rs.getString("pickup_warehouse_name");
                    return dto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 
   public InsertResult deleteShipmentById(int shipmentId) {
        String sql = "DELETE FROM shipments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shipmentId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return new InsertResult(true, "Shipment deleted successfully.");
            } else {
                return new InsertResult(false, "No shipment found with the given ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new InsertResult(false, "Database error: " + e.getMessage());
        }
    }


}

