package service;

import entities.BlockedAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.logging.Logger;

/**
 * Access Log Service
 */
public class BlockedAccessService implements BaseService<BlockedAccess> {

    private static final Logger log = Logger.getLogger(BlockedAccessService.class.getName());

    Session session;

    @Override
    public void save(BlockedAccess entity) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        log.info(entity.toString());
    }

    @Override
    public void update(BlockedAccess entity) {

    }

    @Override
    public BlockedAccess findById(Long id){
        session = HibernateUtil.getSessionFactory().openSession();
        BlockedAccess blockedAccess = session.find(BlockedAccess.class,id);
        session.close();
        return blockedAccess;

    }
}
