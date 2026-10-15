/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Customer;

import Controller.Agent.AgentController;
import Controller.Authetication.CustomerSession;
import static Controller.Authetication.CustomerSession.getCurrentCustomer;
import Controller.Authetication.Session;
import Controller.Customer.CustomerController;
import Controller.Shipment.ShipmentController;
import Controller.Warehouse.WarehouseController;
import Model.Entities.Agent;
import Model.Entities.Customer;
import Model.Entities.Shipment;
import Model.Entities.User;
import Model.Enums.AgentStatus;
import Model.Enums.PackageStatus;
import Utils.Mail.SendMail;
import Utils.Response.InsertResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class CustomerSendPackage extends javax.swing.JFrame {

    List<String> PackageContents;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    AgentController a = new AgentController();
    WarehouseController w = new WarehouseController();
    CustomerController c = new CustomerController();
    ShipmentController shipmentController = new ShipmentController();

    public CustomerSendPackage() {
        initComponents();
        PackageContents = new ArrayList<>();
        listContent.setModel(listModel);
        pickupDate.setMinSelectableDate(new java.util.Date());
        
        List<String> districts = w.getAllDistricts();
        districtsCmb.removeAllItems();

        for (String district : districts) {
            districtsCmb.addItem(district);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deliverySlotDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pickupDate = new com.toedter.calendar.JDateChooser();
        proceedBtn = new javax.swing.JButton();
        deliveryDate = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listContent = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        itemTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        streetNumberTxt = new javax.swing.JTextField();
        cityNameTxt = new javax.swing.JTextField();
        streetNameTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        districtsCmb = new javax.swing.JComboBox();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Schedule Time");

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<html>Note:  We Take a buffer time of two weeks and then you can select your delivery time slot </html>");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel8.setText("Latest Delivery Date");

        jLabel9.setText("Latest Pickup Date");

        pickupDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pickupDatePropertyChange(evt);
            }
        });

        proceedBtn.setText("Proceed");
        proceedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pickupDate, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(deliveryDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(proceedBtn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(pickupDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(deliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(proceedBtn)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout deliverySlotDialogLayout = new javax.swing.GroupLayout(deliverySlotDialog.getContentPane());
        deliverySlotDialog.getContentPane().setLayout(deliverySlotDialogLayout);
        deliverySlotDialogLayout.setHorizontalGroup(
            deliverySlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliverySlotDialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        deliverySlotDialogLayout.setVerticalGroup(
            deliverySlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliverySlotDialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utils/Resources/p19-19-fastrack-1461198656 (2).png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Overall Content");

        jLabel7.setText("Price:");

        jScrollPane1.setViewportView(listContent);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(priceLbl)
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLbl)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Send Package");

        jLabel3.setText("Package Content");

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        confirmBtn.setText("Confirm Package Content");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        streetNumberTxt.setText("No 24 /7");

        cityNameTxt.setText("City Name");

        streetNameTxt.setText("Street Name");

        jLabel10.setText("Address:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(117, 117, 117)
                .addComponent(jLabel2)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(streetNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(itemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1))
                                .addComponent(streetNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(districtsCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cityNameTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(streetNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(streetNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cityNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(districtsCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("LIST" + itemTxt.getText());
        String item = itemTxt.getText().trim();

        if (!item.isEmpty()) {
            PackageContents.add(item);
            listModel.addElement(item);
            itemTxt.setText("");
        }
        
        int price = PackageContents.size() * 300;
        priceLbl.setText(price + "");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        // TODO add your handling code here:
        
        String streetName = streetNameTxt.getText().trim();
        String streetNumber = streetNumberTxt.getText().trim();
        String cityName = cityNameTxt.getText().trim();

        if (streetNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Street number cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!streetNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Street number must be numeric.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (streetName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Street name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cityName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (PackageContents == null || PackageContents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one package content item.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder packageContentFinal = new StringBuilder();
        for (String item : PackageContents) {
            packageContentFinal.append(item).append(" /*/ ");
        }

        if (packageContentFinal.length() >= 5) {
            packageContentFinal.setLength(packageContentFinal.length() - 5); // remove last " /*/ "
        }

        // Now you're good to go!

        
        for (String PackageContent : PackageContents) {
            packageContentFinal.append(PackageContent);
            packageContentFinal.append(" /*/ ");
        }
        
        deliverySlotDialog.pack();
        deliverySlotDialog.setLocationRelativeTo(this);
        deliverySlotDialog.setVisible(true);
        
        
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        listModel.clear();
        PackageContents.clear();
        int price = PackageContents.size() * 300;
        priceLbl.setText("");

    }//GEN-LAST:event_clearBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        CustomerDashboard cust = new CustomerDashboard();
        cust.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pickupDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pickupDatePropertyChange
        // TODO add your handling code here:
        java.util.Date selectedDate = pickupDate.getDate();
        if (selectedDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.add(Calendar.DAY_OF_MONTH, 14);
            Date minDate = cal.getTime();

            // Save this as the default selected date too
            deliveryDate.setDate(minDate);

            // Add 1 more day for max selectable date
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date maxDate = cal.getTime();

            // Set selectable range
            deliveryDate.setMinSelectableDate(minDate);

            // Set focus to the delivery date
            deliveryDate.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_pickupDatePropertyChange

    private void proceedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedBtnActionPerformed
        User user = Session.getCurrentUser();
        
        String address = streetNumberTxt.getText() + " " + streetNameTxt.getText() + " " + cityNameTxt.getText();

        int warehouseId = c.getClosestWarehouseForUser(user.getEmail());
        List<Agent> pickupAgents = a.getAgentsByWarehouseId(warehouseId);

        if (pickupAgents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Oops! There are no pickup agents available in your area.", "No Pickup Agents", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Agent pickupAgent = chooseDeliveryAgentId(pickupAgents);
        if (pickupAgent == null) {
            JOptionPane.showMessageDialog(this, "Oops! Could not assign a pickup agent.", "Pickup Agent Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String deliveryWarehouseLocation = districtsCmb.getSelectedItem().toString();
        List<Agent> deliveryAgents = a.getAgentsByDistrict(deliveryWarehouseLocation);

        if (deliveryAgents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Oops! There are no delivery agents available in the selected district.", "No Delivery Agents", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Agent deliveryAgent = chooseDeliveryAgentId(deliveryAgents);
        if (deliveryAgent == null) {
            JOptionPane.showMessageDialog(this, "Oops! Could not assign a delivery agent.", "Delivery Agent Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder packageContentFinal = new StringBuilder();
        for (String PackageContent : PackageContents) {
            packageContentFinal.append(PackageContent);
            packageContentFinal.append(" /*/ ");
        }

        String result = packageContentFinal.toString();
        
        int price = Integer.parseInt(priceLbl.getText().trim());
        
        Customer customer = c.getCustomerByUserId(user.getId());
        
        System.out.println("customer session " + getCurrentCustomer());

        Date pickupdate = pickupDate.getDate();
        Date deliverydate = deliveryDate.getDate();

        Shipment shipment = new Shipment(
            deliveryAgent.getId(),
            pickupAgent.getId(),
            deliveryAgent.getWarehouseId(),
            warehouseId,
            result,
            pickupdate,
            deliverydate,
            PackageStatus.ORDER_PLACED,
            price,
            address
        );
        

        String deliveryDateStr = new SimpleDateFormat("dd MMM yyyy").format(shipment.getDeliveryTime());
        String pickupDateStr = new SimpleDateFormat("dd MMM yyyy").format(shipment.getPickupTime());
        String content = shipment.getPackageContent();
        String address1 = shipment.getAddress();

        
        InsertResult res = shipmentController.insertShipment(shipment, customer.getId());
        
        String trackingNumber = String.valueOf(res.insertedId);
        
        String subject = "Shipment Created: Tracking #" + trackingNumber;

        String body = "Dear Customer,\n\n"
            + "Your shipment has been successfully created. Below are the details of your delivery:\n\n"
            + "📦 Tracking Number: " + trackingNumber + "\n"
            + "📍 Delivery Address: " + address1 + "\n"
            + "🚚 Pickup Date: " + pickupDateStr + "\n"
            + "🗓️ Estimated Delivery Date: " + deliveryDateStr + "\n"
            + "📃 Package Contents:\n" + content + "\n\n"
            + "You can use the tracking number to monitor the progress of your shipment.\n\n"
            + "Thank you for using our service!\n\n"
            + "Best regards,\n"
            + "Your Logistics Team";

        SendMail mail = new SendMail();
        mail.sendEmail(customer.getEmail(), "issurugayantha14@gmail.com", subject, body);
                
        deliverySlotDialog.dispose();
        // this.dispose();
        JOptionPane.showMessageDialog(this, "Shipment created successfully.");
    }//GEN-LAST:event_proceedBtnActionPerformed
      
    public Agent chooseDeliveryAgentId(List<Agent> agents) {

        // First, try to find an available agent
        for (Agent agent : agents) {
            if (agent.getAgentStatus() == AgentStatus.AVAILABLE) {
                return agent; // Assuming `getId()` returns the user's ID
            }
        }

        // If no available agent, choose one randomly
        if (!agents.isEmpty()) {
            Random random = new Random();
            Agent randomAgent = agents.get(random.nextInt(agents.size()));
            return randomAgent;
        }

        // If no agents at all found, handle accordingly (e.g., return -1 or throw an exception)
        return null;
    }

    
    public Agent selectDeliveryAgent(List<Agent> agents) {
        Agent fallbackBusyAgent = null;

        for (Agent agent : agents) {
            if (agent.getAgentStatus() == AgentStatus.AVAILABLE) {
                return agent;
            } else if (agent.getAgentStatus() == AgentStatus.BUSY && fallbackBusyAgent == null) {
                fallbackBusyAgent = agent;
            }
        }

        return fallbackBusyAgent;
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerSendPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerSendPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerSendPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerSendPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerSendPackage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cityNameTxt;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton confirmBtn;
    private com.toedter.calendar.JDateChooser deliveryDate;
    private javax.swing.JDialog deliverySlotDialog;
    private javax.swing.JComboBox districtsCmb;
    private javax.swing.JTextField itemTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listContent;
    private com.toedter.calendar.JDateChooser pickupDate;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JButton proceedBtn;
    private javax.swing.JTextField streetNameTxt;
    private javax.swing.JTextField streetNumberTxt;
    // End of variables declaration//GEN-END:variables
}
