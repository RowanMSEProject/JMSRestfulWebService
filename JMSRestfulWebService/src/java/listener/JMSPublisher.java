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
 * JMS publishing mechanism
 *
 * @author crouch
 */
public class JMSPublisher implements Publisher {

    @Override
    public void publish(
            Object entity,
            MessageSender.TransactionType transactionType)
            throws JMSException, NamingException, IOException {

        ProduceSender.publish(entity.getClass().getName(), transactionType.toString());
        //for testing
        System.out.println(entity.getClass().getName());
        System.out.println(transactionType.toString());
        System.out.println("Publishing: " + entity + " changed ");
    }

}
