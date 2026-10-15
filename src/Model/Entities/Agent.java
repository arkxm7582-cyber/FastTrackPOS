/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.AgentStatus;
import Model.Enums.UserRole;

public class Agent extends User{
    private int Id;
    private int WarehouseId;
    private int VehicleId;
    private String NIC;
    private String Telephone;
    private AgentStatus AgentStatus;
    

    public Agent(int WarehouseId, int VehicleId, String NIC, String Telephone, String Email, String Password, UserRole Role, String Name) {
        super(Email, Password, Role, Name);
        this.WarehouseId = WarehouseId;
        this.VehicleId = VehicleId;
        this.NIC = NIC;
        this.Telephone = Telephone;
        this.AgentStatus = AgentStatus.AVAILABLE;
    }

    public Agent(int Id, int WarehouseId, int VehicleId, String NIC, String Telephone, AgentStatus AgentStatus, String Email, String Password, UserRole Role, String Name) {
        super(Email, Password, Role, Name);
        this.Id = Id;
        this.WarehouseId = WarehouseId;
        this.VehicleId = VehicleId;
        this.NIC = NIC;
        this.Telephone = Telephone;
        this.AgentStatus = AgentStatus;
    }
    
    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public int getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(int WarehouseId) {
        this.WarehouseId = WarehouseId;
    }

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int VehicleId) {
        this.VehicleId = VehicleId;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public AgentStatus getAgentStatus() {
        return AgentStatus;
    }

    public void setAgentStatus(AgentStatus AgentStatus) {
        this.AgentStatus = AgentStatus;
    }

    @Override
    public String toString() {
        return "Agent{" + "Id=" + Id + ", WarehouseId=" + WarehouseId + ", VehicleId=" + VehicleId + ", NIC=" + NIC + ", Telephone=" + Telephone + ", AgentStatus=" + AgentStatus + '}';
    }    
}
