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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crouch
 */
@Entity
@Table(name = "SKILLSFORUSERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skillsforusers.findAll", query = "SELECT s FROM Skillsforusers s"),
    @NamedQuery(name = "Skillsforusers.findById", query = "SELECT s FROM Skillsforusers s WHERE s.id = :id"),
    @NamedQuery(name = "Skillsforusers.findBySkilllevel", query = "SELECT s FROM Skillsforusers s WHERE s.skilllevel = :skilllevel"),
    @NamedQuery(name = "Skillsforusers.findByCertdate", query = "SELECT s FROM Skillsforusers s WHERE s.certdate = :certdate")})
public class Skillsforusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SKILLLEVEL")
    private int skilllevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CERTDATE")
    private String certdate;
    @JoinColumn(name = "CERTID", referencedColumnName = "CERTID")
    @ManyToOne(optional = false)
    private Certnames certid;
    @JoinColumn(name = "SKILLSID", referencedColumnName = "SKILLSID")
    @ManyToOne(optional = false)
    private Skills skillsid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Login userid;

    public Skillsforusers() {
    }

    public Skillsforusers(Integer id) {
        this.id = id;
    }

    public Skillsforusers(Integer id, int skilllevel, String certdate) {
        this.id = id;
        this.skilllevel = skilllevel;
        this.certdate = certdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(int skilllevel) {
        this.skilllevel = skilllevel;
    }

    public String getCertdate() {
        return certdate;
    }

    public void setCertdate(String certdate) {
        this.certdate = certdate;
    }

    public Certnames getCertid() {
        return certid;
    }

    public void setCertid(Certnames certid) {
        this.certid = certid;
    }

    public Skills getSkillsid() {
        return skillsid;
    }

    public void setSkillsid(Skills skillsid) {
        this.skillsid = skillsid;
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
        if (!(object instanceof Skillsforusers)) {
            return false;
        }
        Skillsforusers other = (Skillsforusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Skillsforusers[ id=" + id + " ]";
    }
    
}
