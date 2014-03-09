/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crouch
 */
@Entity
@Table(name = "EMERGENCYCONTACTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergencycontacts.findAll", query = "SELECT e FROM Emergencycontacts e"),
    @NamedQuery(name = "Emergencycontacts.findByPersonnelid", query = "SELECT e FROM Emergencycontacts e WHERE e.personnelid = :personnelid"),
    @NamedQuery(name = "Emergencycontacts.findByContact", query = "SELECT e FROM Emergencycontacts e WHERE e.contact = :contact"),
    @NamedQuery(name = "Emergencycontacts.findByRelationship", query = "SELECT e FROM Emergencycontacts e WHERE e.relationship = :relationship"),
    @NamedQuery(name = "Emergencycontacts.findByStreet", query = "SELECT e FROM Emergencycontacts e WHERE e.street = :street"),
    @NamedQuery(name = "Emergencycontacts.findByCity", query = "SELECT e FROM Emergencycontacts e WHERE e.city = :city"),
    @NamedQuery(name = "Emergencycontacts.findByStatecode", query = "SELECT e FROM Emergencycontacts e WHERE e.statecode = :statecode"),
    @NamedQuery(name = "Emergencycontacts.findByPostalcode", query = "SELECT e FROM Emergencycontacts e WHERE e.postalcode = :postalcode"),
    @NamedQuery(name = "Emergencycontacts.findByPhone", query = "SELECT e FROM Emergencycontacts e WHERE e.phone = :phone")})
public class Emergencycontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PERSONNELID")
    private Integer personnelid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CONTACT")
    private String contact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RELATIONSHIP")
    private String relationship;
    @Size(max = 255)
    @Column(name = "STREET")
    private String street;
    @Size(max = 100)
    @Column(name = "CITY")
    private String city;
    @Size(max = 2)
    @Column(name = "STATECODE")
    private String statecode;
    @Size(max = 5)
    @Column(name = "POSTALCODE")
    private String postalcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 12)
    @Column(name = "PHONE")
    private String phone;
    @OneToMany(mappedBy = "personnelid")
    private Collection<Emergencycontactsforuser> emergencycontactsforuserCollection;

    public Emergencycontacts() {
    }

    public Emergencycontacts(Integer personnelid) {
        this.personnelid = personnelid;
    }

    public Emergencycontacts(Integer personnelid, String contact, String relationship) {
        this.personnelid = personnelid;
        this.contact = contact;
        this.relationship = relationship;
    }

    public Integer getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(Integer personnelid) {
        this.personnelid = personnelid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlTransient
    public Collection<Emergencycontactsforuser> getEmergencycontactsforuserCollection() {
        return emergencycontactsforuserCollection;
    }

    public void setEmergencycontactsforuserCollection(Collection<Emergencycontactsforuser> emergencycontactsforuserCollection) {
        this.emergencycontactsforuserCollection = emergencycontactsforuserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personnelid != null ? personnelid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emergencycontacts)) {
            return false;
        }
        Emergencycontacts other = (Emergencycontacts) object;
        if ((this.personnelid == null && other.personnelid != null) || (this.personnelid != null && !this.personnelid.equals(other.personnelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emergencycontacts[ personnelid=" + personnelid + " ]";
    }
    
}
