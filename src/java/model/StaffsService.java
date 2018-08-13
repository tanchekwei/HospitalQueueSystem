/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tan Chek Wei
 */
public class StaffsService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public StaffsService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addStaff(Staffs staff) {
        mgr.persist(staff);
        return true;
    }
    
    public List<Object[]> findAll() {
        query = mgr.createNativeQuery("SELECT * FROM Staffs");
        List itemList = query.getResultList();
        return itemList;
    }
    
    public List<Object[]> findEmployed() {
        query = mgr.createNativeQuery("SELECT * FROM Staffs WHERE staffStatus = 'employed'");
        List itemList = query.getResultList();
        return itemList;
    }
    
}
