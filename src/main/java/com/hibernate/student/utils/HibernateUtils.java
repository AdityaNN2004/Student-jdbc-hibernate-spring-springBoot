package com.hibernate.student.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils implements AutoCloseable {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable th) {
			System.err.println("Error while Session Factory Creation");
			throw new ExceptionInInitializerError(th);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		try {
			if(sessionFactory !=null && !sessionFactory.isClosed()) {
				sessionFactory.close();
			}
		}catch(Throwable th) {
			System.err.println("Error while Closing the Hibernate Session");
			th.printStackTrace();
		}
	}

}
