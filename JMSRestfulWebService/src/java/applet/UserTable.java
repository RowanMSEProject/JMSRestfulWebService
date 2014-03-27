/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applet;

/**
 *
 * @author Christopher Crouch
 */
public class UserTable extends DatabaseViewApplet {

    String source = "http://localhost:8080/JMSRestfulWebService/webresources/entities.login/users";
    String service = "entities.Login";
    String command = "Create";

    @Override
    public String getURL() {
        return source;
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
