/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import model.Patients;

/**
 *
 * @author Tan Chek Wei
 */
@Entity
@Table(name = "SEEDOCTORQUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seedoctorqueue.findAll", query = "SELECT s FROM Seedoctorqueue s")
    , @NamedQuery(name = "Seedoctorqueue.findByQueueno", query = "SELECT s FROM Seedoctorqueue s WHERE s.queueno = :queueno")
    , @NamedQuery(name = "Seedoctorqueue.findByLevelofsickness", query = "SELECT s FROM Seedoctorqueue s WHERE s.levelofsickness = :levelofsickness")
    , @NamedQuery(name = "Seedoctorqueue.findByPriority", query = "SELECT s FROM Seedoctorqueue s WHERE s.priority = :priority")
    , @NamedQuery(name = "Seedoctorqueue.findByArrivaltime", query = "SELECT s FROM Seedoctorqueue s WHERE s.arrivaltime = :arrivaltime")})
public class Seedoctorqueue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUEUENO")
    private Integer queueno;
    @Column(name = "LEVELOFSICKNESS")
    private Integer levelofsickness;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRIORITY")
    private Double priority;
    @Column(name = "ARRIVALTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivaltime;
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENTID")
    @ManyToOne
    private Patients patientId;

    public Seedoctorqueue() {
    }

    public Seedoctorqueue(Integer queueno) {
        this.queueno = queueno;
    }

    public Integer getQueueno() {
        return queueno;
    }

    public void setQueueno(Integer queueno) {
        this.queueno = queueno;
    }

    public Integer getLevelofsickness() {
        return levelofsickness;
    }

    public void setLevelofsickness(Integer levelofsickness) {
        this.levelofsickness = levelofsickness;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Patients getPatientId() {
        return patientId;
    }

    public void setPatientId(Patients patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (queueno != null ? queueno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seedoctorqueue)) {
            return false;
        }
        Seedoctorqueue other = (Seedoctorqueue) object;
        if ((this.queueno == null && other.queueno != null) || (this.queueno != null && !this.queueno.equals(other.queueno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ADT.Seedoctorqueue[ queueno=" + queueno + " ]";
    }
    
}
