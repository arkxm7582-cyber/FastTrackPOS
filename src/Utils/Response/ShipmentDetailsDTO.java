/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Response;

import java.sql.Timestamp;

public class ShipmentDetailsDTO {
    public int shipmentId;
    public String packageContent;
    public String status;
    public double price;
    public String address;
    public Timestamp pickupTime;
    public Timestamp deliveryTime;

    public String deliveryAgentName;
    public String pickupAgentName;
    public String customerEmail;
    public String pickupWarehouseName;
    public String deliveryWarehouseName;

}
