package com.hellokoding.account.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
	
	public enum NOMBRE_ROL implements GrantedAuthority
	{
	    USER("ROLE_USER"),
	    ADMIN("ROLE_ADMIN");

	    private String authority;

	    NOMBRE_ROL(String authority)
	    {
	        this.authority = authority;
	    }
	    public String getAuthority()
	    {
	        return this.authority;
	    }
	}
	
    private Long id;
    private String name;
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
