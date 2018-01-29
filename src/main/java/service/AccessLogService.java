package service;

import entities.AccessLog;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Access Log Service
 */
public class AccessLogService implements BaseService<AccessLog> {

    private static final Logger log = Logger.getLogger(AccessLogService.class.getName());

    Session session;


    @Override
    public void save(AccessLog entity) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        log.info(entity.toString());
    }

    @Override
    public void update(AccessLog entity) {

    }

    @Override
    public AccessLog findById(Long id) {

        session = HibernateUtil.getSessionFactory().openSession();
        AccessLog accessLog = session.find(AccessLog.class,id);
        session.close();
        return accessLog;
    }

    public List findByDateAndThreshold(Date startDate, Date endDate, long threshold){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select a.ip from AccessLog a where a.date > :startDate AND a.date < :endDate group by a.ip Having COUNT(*) > :threshold");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("threshold", threshold);
        return query.list();
    }
}
