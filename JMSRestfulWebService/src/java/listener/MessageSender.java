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

    // All entity classes that are participating
    // in the pub/sub. 
    // NOTE: At present this collection is statically specified.
    // This can be changed to auto-discovering all classes in a given package 
    // or a collection of packages using reflection. If in that case, the collection
    // of package names would be specified. Alternatively, this information can be injected.
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
     * Different types of transactions that are published
     */
    public enum TransactionType {

        Create, Remove, Update
    };

    HashSet<Class> classSet;
    Publisher publisher; // Publishing mechanism to be used. TODO: inject here

    public MessageSender() {
        init();

    }

    private void init() {
        classSet = new HashSet<Class>();
        Collections.addAll(classSet, publishedClasses);
        publisher = new JMSPublisher(); // TODO: inject instead.
    }

    // Returns true for objects belonging to 
    // entity classes that are being published, false otherwise
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

    private void publish(Object entity, TransactionType type) throws Exception {
        if (amServicing(entity)) {
            publisher.publish(entity, type);
        }

    }

}
