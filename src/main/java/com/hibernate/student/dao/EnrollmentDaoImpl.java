package com.hibernate.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.student.entity.Enrollment;
import com.hibernate.student.utils.HibernateUtils;

public class EnrollmentDaoImpl implements EnrollmentDao {

	@Override
	public int addNewEnrollment(Enrollment enrollment) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(enrollment);
			tx.commit();
			return 1;
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Enrollment> findAll() {
		// 💡 FIXED: Simplified HQL query to return complete, managed Enrollment entity records
		String jpql = "FROM Enrollment e";
		List<Enrollment> enrollmentList = new ArrayList<>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			enrollmentList = session.createQuery(jpql, Enrollment.class).getResultList();
			tx.commit();
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace(); // Added stack trace back to assist debugging
		}
		return enrollmentList;
	}

	@Override
	public List<Enrollment> findEnrollmentByRollNo(String rollNo) {
		// 💡 FIXED: Fixed query mapping path to navigate through the student entity object safely
		String jpql = "FROM Enrollment e WHERE e.student.rollNo = :rollNo";
		List<Enrollment> enrollmentList = new ArrayList<>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			// 💡 FIXED: Explicitly added the bind parameter value to the engine execution loop
			enrollmentList = session.createQuery(jpql, Enrollment.class)
			                        .setParameter("rollNo", rollNo)
			                        .getResultList();
			tx.commit();
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return enrollmentList;
	}
}
