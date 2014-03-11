/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

/**
 *
 * @author geebzter
 */
public interface Publisher {

    /**
     *
     * @param entity The entity that is to be published
     * @param transactionType THe type of transaction that the entity is
     * involved in (create, update, delete)
     * @throws Exception
     */
    public void publish(Object entity, MessageSender.TransactionType transactionType) throws Exception;
}
