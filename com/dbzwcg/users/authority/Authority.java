/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.users.authority;

import com.dbzwcg.users.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author csiqueira
 */
@Entity(name="com.dbzwcg.users.authority.Authority")
@SequenceGenerator(name = Authority.AUTHORITY_SEQUENCE_NAME, sequenceName = Authority.AUTHORITY_SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@org.hibernate.annotations.Table(appliesTo = "AUTHORITIES")
@Table(name = "AUTHORITIES", uniqueConstraints = {
@UniqueConstraint(columnNames = {"AUTHORITY"})})
public class Authority implements Serializable, GrantedAuthority {
    public static final String AUTHORITY_SEQUENCE_NAME = "SEQ_AUTHORITIES";

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY")
    protected AuthorityType authorityType;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AUTHORITY_SEQUENCE_NAME)
    @Column(name = "ID")
    protected Integer id;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns =
            @JoinColumn(name = "id_authority"),
            inverseJoinColumns =
            @JoinColumn(name = "id_user"))
    protected Set<User> users;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return this.authorityType.toString();
    }
    
    public AuthorityType getAuthorityType() {
        return this.authorityType;
    }
    
    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}