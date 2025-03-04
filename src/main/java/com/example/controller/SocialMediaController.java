package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(){
        //TODO
        return null;
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(){
        //TODO
        return null;
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(){
        //TODO
        return null;
    }

    @GetMapping("messages")
    public ResponseEntity<Message> getAllMessages(){
        //TODO
        return null;
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessageById(){
        //TODO
        return null;
    }

    @DeleteMapping("messages/{messageId}")
    public int deleteMessageById(){
        //TODO
        return 0;
    }

    @PatchMapping("messages/{messageId}")
    public int patchMessageById(){
        //TODO
        return 0;
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<Message> getMessagesByAccount(){
        //TODO
        return null;
    }
}
