package com.hibernate.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.hibernate.student.entity.Course;
import com.hibernate.student.utils.HibernateUtils;

public class CourseDaoImpl implements CourseDao {

	@Override
	public Course findCourseById(String courseCode) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Course c  = null;
		try {
			// 💡 FIXED: Replaced session.find() with an HQL query because courseCode is not your table's numerical Primary Key.
			c = session.createQuery("FROM Course WHERE courseCode = :courseCode", Course.class)
			           .setParameter("courseCode", courseCode)
			           .uniqueResult();
			tx.commit();
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database ERROR");
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public int removeCourse(String courseCode) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		int rowsAffected = 0;
		try {
			// 💡 FIXED: Changed target to the actual database table name 'courses' for native queries.
			// Fixed typo ':courceCode' -> ':courseCode' and explicitly set the named parameter on the query object.
			String sql = "UPDATE courses SET is_active = 0, updated_on = NOW() WHERE course_code = :courseCode";
			@SuppressWarnings({"rawtypes", "deprecation"})
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("courseCode", courseCode);
			
			rowsAffected = query.executeUpdate();
			tx.commit();
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int updateCourse(Course c, String courseCode) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			// 💡 FIXED: Switched to HQL query strategy to mirror findCourseById, 
			// providing consistency in case @NaturalId is not explicitly defined in your entity.
			Course oldCourse = session.createQuery("FROM Course WHERE courseCode = :courseCode", Course.class)
			                          .setParameter("courseCode", courseCode)
			                          .uniqueResult();
			if(oldCourse != null) {
				oldCourse.setCourseCode(c.getCourseCode());
				oldCourse.setCourseName(c.getCourseName());
				oldCourse.setDepartment(c.getDepartment());
				oldCourse.setCredits(c.getCredits());
				tx.commit();
				return 1;
			}
			tx.commit(); // Commit anyway if not found to release connection
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Course> findAll() {
		List<Course> courseList = new ArrayList<>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 💡 FIXED: Simplified HQL. Writing 'select c.courseCode...' instructs Hibernate to map raw properties into an Object array 
		// instead of generating structured 'Course' entity instances, which triggers a TypeMismatch exception.
		String jpql = "FROM Course c WHERE c.isActive = true";
		try {
			courseList = session.createQuery(jpql, Course.class).getResultList();
			tx.commit();
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database ERROR");
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public int addNewCourse(Course c) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(c);
			tx.commit();
			return 1;
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return 0;
	}
}
