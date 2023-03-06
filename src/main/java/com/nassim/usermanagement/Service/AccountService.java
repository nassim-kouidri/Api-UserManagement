package com.nassim.usermanagement.Service;

import com.nassim.usermanagement.Configuration.CustomUserDetailsService;
import com.nassim.usermanagement.Configuration.JwtTokenUtil;
import com.nassim.usermanagement.Controller.AccountController;
import com.nassim.usermanagement.DTO.Request.LoginRequest;
import com.nassim.usermanagement.Model.Account;
import com.nassim.usermanagement.Model.Role;
import com.nassim.usermanagement.Repository.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestBody;


import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    public ResponseEntity<?> login( LoginRequest loginRequest)  {
        Account account = null;
        List<Account> accounts = findAll();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i=0; i<accounts.size(); i++) {
            if (accounts.get(i).getEmail().equals(loginRequest.getEmail())) {
                account = accounts.get(i);
            }
        }
        if (encoder.matches(loginRequest.getPassword(), account.getPassword())) {
            String token = jwtTokenUtil.generateAccessToken(accountRepository.findByEmail(loginRequest.getEmail()));
            logger.info("Token is : " + token);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return ResponseEntity.ok().body(map);
        } else {
            return new ResponseEntity<>("le mot de passe ou l'adresse mail est faux", HttpStatus.NOT_FOUND) ;
        }
    }


    public Account getAccountFromToken (String token){

        String cleanedToken = token.replace("Bearer ", "").trim();
        String email = jwtTokenUtil.getEmail(cleanedToken);
        return accountRepository.findByEmail(email);
    }











}
