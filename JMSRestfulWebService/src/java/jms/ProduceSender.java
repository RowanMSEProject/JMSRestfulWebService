/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import java.io.IOException;
import java.util.Properties;
import static javax.jms.JMSContext.AUTO_ACKNOWLEDGE;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author junxin
 */
public class ProduceSender {

    public static final String TOPIC = "jms/myTopic1";
    public static final String MESSAGE = "This is the test";
    public static final String NAME = "Jun";
            
    /**
     * @param args the command line arguments
     * For debugging
     */

    // Create a JMS topic connection and 
    // connect to GlassFish TOPIC
    public static void publish() 
            throws JMSException, NamingException, IOException 
    {
        // Get the TOPIC from local GlassFishServer
        Context initialContext = ProduceSender.getInitialContext();
        Topic topic = (Topic) initialContext.lookup(ProduceSender.TOPIC);
        
        // Get Connection TOPIC Factory 
        TopicConnectionFactory topicConnectionFactory = 
                (TopicConnectionFactory) initialContext.lookup("jms/myTopicFactory");
        
        // Connect to the Topic connection
        TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
        topicConnection.start();
        
        // Start a new session for the Topic connection
        TopicSession publishSession = topicConnection.createTopicSession(false, 
                AUTO_ACKNOWLEDGE);
        TopicPublisher topicPublisher = publishSession.createPublisher(topic);

        // Publish message to the Topic 
        ObjectMessage objectMessage = publishSession.createObjectMessage();
        objectMessage.setObject(new CommunicationMessage(
                ProduceSender.NAME, 
                ProduceSender.MESSAGE));
        topicPublisher.publish(objectMessage);
        
    }

    // Configure  properties for connecting
    // to GlassFish server
    public static Context getInitialContext() throws JMSException, NamingException 
    {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", 
                "com.sun.enterprise.naming.SerialInitContextFactory");
        properties.setProperty("java.naming.factory.url.pkgs", 
                "com.sun.enterprise.naming");
        properties.setProperty("java.naming.provider.url", 
                "iiop://localhost:3700");
        
        return new InitialContext(properties);
    }

}
