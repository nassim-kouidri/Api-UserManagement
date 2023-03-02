package com.nassim.usermanagement.Configuration;

import com.nassim.usermanagement.Model.Account;
import com.nassim.usermanagement.Model.Role;
import com.nassim.usermanagement.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword())); //if password are not encrypted in database.
        return new org.springframework.security.core.userdetails.User(
                account.getEmail(),
                account.getPassword(),
                getGrantedAuthorities(account.getRole())
        );

    }




    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //Spring Security automatically prefix role with ROLE_
        //so if the role name in database isn't prefix with ROLE_
        //we have to it
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
