package com.project.sorryapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DbRunner {

    SessionFactory sessionFactory;
    Session session;


    public DbRunner() {
        Configuration con = new Configuration().configure().addAnnotatedClass(DbData.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

        sessionFactory = con.buildSessionFactory(reg);// = new AnnotationConfiguration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

//    public void crud() {
//
//        create(session);
//        read(session);
//
//        update(session);
//        read(session);
//
//        delete(session);
//        read(session);
//
//        session.close();
//    }

    private void delete() {
        System.out.println("Deleting mondeo record...");
        DbData data = (DbData) session.get(DbData.class, "data");

        session.beginTransaction();
        session.delete(data);
        session.getTransaction().commit();
    }

    protected void update() {
        System.out.println("Updating table...");
        DbData data = (DbData) session.get(DbData.class, "data");


        session.beginTransaction();
        session.saveOrUpdate(data);
        session.getTransaction().commit();
    }

    protected void create(String nameOfPlayer, int numSpacesMoved, int numSorries, int numPawnsStarted, int numPawnsHome) {
        System.out.println("Creating table record...");
        DbData data = new DbData();
//        data.setId((int)session.createQuery("select max(id) from game_data").list().get(0));
        data.setPlayer(nameOfPlayer);
        data.setMoved(numSpacesMoved);
        data.setSorries(numSorries);
        data.setStarted(numPawnsStarted);
        data.setHome(numPawnsHome);

        session.beginTransaction();
        session.save(data);
        session.getTransaction().commit();
    }

//    private void read() {
//        Query q = session.createQuery("select _car from Car _car");
//
//        List cars = q.list();
//
//        System.out.println("Reading car records...");
//        System.out.printf("%-30.30s  %-30.30s%n", "Model", "Price");
//        for (Car c : cars) {
//            System.out.printf("%-30.30s  %-30.30s%n", c.getModel(), c.getPrice());
//        }
//    }
}