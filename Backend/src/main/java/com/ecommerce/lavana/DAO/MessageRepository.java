package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;


public interface MessageRepository extends JpaRepository<Message,Long> {

    Page<Message> findMessagesByUser(@RequestParam("user") String user, Pageable pageable);
}
