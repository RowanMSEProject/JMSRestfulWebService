/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.util.Collections;
import java.util.HashSet;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Central publishing station. Changes to all participating entities are
 * published by this class
 *
 * @author geebzter
 */
public class MessageSender {

    /** All entity classes that are participating 
     * in the pub/sub. 
     * 
     * NOTE: At present the collection of entity classes specified statically.
     * This can be changed to auto-discovery of all classes in a given package 
     * or collection of packages using reflection. Alternatively, this 
     * information can be injected.
     *  
     */
    final static Class[] publishedClasses
            = {
                entities.Certnames.class,
                entities.Emergencycontacts.class,
                entities.Emergencycontactsforuser.class,
                entities.Login.class,
                entities.Skills.class,
                entities.Skillsforusers.class,
                entities.Userroles.class};

    /**
     * Types of transactions that are published
     */
    public enum TransactionType {

        Create, Remove, Update
    };

    private HashSet<Class> classSet;
    private Publisher publisher; // Publishing mechanism to be used. 
                                 // TODO: inject here

    /** Default constructor
     *
     */
    public MessageSender() {
        init();
    }

    /**
     * Generate the list of classes participating in pub/sub
     *  and create publisher object
     */
    private void init() {
        classSet = new HashSet<>();
        Collections.addAll(classSet, publishedClasses);
        publisher = new JMSPublisher(); // TODO: inject instead.
    }

    /** Returns true for objects belonging to entity classes that
     *  are being published (i.e., participate in pub/sub), false otherwise
     */ 
    private boolean amServicing(Object entity) {
        return classSet.contains(entity.getClass());
    }

    @PostPersist
    /**
     * Callback for entity creation
     */
    public void entityCreated(Object entity) throws Exception {
        publish(entity, TransactionType.Create);
    }

    @PostUpdate
    /**
     * Callback for entity update
     */
    public void entityUpdated(Object entity) throws Exception {
        publish(entity, TransactionType.Update);
    }

    @PostRemove
    /**
     * Callback for entity removal
     */
    public void entityRemoved(Object entity) throws Exception {
        publish(entity, TransactionType.Remove);
    }

    /**
     * Publish the entity object if it belongs to the list 
     * of classes participating in pub/sub
     * 
     */
    private void publish(Object entity, TransactionType type) 
            throws Exception 
    {
        if (amServicing(entity)) {
            publisher.publish(entity, type);
        }
    }

}
