/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.players;

import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.users.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author csiqueira
 */

@Entity(name = "com.dbzwcg.players.Player")
@Table(name = "players")
@PrimaryKeyJoinColumn(name="id_user", referencedColumnName="ID")
public class Player extends User {

    public static void main (String args[]) {
        ConnectionFactory cf = new ConnectionFactory();
        cf.start();
        Player p = new Player();
        p.setDisplayName("OAJPSD");
        p.setPassword(Long.toString(System.currentTimeMillis()));
        p.setEmail(Long.toString(System.currentTimeMillis()));
        p.setPoints(1000);
        p.setEnable(true);
        cf.getEntityManager().persist(p);
        cf.finish();
    }

    @Column(name = "POINTS")
    protected Integer points;

    @Column(name = "DISPLAYNAME")
    protected String displayName;


    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getPassword() {
        return "SECURED";
    }
    
    @Override
    public String getEmail() {
        return "SECURED";
    }
}