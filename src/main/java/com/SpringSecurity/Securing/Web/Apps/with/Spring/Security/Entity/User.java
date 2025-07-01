package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Entity;
import jakarta.persistence.*;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	No args constructor
	public User() {
		
	}
//	Args constructor
	public User(String username, String password, Role role) {
		this.username=username;
		this.password=password;
		this.role=role;
	}
	
//	getters and setters
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public Role getRole() {
	    return role;
	}

	public void setRole(Role role) {
	     this.role = role;
	}

}
