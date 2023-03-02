package com.nassim.usermanagement.Service;

import com.nassim.usermanagement.Configuration.CustomUserDetailsService;
import com.nassim.usermanagement.Configuration.JwtTokenUtil;
import com.nassim.usermanagement.Model.Account;
import com.nassim.usermanagement.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public ResponseEntity<?> findById(String id){
        Optional<Account> e= accountRepository.findById(id);
        if(e.isPresent()){
            return new ResponseEntity<>(e.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("L'utilisateur n'existe pas", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> saveAccount(Account account) {
        Optional<Account> existingAccount = Optional.ofNullable(accountRepository.findByEmail(account.getEmail()));
        if (existingAccount.isPresent()) {
            return new ResponseEntity<>("l'email saisie est déjà utilisé, veuillez saisir une autre adresse email, merci", HttpStatus.NOT_FOUND);
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(account.getPassword());
        account.setPassword(encodedPassword);
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }






}
