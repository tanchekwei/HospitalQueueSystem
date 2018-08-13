/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tan Chek Wei
 */
@Entity
@Table(name = "TIMECLOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timeclock.findAll", query = "SELECT t FROM Timeclock t")
    , @NamedQuery(name = "Timeclock.findByTimeclockid", query = "SELECT t FROM Timeclock t WHERE t.timeclockid = :timeclockid")
    , @NamedQuery(name = "Timeclock.findByStaffid", query = "SELECT t FROM Timeclock t WHERE t.staffid = :staffid")
    , @NamedQuery(name = "Timeclock.findByTimeclocktype", query = "SELECT t FROM Timeclock t WHERE t.timeclocktype = :timeclocktype")
    , @NamedQuery(name = "Timeclock.findByTimeclocktime", query = "SELECT t FROM Timeclock t WHERE t.timeclocktime = :timeclocktime")})
public class Timeclock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TIMECLOCKID")
    private Integer timeclockid;
    @JoinColumn(name = "STAFFID")
    private Staffs staffid;
    @Column(name = "TIMECLOCKTYPE")
    private String timeclocktype;
    @Column(name = "TIMECLOCKTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar timeclocktime;

    public Timeclock() {
    }

    public Timeclock(Integer timeclockid) {
        this.timeclockid = timeclockid;
    }
    
    public Timeclock(Integer timeclockid, String timeclocktype, java.util.Calendar timeclocktime, Staffs staffid) {
        this.timeclockid = timeclockid;
        this.staffid = staffid;
        this.timeclocktype = timeclocktype;
        this.timeclocktime = timeclocktime;
    }
    
    public Timeclock(String timeclocktype, java.util.Calendar timeclocktime, Staffs staffid) {
        this.staffid = staffid;
        this.timeclocktype = timeclocktype;
        this.timeclocktime = timeclocktime;
    }

    public Integer getTimeclockid() {
        return timeclockid;
    }

    public void setTimeclockid(Integer timeclockid) {
        this.timeclockid = timeclockid;
    }

    public Staffs getStaffid() {
        return staffid;
    }

    public void setStaffid(Staffs staffid) {
        this.staffid = staffid;
    }

    public String getTimeclocktype() {
        return timeclocktype;
    }

    public void setTimeclocktype(String timeclocktype) {
        this.timeclocktype = timeclocktype;
    }

    public java.util.Calendar getTimeclocktime() {
        return timeclocktime;
    }

    public void setTimeclocktime(java.util.Calendar timeclocktime) {
        this.timeclocktime = timeclocktime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeclockid != null ? timeclockid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timeclock)) {
            return false;
        }
        Timeclock other = (Timeclock) object;
        if ((this.timeclockid == null && other.timeclockid != null) || (this.timeclockid != null && !this.timeclockid.equals(other.timeclockid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Timeclock[ timeclockid=" + timeclockid + " ]";
    }
    
}
