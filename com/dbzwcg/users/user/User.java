package com.dbzwcg.users.user;

import com.dbzwcg.users.authority.Authority;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author csiqueira
 */

@Entity(name = "com.dbzwcg.users.user.User")
@SequenceGenerator(name = User.USER_SEQUENCE_NAME, sequenceName = User.USER_SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@org.hibernate.annotations.Table(appliesTo = "USERS")
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    public static final String USER_SEQUENCE_NAME = "SEQ_USERS";
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns =
            @JoinColumn(name = "id_user"),
            inverseJoinColumns =
            @JoinColumn(name = "id_authority"))
    protected Set<Authority> roles;
    @Column(name = "ENABLE")
    protected Boolean enable;
    @Column(name = "EMAIL")
    protected String email;
    @Column(name = "PASSWORD")
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_SEQUENCE_NAME)
    @Column(name = "ID")
    protected Integer id;

    public Set<Authority> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Authority> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}