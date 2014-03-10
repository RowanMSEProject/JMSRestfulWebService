/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

import java.io.IOException;
import javax.jms.JMSException;
import javax.naming.NamingException;
import jms.ProduceSender;

/**
 *
 * @author crouch
 */
public class Publisher {
    
    public static void publish (Object entity, 
            MessageSender.TransactionType transactionType) throws JMSException, NamingException, IOException {
        // Stub
        // This method should delegate to the 
        // JMS publishing mechanism
        
        String channel = entity.getClass().getName();
        publish(channel, transactionType);
        System.out.println("Publishing: " + entity + " changed ");
    }
    
    private static void publish ( String channel, 
            MessageSender.TransactionType transactionType) {
      try {  
        ProduceSender.publish(channel, transactionType.toString());
      } catch (Exception e) {
          e.printStackTrace();
     }
        
    }
}
