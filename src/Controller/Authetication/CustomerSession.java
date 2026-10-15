/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Authetication;

import Model.Entities.Customer;
import Model.Entities.User;

public class CustomerSession extends Session {

    public static void setCurrentCustomer(Customer customer) {
        setCurrentUser(customer);
    }

    public static Customer getCurrentCustomer() {
        User user = getCurrentUser();
        if (user instanceof Customer) {
            return (Customer) user;
        }
        return null;
    }

    public static void clearCustomerSession() {
        clearSession();
    }
}
