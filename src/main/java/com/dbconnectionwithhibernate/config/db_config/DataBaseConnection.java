package com.dbconnectionwithhibernate.config.db_config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseConnection {
    public static SessionFactory newFactory(Class Class) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Class)
                .buildSessionFactory();
        return factory;
    }

    public static Session newSession(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        return session;
    }
}
