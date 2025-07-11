package com.library.zweibrary.AdminService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.zweibrary.LoginData.LoginAdmin;
import com.library.zweibrary.adminRepositories.Repositories;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private Repositories repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginAdmin> admin=repo.findByUsername(username);
        
        if(admin.isPresent()){
            var userObject=admin.get();
            return User.builder()
            .username(userObject.getUsername())
            .password(userObject.getPassword())
            .roles("USER")
            .build();
        }else{
            throw new UsernameNotFoundException("No User with that name");
        }
    }
    

}
