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
public class TimeClockService {

    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public TimeClockService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addTimeClock(Timeclock timeClock) {
        mgr.persist(timeClock);
        return true;
    }

    public List<Object[]> findAll() {
        query = mgr.createNativeQuery("SELECT * FROM Timeclock");
        List itemList = query.getResultList();
        return itemList;
    }

    public List<Object[]> findToday(int staffID) {
        query = mgr.createNativeQuery("SELECT * FROM Timeclock WHERE staffID = " + staffID + 
                                      " AND DATE(TIMECLOCKTIME) = CURRENT_DATE");
        List itemList = query.getResultList();
        return itemList;
    }
    
    public List<Object[]> findTodayAll() {
        query = mgr.createNativeQuery("SELECT * FROM Timeclock WHERE DATE(TIMECLOCKTIME) = CURRENT_DATE");
        List itemList = query.getResultList();
        return itemList;
    }
}
