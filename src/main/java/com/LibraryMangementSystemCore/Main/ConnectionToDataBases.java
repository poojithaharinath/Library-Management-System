package com.LibraryMangementSystemCore.Main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionToDataBases {
	private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate1.cfg.xml")
                    .addAnnotatedClass(BooksStore.class)
                    .addAnnotatedClass(Books.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
