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
@Table(name = "STAFFS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staffs.findAll", query = "SELECT s FROM Staffs s")
    , @NamedQuery(name = "Staffs.findByStaffid", query = "SELECT s FROM Staffs s WHERE s.staffid = :staffid")
    , @NamedQuery(name = "Staffs.findByStaffname", query = "SELECT s FROM Staffs s WHERE s.staffname = :staffname")
    , @NamedQuery(name = "Staffs.findByStaffposition", query = "SELECT s FROM Staffs s WHERE s.staffposition = :staffposition")
    , @NamedQuery(name = "Staffs.findByStaffic", query = "SELECT s FROM Staffs s WHERE s.staffic = :staffic")
    , @NamedQuery(name = "Staffs.findByStaffgender", query = "SELECT s FROM Staffs s WHERE s.staffgender = :staffgender")
    , @NamedQuery(name = "Staffs.findByStaffcontact", query = "SELECT s FROM Staffs s WHERE s.staffcontact = :staffcontact")
    , @NamedQuery(name = "Staffs.findByStaffemail", query = "SELECT s FROM Staffs s WHERE s.staffemail = :staffemail")
    , @NamedQuery(name = "Staffs.findByStaffaddress", query = "SELECT s FROM Staffs s WHERE s.staffaddress = :staffaddress")
    , @NamedQuery(name = "Staffs.findByStaffstatus", query = "SELECT s FROM Staffs s WHERE s.staffstatus = :staffstatus")
    , @NamedQuery(name = "Staffs.findByStafftype", query = "SELECT s FROM Staffs s WHERE s.stafftype = :stafftype")
    , @NamedQuery(name = "Staffs.findByStaffusername", query = "SELECT s FROM Staffs s WHERE s.staffusername = :staffusername")
    , @NamedQuery(name = "Staffs.findByStaffpassword", query = "SELECT s FROM Staffs s WHERE s.staffpassword = :staffpassword")})
public class Staffs implements Serializable {

    @Size(max = 50)
    @Column(name = "STAFFNAME")
    private String staffname;
    @Size(max = 6)
    @Column(name = "STAFFPOSITION")
    private String staffposition;
    @Size(max = 50)
    @Column(name = "STAFFIC")
    private String staffic;
    @Size(max = 50)
    @Column(name = "STAFFGENDER")
    private String staffgender;
    @Size(max = 50)
    @Column(name = "STAFFCONTACT")
    private String staffcontact;
    @Size(max = 50)
    @Column(name = "STAFFEMAIL")
    private String staffemail;
    @Size(max = 50)
    @Column(name = "STAFFADDRESS")
    private String staffaddress;
    @Size(max = 8)
    @Column(name = "STAFFSTATUS")
    private String staffstatus;
    @Size(max = 19)
    @Column(name = "STAFFTYPE")
    private String stafftype;
    @Size(max = 50)
    @Column(name = "STAFFUSERNAME")
    private String staffusername;
    @Size(max = 50)
    @Column(name = "STAFFPASSWORD")
    private String staffpassword;
    @OneToMany(mappedBy = "staffid")
    private Collection<Timeclock> timeclockCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STAFFID")
    private Integer staffid;

    public Staffs() {
    }

    public Staffs(Integer staffid) {
        this.staffid = staffid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffid != null ? staffid.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staffs)) {
            return false;
        }
        Staffs other = (Staffs) object;
        if ((this.staffid == null && other.staffid != null) || (this.staffid != null && !this.staffid.equals(other.staffid))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "model.Staffs[ staffid=" + staffid + " ]";
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public String getStaffposition() {
        return staffposition;
    }

    public void setStaffposition(String staffposition) {
        this.staffposition = staffposition;
    }

    public String getStaffic() {
        return staffic;
    }

    public void setStaffic(String staffic) {
        this.staffic = staffic;
    }

    public String getStaffgender() {
        return staffgender;
    }

    public void setStaffgender(String staffgender) {
        this.staffgender = staffgender;
    }

    public String getStaffcontact() {
        return staffcontact;
    }

    public void setStaffcontact(String staffcontact) {
        this.staffcontact = staffcontact;
    }

    public String getStaffemail() {
        return staffemail;
    }

    public void setStaffemail(String staffemail) {
        this.staffemail = staffemail;
    }

    public String getStaffaddress() {
        return staffaddress;
    }

    public void setStaffaddress(String staffaddress) {
        this.staffaddress = staffaddress;
    }

    public String getStaffstatus() {
        return staffstatus;
    }

    public void setStaffstatus(String staffstatus) {
        this.staffstatus = staffstatus;
    }

    public String getStafftype() {
        return stafftype;
    }

    public void setStafftype(String stafftype) {
        this.stafftype = stafftype;
    }

    public String getStaffusername() {
        return staffusername;
    }

    public void setStaffusername(String staffusername) {
        this.staffusername = staffusername;
    }

    public String getStaffpassword() {
        return staffpassword;
    }

    public void setStaffpassword(String staffpassword) {
        this.staffpassword = staffpassword;
    }

    @XmlTransient
    public Collection<Timeclock> getTimeclockCollection() {
        return timeclockCollection;
    }

    public void setTimeclockCollection(Collection<Timeclock> timeclockCollection) {
        this.timeclockCollection = timeclockCollection;
    }
    
}
