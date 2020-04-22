package com.example.jogo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private WebApplicationContext applicationContext;
    private UserRepository userRepository;
 
    public MyUserDetailsService(){
    	super();
    }
    
    @PostConstruct
    public void completeSetup(){
    	userRepository = applicationContext.getBean(UserRepository.class);
    }
    
    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}