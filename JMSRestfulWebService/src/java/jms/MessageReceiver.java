/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import applet.DatabaseViewApplet;
import applet.RolesTable;
import applet.UserTable;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author junxin Implementation of JMS subscriber/listener for publisher
 * messages
 */
public class MessageReceiver implements MessageListener, ExceptionListener {

    public static final String TOPIC = "jms/myTopic1";

    /**
     * @param args the command line arguments
     */
    public MessageReceiver() throws JMSException, NamingException, IOException {
        // MessageReceiver mr=new MessageReceiver();

        // Get TOPIC
        Context initialContext = MessageReceiver.getInitialContext();
        Topic topic = (Topic) initialContext.lookup(ProduceSender.TOPIC);

        // Create Topic Connection
        TopicConnectionFactory topicConnectionFactory
                = (TopicConnectionFactory) initialContext.lookup("jms/myTopicFactory");
        TopicConnection topicConnection
                = topicConnectionFactory.createTopicConnection();

        // Subscribe for topic notification
        subscribe(topicConnection, topic);
        topicConnection.start();

    }

    //MessageReceiverApplet applet;
    DatabaseViewApplet applets;

    public void setApplet(DatabaseViewApplet applet) {
        this.applets = applet;
    }

    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            CommunicationMessage communicationMessage = (CommunicationMessage) objectMessage.getObject();
            String name = communicationMessage.getName();
            String sentMessage = communicationMessage.getMessage();
            applets.refresh(name, sentMessage);
      
        } catch (JMSException ex) {
            Logger.getLogger(ProduceSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subscribe(TopicConnection topicConnection, Topic topic)
            throws JMSException, IOException {
        TopicSession subscribeSession
                = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber topicSubscriber
                = subscribeSession.createSubscriber(topic);

        topicSubscriber.setMessageListener(this);
        topicConnection.setExceptionListener(this);

        /*
         BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
         String message=null;
         while(true){
         message=reader.readLine();
         if(message.equalsIgnoreCase("exit")){
         topicConnection.close();
         System.exit(0);
         }
         }
         */
    }

    public void onException(JMSException exception) {
        //applet.print("something bad happended: " + exception);
    }

    public static Context getInitialContext() throws JMSException, NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        properties.setProperty("java.naming.provider.url", "iiop://localhost:3700");
        return new InitialContext(properties);
    }
}
