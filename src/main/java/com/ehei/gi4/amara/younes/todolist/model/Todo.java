package com.ehei.gi4.amara.younes.todolist.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime targetDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userLogin", nullable = false)
    private User user;
    public Todo() {

    }

    public Todo(String description, LocalDateTime targetDate) {
        this.description = description;
        this.targetDate = targetDate;
    }

    public Todo(String description, LocalDateTime targetDate, User user) {
        this.description = description;
        this.targetDate = targetDate;
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public Todo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Todo setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public Todo setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Todo setUser(User user) {
        this.user = user;
        return this;
    }
}
