/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.players;

import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.users.user.User;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author csiqueira
 */
public class PlayerDAO {

    private ConnectionFactory cf;

    PlayerDAO() {
        this.cf = new ConnectionFactory();
    }

    Player getPlayerFromEmail(String email) {
        this.cf.start();
        Player p = null;
        try {
            Query q = this.cf.getEntityManager().createQuery("SELECT p FROM com.dbzwcg.users.user.User p WHERE email = :email", User.class);
            User u = (User) q.setParameter("email", email).getSingleResult();

            q = this.cf.getEntityManager().createQuery("SELECT p FROM com.dbzwcg.players.Player p WHERE id_user = :id_user", Player.class);
            p = (Player) q.setParameter("id_user", u.getId()).getSingleResult();
        } catch (NoResultException e) {
        }
        this.cf.finish();

        return p;
    }

    void updatePoints (Integer playerId, Integer points) {
        
        if(playerId != null && points != null) {
            try {
                this.cf.start();
                
                Player p = this.cf.getEntityManager().find(Player.class, playerId);
                Integer playerPoints = p.getPoints();
                p.setPoints(playerPoints + points);
                this.cf.getEntityManager().persist(p);
                
                this.cf.finish();
            } catch (NoResultException e) {
                this.cf.finish();
            } catch (NullPointerException e) {
                this.cf.finish();
            }
        }
    }
    
    Player persistPlayerToDatabase(Player player) {

        if (player != null) {
            this.cf.start();
            this.cf.getEntityManager().persist(player);
            this.cf.finish();
        }

        return player;
    }
}
