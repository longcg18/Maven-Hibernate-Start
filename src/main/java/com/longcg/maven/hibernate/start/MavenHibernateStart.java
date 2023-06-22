
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Student;
import utils.HibernateUtil;

import java.util.List;
/**
 *
 * @author Nguyen Long
 */
public class MavenHibernateStart {

    public static void main(String[] args) {
        Student student = new Student("Ramesh", "Fadatare", "rameshfadatare@javaguides.com");
        Student student1 = new Student("John", "Cena", "john@javaguides.com");
        Transaction transaction = null;

        // Query
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(student);
            session.persist(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        // Get Data
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List <Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
            session.getSessionFactory().close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
