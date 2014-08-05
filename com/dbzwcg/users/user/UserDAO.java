/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.users.user;

import com.dbzwcg.services.authentication.CustomUserDetails;
import com.dbzwcg.services.sql.ConnectionFactory;
import javax.persistence.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author csiqueira
 */
class UserDAO {

    private ConnectionFactory cf;

    UserDAO() {
        this.cf = new ConnectionFactory();
    }

    void persistUserToDatabase(User usr) {
        this.cf.start();
        this.cf.getEntityManager().merge(usr);
        this.cf.finish();
    }

    User getUserFromId(Integer id) {
        User u = null;
        
        try {
            this.cf.start();
            u = this.cf.getEntityManager().find(User.class, id);
            this.cf.finish();
        } catch (javax.persistence.NoResultException e) {
        }
        
        return u;
    }
    
    User getUserFromUsername(String email) {
        User u = null;

        try {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT u FROM com.dbzwcg.users.user.User u WHERE email = :email", User.class);
            u = (User) q.setParameter("email", email).getSingleResult();
            this.cf.finish();
        } catch (javax.persistence.NoResultException e) {
        }

        return u;
    }

    CustomUserDetails getUserDetails(String email) throws UsernameNotFoundException {
        User u = null;
        try {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT u FROM com.dbzwcg.users.user.User u WHERE email = :email", User.class);
            u = (User) q.setParameter("email", email).getSingleResult();
            this.cf.finish();
        } catch (javax.persistence.NoResultException e) {
        }

        CustomUserDetails cud = null;

        if (u != null && !u.getRoles().isEmpty()) {
            cud = new CustomUserDetails(u);
        }

        return cud;
    }
}