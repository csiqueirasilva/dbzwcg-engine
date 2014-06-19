/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.users.authority;

import com.dbzwcg.services.sql.ConnectionFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author csiqueira
 */
class AuthorityDAO {
    private ConnectionFactory cf;
    
    public AuthorityDAO() {
        this.cf = new ConnectionFactory();
    }
    
    static public void main (String args[]) {
            Authority a = new AuthorityDAO().getAuthorityByName(AuthorityType.ROLE_PLAYER.toString());
    }
    
    public Authority getAuthorityByName(String authorityName) {
        Authority a = null;
        
        try {
            if(authorityName != null) {
                this.cf.start();
                a = (Authority) this.cf.getEntityManager().createQuery("SELECT a FROM com.dbzwcg.users.authority.Authority a WHERE authority = :authority", Authority.class).setParameter("authority", authorityName).getSingleResult();
                this.cf.finish();
            }
        } catch (NoResultException e) {
            this.cf.finish();
        }
        
        return a;
    }
    
}
