/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Tan Chek Wei
 */
@Entity
@Table(name = "PATIENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patients.findAll", query = "SELECT p FROM Patients p")
    , @NamedQuery(name = "Patients.findByPatientid", query = "SELECT p FROM Patients p WHERE p.patientid = :patientid")
    , @NamedQuery(name = "Patients.findByPatientname", query = "SELECT p FROM Patients p WHERE p.patientname = :patientname")
    , @NamedQuery(name = "Patients.findByPatientic", query = "SELECT p FROM Patients p WHERE p.patientic = :patientic")
    , @NamedQuery(name = "Patients.findByPatientgender", query = "SELECT p FROM Patients p WHERE p.patientgender = :patientgender")
    , @NamedQuery(name = "Patients.findByPatientcontact", query = "SELECT p FROM Patients p WHERE p.patientcontact = :patientcontact")
    , @NamedQuery(name = "Patients.findByPatientaddress", query = "SELECT p FROM Patients p WHERE p.patientaddress = :patientaddress")})
public class Patients implements Serializable {

    @Size(max = 50)
    @Column(name = "PATIENTNAME")
    private String patientname;
    @Size(max = 50)
    @Column(name = "PATIENTIC")
    private String patientic;
    @Size(max = 50)
    @Column(name = "PATIENTGENDER")
    private String patientgender;
    @Size(max = 50)
    @Column(name = "PATIENTCONTACT")
    private String patientcontact;
    @Size(max = 50)
    @Column(name = "PATIENTADDRESS")
    private String patientaddress;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PATIENTID")
    private Integer patientid;

    public Patients() {
    }
    
    public Patients(Integer patientid) {
        this.patientid = patientid;
    }

    public Patients(Integer patientid, String patientname, String patientic, String patientgender, String patientcontact, String patientaddress) {
        this.patientid = patientid;
        this.patientname = patientname;
        this.patientic = patientic;
        this.patientgender = patientgender;
        this.patientcontact = patientcontact;
        this.patientaddress = patientaddress;
    }
    
    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientid != null ? patientid.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patients)) {
            return false;
        }
        Patients other = (Patients) object;
        if ((this.patientid == null && other.patientid != null) || (this.patientid != null && !this.patientid.equals(other.patientid))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "model.Patients[ patientid=" + patientid + " ]";
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientic() {
        return patientic;
    }

    public void setPatientic(String patientic) {
        this.patientic = patientic;
    }

    public String getPatientgender() {
        return patientgender;
    }

    public void setPatientgender(String patientgender) {
        this.patientgender = patientgender;
    }

    public String getPatientcontact() {
        return patientcontact;
    }

    public void setPatientcontact(String patientcontact) {
        this.patientcontact = patientcontact;
    }

    public String getPatientaddress() {
        return patientaddress;
    }

    public void setPatientaddress(String patientaddress) {
        this.patientaddress = patientaddress;
    }
    
}
