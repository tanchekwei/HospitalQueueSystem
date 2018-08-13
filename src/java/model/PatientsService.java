/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JT
 */
public class PatientsService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;
    
    public PatientsService(EntityManager em) {
        this.mgr = em;
    }
    
    public boolean addPatients(Patients patient) {
        mgr.persist(patient);
        return true;
    }
    
    public boolean updatePatients(Patients patient) {
        Patients tempPatient = findPatientsById(patient.getPatientid());
        if (tempPatient != null) {
            tempPatient.setPatientid(patient.getPatientid());
            tempPatient.setPatientic(patient.getPatientic());
            tempPatient.setPatientgender(patient.getPatientgender());
            tempPatient.setPatientname(patient.getPatientname());
            tempPatient.setPatientcontact(patient.getPatientcontact());
            tempPatient.setPatientaddress(patient.getPatientaddress());
            return true;
        }
        return false;
    }
    
    public Patients findPatientsById(Integer id) {
        Patients patient = mgr.find(Patients.class, id);
        return patient;
    }
    
    public List<Patients> findAll() {
        List<Patients> pList = mgr.createNamedQuery("Patients.findAll").getResultList();
        return pList;
    }
}
