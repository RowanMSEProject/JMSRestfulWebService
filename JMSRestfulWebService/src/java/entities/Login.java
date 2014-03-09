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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "LOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByUserid", query = "SELECT l FROM Login l WHERE l.userid = :userid"),
    @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username"),
    @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"),
    @NamedQuery(name = "Login.findByFirstname", query = "SELECT l FROM Login l WHERE l.firstname = :firstname"),
    @NamedQuery(name = "Login.findByLastname", query = "SELECT l FROM Login l WHERE l.lastname = :lastname"),
    @NamedQuery(name = "Login.findByMiddleinitial", query = "SELECT l FROM Login l WHERE l.middleinitial = :middleinitial"),
    @NamedQuery(name = "Login.findBySsnum", query = "SELECT l FROM Login l WHERE l.ssnum = :ssnum"),
    @NamedQuery(name = "Login.findByBirth", query = "SELECT l FROM Login l WHERE l.birth = :birth"),
    @NamedQuery(name = "Login.findByGender", query = "SELECT l FROM Login l WHERE l.gender = :gender"),
    @NamedQuery(name = "Login.findByServicedate", query = "SELECT l FROM Login l WHERE l.servicedate = :servicedate"),
    @NamedQuery(name = "Login.findByStreet", query = "SELECT l FROM Login l WHERE l.street = :street"),
    @NamedQuery(name = "Login.findByCity", query = "SELECT l FROM Login l WHERE l.city = :city"),
    @NamedQuery(name = "Login.findByStatecode", query = "SELECT l FROM Login l WHERE l.statecode = :statecode"),
    @NamedQuery(name = "Login.findByPostalcode", query = "SELECT l FROM Login l WHERE l.postalcode = :postalcode"),
    @NamedQuery(name = "Login.findByMobilephone", query = "SELECT l FROM Login l WHERE l.mobilephone = :mobilephone"),
    @NamedQuery(name = "Login.findByWorkphone", query = "SELECT l FROM Login l WHERE l.workphone = :workphone"),
    @NamedQuery(name = "Login.findByHomephone", query = "SELECT l FROM Login l WHERE l.homephone = :homephone")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 1)
    @Column(name = "MIDDLEINITIAL")
    private String middleinitial;
    @Size(max = 11)
    @Column(name = "SSNUM")
    private String ssnum;
    @Size(max = 10)
    @Column(name = "BIRTH")
    private String birth;
    @Size(max = 1)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 10)
    @Column(name = "SERVICEDATE")
    private String servicedate;
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
    @Size(max = 12)
    @Column(name = "MOBILEPHONE")
    private String mobilephone;
    @Size(max = 20)
    @Column(name = "WORKPHONE")
    private String workphone;
    @Size(max = 12)
    @Column(name = "HOMEPHONE")
    private String homephone;
    @OneToMany(mappedBy = "userid")
    private Collection<Emergencycontactsforuser> emergencycontactsforuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Skillsforusers> skillsforusersCollection;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")
    @ManyToOne
    private Userroles roleid;

    public Login() {
    }

    public Login(Integer userid) {
        this.userid = userid;
    }

    public Login(Integer userid, String username, String password, String firstname, String lastname) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddleinitial() {
        return middleinitial;
    }

    public void setMiddleinitial(String middleinitial) {
        this.middleinitial = middleinitial;
    }

    public String getSsnum() {
        return ssnum;
    }

    public void setSsnum(String ssnum) {
        this.ssnum = ssnum;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getServicedate() {
        return servicedate;
    }

    public void setServicedate(String servicedate) {
        this.servicedate = servicedate;
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

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    @XmlTransient
    public Collection<Emergencycontactsforuser> getEmergencycontactsforuserCollection() {
        return emergencycontactsforuserCollection;
    }

    public void setEmergencycontactsforuserCollection(Collection<Emergencycontactsforuser> emergencycontactsforuserCollection) {
        this.emergencycontactsforuserCollection = emergencycontactsforuserCollection;
    }

    @XmlTransient
    public Collection<Skillsforusers> getSkillsforusersCollection() {
        return skillsforusersCollection;
    }

    public void setSkillsforusersCollection(Collection<Skillsforusers> skillsforusersCollection) {
        this.skillsforusersCollection = skillsforusersCollection;
    }

    public Userroles getRoleid() {
        return roleid;
    }

    public void setRoleid(Userroles roleid) {
        this.roleid = roleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Login[ userid=" + userid + " ]";
    }
    
}
