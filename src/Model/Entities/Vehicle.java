/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.VehicleType;

public class Vehicle {
    private int Id;
    private String Model;
    private String NumberPlate;
    private VehicleType VehicleType;

    public Vehicle(String Model, String NumberPlate, VehicleType VehicleType) {
        this.Model = Model;
        this.NumberPlate = NumberPlate;
        this.VehicleType = VehicleType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getNumberPlate() {
        return NumberPlate;
    }

    public void setNumberPlate(String NumberPlate) {
        this.NumberPlate = NumberPlate;
    }

    public VehicleType getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(VehicleType VehicleType) {
        this.VehicleType = VehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "Id=" + Id + ", Model=" + Model + ", NumberPlate=" + NumberPlate + ", VehicleType=" + VehicleType + '}';
    }
}
