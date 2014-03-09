/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crouch
 */
@Entity
@Table(name = "EMERGENCYCONTACTSFORUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergencycontactsforuser.findAll", query = "SELECT e FROM Emergencycontactsforuser e"),
    @NamedQuery(name = "Emergencycontactsforuser.findById", query = "SELECT e FROM Emergencycontactsforuser e WHERE e.id = :id")})
public class Emergencycontactsforuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "PERSONNELID", referencedColumnName = "PERSONNELID")
    @ManyToOne
    private Emergencycontacts personnelid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Login userid;

    public Emergencycontactsforuser() {
    }

    public Emergencycontactsforuser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Emergencycontacts getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(Emergencycontacts personnelid) {
        this.personnelid = personnelid;
    }

    public Login getUserid() {
        return userid;
    }

    public void setUserid(Login userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emergencycontactsforuser)) {
            return false;
        }
        Emergencycontactsforuser other = (Emergencycontactsforuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emergencycontactsforuser[ id=" + id + " ]";
    }
    
}
