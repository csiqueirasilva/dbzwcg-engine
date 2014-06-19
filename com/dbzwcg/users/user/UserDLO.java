/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.users.user;

import com.dbzwcg.services.authentication.CustomUserDetails;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author csiqueira
 */
public class UserDLO {

    public static String getUserPassword (User usr) {
        String password = null;
        
        if(usr != null) {
            try {
                Field f = User.class.getDeclaredField("password");
                password = (String) f.get(usr);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return password;
    }
    
    public static String getUserEmail (User usr) {
        String email = null;
        
        if(usr != null) {
            try {
                Field f = User.class.getDeclaredField("email");
                email = (String) f.get(usr);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserDLO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return email;
    }
    
    public static CustomUserDetails getAuthUserDetails(String username) {
        CustomUserDetails cud = new UserDAO().getUserDetails(username);
        return cud;
    }

    public static User getUserFromUsername(String username) {
        return new UserDAO().getUserFromUsername(username);
    }
    
    public static void persistUserToDatabase (User usr) {
        new UserDAO().persistUserToDatabase(usr);
    }
 
    public static void main (String args[]) {
        UserDLO.getAuthUserDetails("root@dbzwcg.com");
        System.out.println(UserDLO.encodePassword("root"));
    }
    
    public static String encodePassword (String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String ret = bcrypt.encode(password);
        return ret;
    }
}