/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enums.UserRole;

public class User {
    
    private int Id;
    private String Email;
    private String Password;
    private UserRole Role;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public User(String Email, String Password, UserRole Role, String Name) {
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.Name = Name;
    }

    public User(int Id, String Email, String Password, UserRole Role, String Name) {
        this.Id = Id;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.Name = Name;
    }
 
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public UserRole getRole() {
        return Role;
    }

    public void setRole(UserRole Role) {
        this.Role = Role;
    }
    
    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Email=" + Email + ", Password=" + Password + ", Role=" + Role + ", Name=" + Name + '}';
    }
}
