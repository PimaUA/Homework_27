package dao;

import entity.Student;
import hibernate.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StudentDaoTest {
    /*
     In this method, we need to test with two cases: In the exceptional
     case, we have to give an id which does not exist in the table.
     Consequently, the expected result is a null object.
     In the standard case, we have to give an existed id in the table.
     Therefore, the expected result is an
     object with full attribute values as the same as that in the table.
    */
    @Test
    public void getByID() {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getByID(15); //exception scenario
        Assert.assertNull(student);

        student = studentDao.getByID(2); //standard scenario
        Assert.assertNotNull(student);
        Assert.assertEquals(2, student.getId());
        Assert.assertEquals("Michael", student.getName());
        Assert.assertEquals("michael.gmail.com", student.getEmail());
    }

    /*
     In this method, we need
     to test the returned list is not null and its size
     is the same to the total number of students in the table.
    */
    @Test
    public void getAll() {
        StudentDao studentDao = new StudentDao();
        List<Student> actualListOfStudents = studentDao.getAll();
        Assert.assertNotNull(actualListOfStudents);
        Assert.assertEquals(11, actualListOfStudents.size());
    }

    /*
     In this method, we need to test the new
     inserted id (must be greater than the
     current maximal id in the table).
     And check whether the new inserted student in the table is
     identical to the original one.
   */
    @Test
    public void add() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Mike", "mike.gmail.com");
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        Assert.assertNotNull(student);
        Assert.assertTrue(10 < student.getId()); // test correct id

        Assert.assertEquals(11, studentDao.getAll().size()); // test all row in the table

        Student studentNewInserted = studentDao.getByID(11); //test the new inserted row
        Assert.assertEquals(student.getId(), studentNewInserted.getId());
        Assert.assertEquals(student.getName(), studentNewInserted.getName());
        Assert.assertEquals(student.getEmail(), studentNewInserted.getEmail());
        transaction.rollback();
        session.close();
    }

    /*
     In this method, we need to test
     in the case update a student, and then,
     get its information to test whether it is the same to the original object.
     */
    @Test
    public void update() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student(1, "Jim", "jim.gmail.com");
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student1 = new Student(1, "Jam", "jam.gmail.com");
        session.update(student1);

        Student studentUpdated = studentDao.getByID(student.getId()); //test the new updated row
        Assert.assertEquals(student.getId(), studentUpdated.getId());
        Assert.assertEquals(student.getName(), studentUpdated.getName());
        Assert.assertEquals(student.getEmail(), studentUpdated.getEmail());
        transaction.rollback();
        session.close();
    }

    /*
     In this method, we need to test the total
     number students remaining in the table (add and then delete it).
     And then, we have to test if the table with that id is still existent in the table.
    */
    @Test
    public void delete() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Jin", "jin.gmail.com");
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        session.delete(student);
        Assert.assertEquals(11, studentDao.getAll().size()); // test all row in the table

        Student studentAnother = studentDao.getByID(student.getId()); //test the new deleted row
        Assert.assertNull(studentAnother);
        transaction.rollback();
        session.close();
    }
}