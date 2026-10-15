/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.PackageStatus;
import java.util.Date;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class Shipment {

    private int Id;
    private int DeliveryAgentId;
    private int PickupAgentId;
    private int DeliveryWarehouseId;
    private int PickupWarehouseId;
    private String PackageContent;
    private Date PickupTime;
    private Date DeliveryTime;
    private PackageStatus PackageStatus;
    private int price;
    private String address;
    private boolean fromPickupWarehouse;
    private int customer_id;


    public Shipment(int DeliveryAgentId, int PickupAgentId, int DeliveryWarehouseId, int PickupWarehouseId, String PackageContent, Date PickupTime, Date DeliveryTime, PackageStatus PackageStatus, int price, String address) {
        this.DeliveryAgentId = DeliveryAgentId;
        this.PickupAgentId = PickupAgentId;
        this.DeliveryWarehouseId = DeliveryWarehouseId;
        this.PickupWarehouseId = PickupWarehouseId;
        this.PackageContent = PackageContent;
        this.PickupTime = PickupTime;
        this.DeliveryTime = DeliveryTime;
        this.PackageStatus = PackageStatus;
        this.price = price;
        this.address = address;
    }

    public Shipment(int Id, int DeliveryAgentId, int PickupAgentId, int DeliveryWarehouseId, int PickupWarehouseId, int CustomerId, String PackageContent, Date PickupTime, Date DeliveryTime, PackageStatus PackageStatus, int price, String address) {
        this.Id = Id;
        this.DeliveryAgentId = DeliveryAgentId;
        this.PickupAgentId = PickupAgentId;
        this.DeliveryWarehouseId = DeliveryWarehouseId;
        this.PickupWarehouseId = PickupWarehouseId;
        this.customer_id = CustomerId;
        this.PackageContent = PackageContent;
        this.PickupTime = PickupTime;
        this.DeliveryTime = DeliveryTime;
        this.PackageStatus = PackageStatus;
        this.price = price;
        this.address = address;
    }
    
    public Shipment(
        int PickupAgentId,
        int DeliveryAgentId,
        int PickupWarehouseId,
        int CustomerId,
        int DeliveryWarehouseId,
        String PackageContent,
        Date PickupTime,
        Date DeliveryTime,
        PackageStatus PackageStatus,
        int price,
        String address
    ) {
        this.PickupAgentId = PickupAgentId;
        this.DeliveryAgentId = DeliveryAgentId;
        this.PickupWarehouseId = PickupWarehouseId;
        this.customer_id = CustomerId;
        this.DeliveryWarehouseId = DeliveryWarehouseId;
        this.PackageContent = PackageContent;
        this.PickupTime = PickupTime;
        this.DeliveryTime = DeliveryTime;
        this.PackageStatus = PackageStatus;
        this.price = price;
        this.address = address;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getDeliveryAgentId() {
        return DeliveryAgentId;
    }

    public void setDeliveryAgentId(int DeliveryAgentId) {
        this.DeliveryAgentId = DeliveryAgentId;
    }

    public int getPickupAgentId() {
        return PickupAgentId;
    }

    public void setPickupAgentId(int PickupAgentId) {
        this.PickupAgentId = PickupAgentId;
    }

    public int getDeliveryWarehouseId() {
        return DeliveryWarehouseId;
    }

    public void setDeliveryWarehouseId(int DeliveryWarehouseId) {
        this.DeliveryWarehouseId = DeliveryWarehouseId;
    }

    public int getPickupWarehouseId() {
        return PickupWarehouseId;
    }

    public void setPickupWarehouseId(int PickupWarehouseId) {
        this.PickupWarehouseId = PickupWarehouseId;
    }

    public String getPackageContent() {
        return PackageContent;
    }

    public void setPackageContent(String PackageContent) {
        this.PackageContent = PackageContent;
    }

    public Date getPickupTime() {
        return PickupTime;
    }

    public void setPickupTime(Date PickupTime) {
        this.PickupTime = PickupTime;
    }

    public Date getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(Date DeliveryTime) {
        this.DeliveryTime = DeliveryTime;
    }

    public PackageStatus getPackageStatus() {
        return PackageStatus;
    }

    public void setPackageStatus(PackageStatus PackageStatus) {
        this.PackageStatus = PackageStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    public boolean isFromPickupWarehouse() {
        return fromPickupWarehouse;
    }

    public void setFromPickupWarehouse(boolean fromPickupWarehouse) {
        this.fromPickupWarehouse = fromPickupWarehouse;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    
    @Override
    public String toString() {
        return "Shipment{" + "Id=" + Id + ", DeliveryAgentId=" + DeliveryAgentId + ", PickupAgentId=" + PickupAgentId + ", DeliveryWarehouseId=" + DeliveryWarehouseId + ", PickupWarehouseId=" + PickupWarehouseId + ", PackageContent=" + PackageContent + ", PickupTime=" + PickupTime + ", DeliveryTime=" + DeliveryTime + ", PackageStatus=" + PackageStatus + ", price=" + price + '}';
    }
}
