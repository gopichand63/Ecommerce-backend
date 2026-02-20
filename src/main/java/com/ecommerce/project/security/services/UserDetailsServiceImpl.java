package com.ecommerce.project.security.services;

import com.ecommerce.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    Userrepository userrepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return UserDetailsImpl.build(user);
    }
}
