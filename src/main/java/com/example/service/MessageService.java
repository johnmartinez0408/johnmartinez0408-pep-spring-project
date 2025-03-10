package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;;

@Service
public class MessageService {
    
    public MessageRepository messageRepository;
    public AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }
    
    public Message createMessage(Message message){
        //Null checks
        if(message!= null && message.getMessageText()!=null && message.getPostedBy() !=null){
            //Rule checks
            if(!message.getMessageText().equals("") && message.getMessageText().length() <=255){
                Account existingAccount = accountService.getAccountById(message.getPostedBy());
                if(existingAccount!=null){
                    messageRepository.save(message);
                    return message;
                } 
            }
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int id){
        Optional<Message> optMessage = messageRepository.findById(id);
        if(optMessage.isPresent()){
            return messageRepository.findById(id).get();
        }
        return null;
    }

    public boolean deleteById(int id){
        Message m = getMessageById(id);
        if(m!=null){
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Message patchMessage(Message message){
        //Null checks
        if(message!= null && message.getMessageText()!=null && message.getMessageId() !=null){
            //Rule checks
            if(!message.getMessageText().equals("") && message.getMessageText().length() <=255){
                Message existingDbMessage = getMessageById(message.getMessageId());
                //If there is a matching message in db
                if(existingDbMessage!=null){
                    existingDbMessage.setMessageText(message.getMessageText());
                    messageRepository.save(existingDbMessage);
                    return existingDbMessage;
                }
            }
        }
        return null;
    }

    public List<Message> getMessagesByAccount(int accountId){
        List<Message> messages = messageRepository.findByPostedBy(accountId);
        return messages;
    }
}
