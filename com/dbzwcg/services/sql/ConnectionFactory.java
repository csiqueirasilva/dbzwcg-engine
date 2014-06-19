/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.services.sql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.hibernate.Transaction;

/**
 *
 * @author csiqueira
 */
public class ConnectionFactory {
    protected EntityManagerFactory emf;
    protected EntityManager em;

    public void start() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("dbzjpa");
            this.em = this.emf.createEntityManager();
            this.em.getTransaction().begin();
        }
    }

    public void finish() {
        if (this.emf != null) {
            EntityTransaction t = this.em.getTransaction();
            
            try {
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            } finally {
                this.em.close();
                this.emf.close();
            }
            this.em = null;
            this.emf = null;
        }
    }

    public EntityManager getEntityManager() {
        return this.em;
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }
}