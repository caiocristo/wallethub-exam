package service;

import entities.Scan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.logging.Logger;

/**
 * Scan Service
 */
public class ScanService implements BaseService<Scan> {

    private static final Logger log = Logger.getLogger(ScanService.class.getName());

    Session session;


    @Override
    public void save(Scan entity) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        log.info(entity.toString());
    }

    @Override
    public void update(Scan entity) {

    }

    @Override
    public Scan findById(Long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Scan scan = session.find(Scan.class,id);
        session.close();
        return scan;
    }
}
