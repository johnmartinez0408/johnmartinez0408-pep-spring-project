package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){
        int status = 200;
        Account completedAccount = null;
        try{
            completedAccount = accountService.createAccount(account);
        }catch(SecurityException se){
            status=409; //Duplicate username
        }catch(IllegalArgumentException iae){
            status=400; //Argument rules not followed
        }
        return ResponseEntity.status(status).body(completedAccount);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account foundAccount = accountService.login(account);
        if(foundAccount!=null){
            return ResponseEntity.ok().body(foundAccount);
        }else{
            return ResponseEntity.status(401).body(new Account());
        }
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        int status = 200;
        Message insertedMessage = messageService.createMessage(message);
        if(insertedMessage == null){
            status = 400;
        }
        return ResponseEntity.status(status).body(insertedMessage);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.ok().body(messageService.getAllMessages());
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable("messageId") int messageId){
        return ResponseEntity.ok().body(messageService.getMessageById(messageId));
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable("messageId") int messageId){
        int deletedRows = 0;
        if(messageService.deleteById(messageId)){
            deletedRows =1;
            return ResponseEntity.ok().body(deletedRows);
        }else{
            return ResponseEntity.ok().body(null);
        }      
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> patchMessageById(@PathVariable("messageId") int messageId, @RequestBody Message message){
        if(message.getMessageId()==null){
            message.setMessageId(messageId);
        }
        Message patchedMessage = messageService.patchMessage(message);
        if(patchedMessage!=null){
            return ResponseEntity.ok().body(1);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable("accountId") int accountId){
        return ResponseEntity.ok().body(messageService.getMessagesByAccount(accountId));
    }
}
