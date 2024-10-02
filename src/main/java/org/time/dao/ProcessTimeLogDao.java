package org.time.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.time.model.ProcessTimeLog;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Repository
@Log4j2
public class ProcessTimeLogDao {

    @Autowired
    private SessionFactory sessionFactory;

    public ProcessTimeLog save(ProcessTimeLog processTimeLog) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.beginTransaction();
        s.persist(processTimeLog);
        s.flush();
        tr.commit();
        return processTimeLog;
    }

}
