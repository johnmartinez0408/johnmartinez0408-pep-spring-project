package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Query("SELECT m FROM Message m WHERE m.postedBy = ?1")
    public List<Message> findByPostedBy(int postedBy);
}
