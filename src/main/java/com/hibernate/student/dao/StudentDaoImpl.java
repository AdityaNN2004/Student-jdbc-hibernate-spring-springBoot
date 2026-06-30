package com.hibernate.student.dao;

import static com.hibernate.student.utils.HibernateUtils.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import com.hibernate.student.entity.Student;
import com.hibernate.student.utils.HibernateUtils;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        // 💡 FIXED: Simply select the entity. HQL automatically fetches mapped fields into Student objects.
        String hql = "from Student s where s.isDeleted = false"; 
        try {
            studentList = session.createQuery(hql, Student.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findStudentByRollNo(String rollNo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Student student = null;
        try {
            // 💡 FIXED: Replaced session.find() with an HQL query to look up by rollNo instead of the numeric primary key ID
            student = session.createQuery("FROM Student WHERE rollNo = :rollNo", Student.class)
                             .setParameter("rollNo", rollNo)
                             .uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Database Error:");
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public int removeStudent(String rollNo) {
        int rowsAffected = 0;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        // 💡 FIXED: Corrected SQL syntax mapping (added space and fixed the assignment operator near :rollNo)
        String sql = "UPDATE students SET is_deleted = 1, updated_on = NOW() WHERE roll_no = :rollNo";
        try {
            @SuppressWarnings({ "rawtypes", "deprecation" })
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("rollNo", rollNo);
            rowsAffected = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Database Error:");
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int editStudentDetails(Student s, String rollNo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        int result = 0;
        try {
            // 💡 FIX NOTE: If you haven't explicitly set up @NaturalId on your entity field, 
            // use an HQL query here instead just like findStudentByRollNo.
            Student oldStudent = session.createQuery("FROM Student WHERE rollNo = :rollNo", Student.class)
                                        .setParameter("rollNo", rollNo)
                                        .uniqueResult();
            if (oldStudent != null) {
                oldStudent.setRollNo(s.getRollNo());
                oldStudent.setFirstName(s.getFirstName());
                oldStudent.setLastName(s.getLastName());
                oldStudent.setEmail(s.getEmail());
                oldStudent.setDob(s.getDob());
                oldStudent.setAddress(s.getAddress());
                oldStudent.setGpa(s.getGpa());
                oldStudent.setGender(s.getGender());
                oldStudent.setEnrollmentDate(s.getEnrollmentDate());
                result = 1; // Mark success
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Database Error");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addNewStudent(Student stud) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(stud);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Database Error");
            e.printStackTrace();
        }
        return 0;
    }
}
