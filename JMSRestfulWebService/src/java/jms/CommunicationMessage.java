/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jms;

import java.io.Serializable;

/**
 *
 * @author junxin
 * Simple representation of a JMS message
 */
public class CommunicationMessage implements Serializable{
    private String name;
    private String message;
   
    public CommunicationMessage(String name, String message)
    {
        this.name=name;
        this.message=message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
