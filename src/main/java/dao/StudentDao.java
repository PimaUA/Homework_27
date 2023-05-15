package dao;

import entity.Student;
import hibernate.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao implements Dao<Student> {

    SessionFactory sessionFactory = HibernateSession.getSessionFactory();

    @Override
    public Student getByID(int id) {
        return sessionFactory.openSession().get(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        return sessionFactory.openSession().createQuery("From Student", Student.class).list();
    }

    @Override
    public void add(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
        session.close();
    }
}
