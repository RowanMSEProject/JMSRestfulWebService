/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import entities.Login;
import entities.Userroles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author crouch
 */
@Stateless
@Path("entities.login")
public class LoginFacadeREST extends AbstractFacade<Login> {

    @PersistenceContext(unitName = "JMSRestfulWebServicePU")
    private EntityManager em;

    public LoginFacadeREST() {
        super(Login.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Login entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Login entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Login find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * delete a user from the database based off userID input
     *
     * @param userid
     */
    @POST
    @Path("/rm")
    @Consumes("application/x-www-form-urlencoded")
    public void delete(@FormParam("userid") String userid) {
        int id = Integer.parseInt(userid);
        super.remove(super.find(id));
    }
    
    /**
     * Create a table of information about users from Login table
     *
     * @return
     */
    @GET
    @Path("users/table")
    @Produces("text/html")
    public String getUsersTable() {
        if (em != null) {
            List<Login> results = em.createQuery("SELECT L FROM Login L").getResultList();
            String answer = "<h2> Users </h2>" + "<br><table border='1'> <tr>";
            answer = answer + "<th>USERID</th><th>FIRST NAME</th><th>LAST NAME</th><th>USERNAME</th><th>PASSWORD</th><th>ROLE</th></tr>";
            for (Login result : results) {
                answer = answer + "<tr><td>" + result.getUserid() + "</td>";
                answer = answer + "<td>" + result.getFirstname() + "</td>";
                answer = answer + "<td>" + result.getLastname() + "</td>";
                answer = answer + " <td>" + result.getUsername() + "</td>";
                answer = answer + " <td>" + result.getPassword() + "</td>";
                answer = answer + " <td>" + result.getRoleid().getDescription() + "</td></tr>";
            }
            answer = answer + "</table>";
            return answer;
        } else {
            throw new WebApplicationException(Response.status(400)
                    .entity("Null entity manager")
                    .build());
        }
    }
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Login> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Login> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Path("/create")
    @Consumes({"application/x-www-form-urlencoded", "application/xml", "application/json"})
    public void createUser(@FormParam("firstName") String firstName,
                           @FormParam("lastName") String lastName,
                           @FormParam("username") String username,
                           @FormParam("password") String password,
                           @FormParam("role") String role) {
            List<Login> users = em.createNamedQuery("Login.findAll").getResultList();
            int id = users.get(users.size() - 1).getUserid();
            id++;
            Login newUser = new Login(id, username, password, firstName, lastName);
            int roleID = Integer.parseInt(role);
            Userroles newRole = new Userroles(roleID);
            newUser.setRoleid(newRole);
            super.create(newUser);
    }
    
    @GET
    @Path("/users")
    @Consumes({"application/x-www-form-urlencoded", "application/xml", "application/json"})
    public String getUsers() {
        final String format = "%s\t\t%s\n";

        String answer = String.format(format, "Username", "Password");
        answer += "============================\n";

        List<Login> users = em.createNamedQuery("Login.findAll").getResultList();
        for (Login u : users) {
            answer += String.format(format, u.getUsername(), u.getPassword());
        }
        return answer;
    }
    
    public Login createUsers(Login user){
        em.persist(user);
        return user;
    }
    
    public Login updateUsers(Login user){
        em.refresh(user);
        return user;
    }
    
    public void removeUsers(Login user){
        super.remove(user);
    }

}
