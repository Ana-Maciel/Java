package com.example.jogo;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
 
    @Column(name = "username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="enabled")
    private boolean enabled = true;
    
    public User() {
    	
    }
    
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id){
    	this.id = id;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }
    
    public String getPassword(){
    	return password;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    public boolean isEnabled(){
    	return enabled;
    }
    
    public void setEnabled(boolean enabled){
    	this.enabled = enabled;
    }
    
    @Override
    public String toString(){
    	return "Jogo [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}