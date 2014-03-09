/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import listener.MessageSender;

/**
 *
 * @author crouch
 */
@EntityListeners(MessageSender.class)
@Entity
@Table(name = "CERTNAMES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certnames.findAll", query = "SELECT c FROM Certnames c"),
    @NamedQuery(name = "Certnames.findByCertid", query = "SELECT c FROM Certnames c WHERE c.certid = :certid"),
    @NamedQuery(name = "Certnames.findByCertname", query = "SELECT c FROM Certnames c WHERE c.certname = :certname")})
public class Certnames implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CERTID")
    private Integer certid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CERTNAME")
    private String certname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "certid")
    private Collection<Skillsforusers> skillsforusersCollection;

    public Certnames() {
    }

    public Certnames(Integer certid) {
        this.certid = certid;
    }

    public Certnames(Integer certid, String certname) {
        this.certid = certid;
        this.certname = certname;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    public String getCertname() {
        return certname;
    }

    public void setCertname(String certname) {
        this.certname = certname;
    }

    @XmlTransient
    public Collection<Skillsforusers> getSkillsforusersCollection() {
        return skillsforusersCollection;
    }

    public void setSkillsforusersCollection(Collection<Skillsforusers> skillsforusersCollection) {
        this.skillsforusersCollection = skillsforusersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certid != null ? certid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certnames)) {
            return false;
        }
        Certnames other = (Certnames) object;
        if ((this.certid == null && other.certid != null) || (this.certid != null && !this.certid.equals(other.certid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Certnames[ certid=" + certid + " ]";
    }
    
}
