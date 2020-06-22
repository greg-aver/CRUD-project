package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;
import java.util.List;

public class UserHibernateDAO implements UserDAO<User> {

    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String hql = "From " + User.class.getSimpleName();
        return session.createQuery(hql).list();
    }

    @Override
    public User getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public void add(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
