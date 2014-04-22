/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import entities.Userroles;
import java.util.ArrayList;
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

/**
 *
 * @author crouch
 */
@Stateless
@Path("entities.userroles")
public class UserrolesFacadeREST extends AbstractFacade<Userroles> {

    @PersistenceContext(unitName = "JMSRestfulWebServicePU")
    private EntityManager em;

    public UserrolesFacadeREST() {
        super(Userroles.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Userroles entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Userroles entity) {
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
    public Userroles find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces("text/plain")
    public List<Userroles> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Userroles> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("/list")
    @Produces("text/html")
    public String findRoles() {
        List<Userroles> results = em.createQuery("SELECT U FROM Userroles U").getResultList();
        String answer = "<select name=\"role\"> ";
        for (Userroles result : results) {
            answer = answer + "<option value=\"" + result.getRoleid() + "\">" + result.getDescription() + "</option>";
        }
        answer = answer + "</select>";
        return answer;
    }
    
    @POST
    @Path("/create")
    @Consumes({"application/x-www-form-urlencoded", "application/xml", "application/json"})
    public void createRole(@FormParam("role") String role) {
            List<Userroles> roles = em.createNamedQuery("Userroles.findAll").getResultList();
            int id = roles.get(roles.size() - 1).getRoleid();
            id++;
            Userroles newRole = new Userroles(id, role);
            super.create(newRole);
    }
    
    @GET
    @Path("/roles")
    @Consumes({"application/x-www-form-urlencoded", "application/xml", "application/json"})
    public String getRoles() {
        final String format = "%s\t\t%s\n";

        String answer = String.format(format, "ID", "Description");
        answer += "============================\n";

        List<Userroles> roles = em.createNamedQuery("Userroles.findAll").getResultList();
        for (Userroles u : roles) {
            answer += String.format(format, u.getRoleid(), u.getDescription());
        }
        return answer;
    }
    
    @GET
    @Produces("text/plain")
    public List<Integer> findroleIDs() {
        List<Userroles> temp = super.findAll();
        List<Integer> ids = new ArrayList<Integer>();
        for(Userroles u : temp) {
            ids.add(u.getRoleid());
        }
        return ids;
    }
    
    @GET
    @Produces("text/plain")
    public List<String> findroleNames() {
        List<Userroles> temp = super.findAll();
        List<String> ids = new ArrayList<String>();
        for(Userroles u : temp) {
            ids.add(u.getDescription());
        }
        return ids;
    }

}
