/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applet;

/**
 * This is a class template that may be expanded in the future to include
 * additional functionality for dealing with the User Information table
 * in the database
 * 
 * @author Christopher Crouch
 */
public class UserEntityViewerApplet extends ViewerApplet {

    String url = "http://localhost:8080/JMSRestfulWebService/webresources/entities.login/users";
    String service = "entities.Login";
    String command = "Create";

    /**
     *************************** 
     * Accessor methods
     ***************************
     */
    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
