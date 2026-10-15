/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.UserRole;

public class Customer extends User{
    private int Id;
    private String Address;
    private String Telephone;
    private String NIC;
    private int ClosestWarehouseId;

    public Customer(String Address, String Telephone, String NIC, int ClosestWarehouseId, String Email, String Name, String Password, UserRole Role) {
        super(Email, Password, Role, Name);
        this.Address = Address;
        this.Telephone = Telephone;
        this.NIC = NIC;
        this.ClosestWarehouseId = ClosestWarehouseId;
    }  

    public Customer(String Address, String Telephone, String NIC, int ClosestWarehouseId, int Id, String Email, String Password, UserRole Role, String Name) {
        super(Email, Password, Role, Name);
        this.Id = Id;
        this.Address = Address;
        this.Telephone = Telephone;
        this.NIC = NIC;
        this.ClosestWarehouseId = ClosestWarehouseId;
    }

    public Customer(int Id, String Address, String Telephone, int ClosestWarehouseId, String Email, String Password, UserRole Role, String Name) {
        super(Email, Password, Role, Name);
        this.Id = Id;
        this.Address = Address;
        this.Telephone = Telephone;
        this.ClosestWarehouseId = ClosestWarehouseId;
    }
    
    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public int getClosestWarehouseId() {
        return ClosestWarehouseId;
    }

    public void setClosestWarehouseId(int ClosestWarehouseId) {
        this.ClosestWarehouseId = ClosestWarehouseId;
    }
    
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }     
}
