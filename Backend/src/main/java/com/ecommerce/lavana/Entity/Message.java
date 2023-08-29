package com.ecommerce.lavana.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user")
    private String user;

    @Size(min = 5, message = "question must contain at least 5 characters")
    @Column(name = "title")
    private String title;

    @Size(min = 5, message = "question must contain at least 5 characters")
    @Column(name = "question")
    private String question;

    @Column(name = "admin")
    private String admin;

    @Size(min = 5, message = "response must contain at least 5 characters")
    @Column(name = "response")
    private String response;

    @Column(name = "closed")
    private boolean closed;

    public Message(String title, String question) {
        this.title = title;
        this.question = question;
    }
}
