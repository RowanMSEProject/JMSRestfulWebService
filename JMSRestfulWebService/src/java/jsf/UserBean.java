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
    List<Login> otherUsers;
    List<Userroles> roles;
    
    public UserBean(){
        user=new Login();
    }
    
    public void createUser() {
        lfr.createUsers(user);
        user=new Login();
    }
    
    public void updateUser(){
        lfr.updateUsers(user);
    }
    
    public void removeUser(){
       lfr.delete(user.getUserid().toString());
       user = new Login();
    }
    
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
    
    public List<Login> getUsers(){
        users=lfr.findAll();
        return users;
    }
    
    public List<SelectItem> getOtherUsers() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        users=lfr.findAll();
        for(Login l: users){
            items.add(new SelectItem(l.getUserid().toString(), l.getUsername()));
        }
        
        return items;
    }

    public void setUsers(List<Login> users) {
        this.users = users;
    }
    
    public void removeUser(int userid) {
        lfr.remove(userid);
    }
    
    public List<SelectItem> getRoles() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        roles=urfr.findAll();
        for(Userroles r: roles){
            items.add(new SelectItem(r.getRoleid().toString(), r.getDescription()));
        }
        
        return items;
    }
    
    public void setFields(){
        user=lfr.find(user.getUserid());
    }
}