/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

/**
 *
 * @author crouch
 */
public class Publisher {
    
    public static void publish (Object entity) {
        // Stub
        // This method should delegate to the 
        // JMS publishing mechanism
        System.out.println("Publishing: " + entity + " changed ");
    }
}
