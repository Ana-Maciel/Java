package com.example.jogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
 
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled = true;
    
    private User() {
    	
    }
    
    private User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id){
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
}