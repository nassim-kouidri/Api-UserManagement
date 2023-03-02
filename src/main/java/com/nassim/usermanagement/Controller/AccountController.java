package com.nassim.usermanagement.Controller;

import com.nassim.usermanagement.Configuration.JwtTokenUtil;
import com.nassim.usermanagement.DTO.Request.LoginRequest;
import com.nassim.usermanagement.DTO.Response.LoginResponse;
import com.nassim.usermanagement.Model.Account;
import com.nassim.usermanagement.Model.Role;
import com.nassim.usermanagement.Repository.AccountRepository;
import com.nassim.usermanagement.Service.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("apiSecured/admin")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(AccountController.class);


    @GetMapping("/allAccount")
    public List<Account> findAllAccount(){
        return accountService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?>  register (@RequestBody  Account account){

        return accountService.saveAccount(account);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable("id") String id){
        return  accountService.findById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        Role userRole = null;
        Account account = null;
        List<Account> accounts = accountService.findAll();
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        for (int i=0;i<accounts.size();i++) {
            if(accounts.get(i).getEmail().equals(loginRequest.getEmail()) ) {
                account = accounts.get(i);
            }
        }
        if(loginRequest.getPassword().equals(accountRepository.findByEmail(loginRequest.getEmail()).getPassword()))  {
            String token = jwtTokenUtil.generateAccessToken(accountRepository.findByEmail(loginRequest.getEmail()));
            logger.info("Token is : " + token);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return ResponseEntity.ok().body(map);
        }
        throw new BadCredentialsException("");
    }



}
