package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    public AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) throws SecurityException, IllegalArgumentException{
        //Null checks
        if(account!=null && account.getUsername()!=null && account.getPassword()!=null){
            //Check input rules are followed
            if(!account.getUsername().equals("") && account.getPassword().length()>3){
                //If there are no accounts with this username
                if(getAccountCountByUsername(account.getUsername())==0){
                    accountRepository.save(account);
                    return account;
                } else{
                    throw new SecurityException("username already taken");
                }
                
            }else{
                throw new IllegalArgumentException("Invalid input arguments");
            }
        }
        
        return null;
    }

    public Account login(Account account){
        Optional<Account> foundAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(foundAccount.isPresent()){
            return foundAccount.get();
        }
        return null;
    }

    public int getAccountCountByUsername(String username){
        return accountRepository.findByUsername(username).size();
    }

    public Account getAccountById(int id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }
}
