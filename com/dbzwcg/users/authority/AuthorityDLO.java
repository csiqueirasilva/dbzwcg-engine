/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.users.authority;

/**
 *
 * @author csiqueira
 */
public class AuthorityDLO {
    public static Authority getAuthorityByType(AuthorityType authorityName) {
        return new AuthorityDAO().getAuthorityByName(authorityName.toString());
    }
}
