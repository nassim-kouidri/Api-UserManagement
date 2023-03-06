package com.nassim.usermanagement.Controller;


import com.nassim.usermanagement.DTO.Request.LoginRequest;

import com.nassim.usermanagement.Model.Account;

import com.nassim.usermanagement.Service.AccountService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("apiSecured")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/allAccount")
    public List<Account> findAllAccount() {
        return accountService.findAll();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody Account account) {

        return accountService.saveAccount(account);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable("id") String id) {
        return accountService.findById(id);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return accountService.login(loginRequest);
    }

    @GetMapping ("/getAccountFromToken")
    public Account getAccountFromToken (@RequestHeader(HttpHeaders.AUTHORIZATION)  String token){
        return accountService.getAccountFromToken(token);
    }




}
