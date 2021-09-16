package chinh.anh.service;

import chinh.anh.model.Customer;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerService1 {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    public void persist(final Customer customer) {
        entityManager.persist(customer);
    }
    public Customer findById(final int id) {
        return entityManager.find(Customer.class, id);
    }
    public void delete(final Customer customer) {
        entityManager.remove(customer);
    }
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }
}
