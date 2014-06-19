/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.players;

import com.dbzwcg.users.user.User;
import com.dbzwcg.users.user.UserDLO;
import java.util.regex.Pattern;

/**
 *
 * @author csiqueira
 */
public class PlayerDLO {

    public static void main (String args[]) {
        Player p = new Player();
        p.setDisplayName("ALeluia");
        p.setPassword("poipoi09");
        p.setEmail("a@a.com");
        p.setPoints(0);
        registerUser(p);
    }
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static void updatePoints(Integer playerId, Integer points) {
        new PlayerDAO().updatePoints(playerId, points);
    }
    
    public static Player getPlayerFromEmail(String email) {
        return new PlayerDAO().getPlayerFromEmail(email);
    }

    public static Player persistPlayerToDatabase(Player player) {
        return new PlayerDAO().persistPlayerToDatabase(player);
    }

    public static boolean registerUser(Player usr) {
        boolean ret = false;

        if (usr != null && usr.getId() == null) {
            String email = UserDLO.getUserEmail(usr);
            if (email != null) {
                boolean checkEmail = Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
                if (checkEmail) {
                    Player p = getPlayerFromEmail(usr.getEmail());
                    if (p == null) {
                        persistPlayerToDatabase(usr);
                        ret = true;
                    }
                }
            }
        }

        return ret;
    }
}