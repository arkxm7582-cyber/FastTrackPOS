/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.UserRole;

public class Warehouse extends User {

    private int Id;
    private String Address;
    private String District;
    
    public Warehouse(String Address, String District, String Email, String Password, UserRole Role, String Name) {
        super(Email, Password, Role, Name);
        this.Address = Address;
        this.District = District;
    }
    
    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
