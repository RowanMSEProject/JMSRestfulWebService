/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Central publishing station. Changes to all participating
 * entities are published by this class
 *
 * @author geebzter
 */

public class MessageSender {

    // All entity classes that are participating
    // in the pub/sub. 
    
    final static Class[] publishedClasses
            = {
                entities.Certnames.class,
                entities.Emergencycontacts.class,
                entities.Emergencycontactsforuser.class,
                entities.Login.class,
                entities.Skills.class,
                entities.Skillsforusers.class,
                entities.Userroles.class};

    HashSet<Class> classSet;

    public MessageSender() {
        init();

    }

    private void init() {
        classSet = new HashSet<Class>();
        Collections.addAll(classSet, publishedClasses);
    }

    // Returns true for objects belonging to 
    // entity classes that are being published, false otherwise
    private boolean amServicing(Object entity) {

        return classSet.contains(entity.getClass());

    }

    @PostPersist
    @PostRemove
    @PostUpdate
    /**
     * Callback for entity changes
     */
    public void entityUpdated(Object entity) throws JMSException, NamingException, IOException {
        if (amServicing(entity))
            Publisher.publish(entity);
     
    }
}
