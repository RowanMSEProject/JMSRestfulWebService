/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import entities.Login;
import entities.Userroles;
import entities.service.LoginFacadeREST;
import entities.service.UserrolesFacadeREST;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

/**
 *
 * @author junxin
 */
@ManagedBean(name = "UserBean")
@RequestScoped
public class UserBean implements Serializable{
    @EJB
    UserrolesFacadeREST urfr;

    @EJB
    LoginFacadeREST lfr;
    
    Login user;
    List<Login> users;
    List<Userroles> roles;
    
    public UserBean(){
        
        user=new Login();
    }
    
    public String createUser() {
        user=lfr.createUsers(user);
        return showList();
    }
    
    public Userroles createRole(int roleID) {
        Userroles newRole = new Userroles(roleID);
        return newRole;
    }
    
    public String updateUser(){
        user=lfr.updateUsers(user);
        return showList();
    }
    
//    public String removeUser(){
//        user=lfr.removeUsers(user);
//       return showList();
//    }
    
    public String showList(){
        users=lfr.findAll();
        return "usersTable.xhtml";
    }

    public Login getUser() {
        return user;
    }
    
    public void setUser(Login user) {
        this.user = user;
    }

    public List<Login> getUsers() {
        return users;
    }

    public void setUsers(List<Login> users) {
        this.users = users;
    }
    
    public void removeUser(int userid) {
        lfr.remove(userid);
    }
    
    public List<Userroles> getRole() {
        return urfr.findAll();
    }
    
}