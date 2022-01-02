package com.ehei.gi4.amara.younes.todolist.repository;

import com.ehei.gi4.amara.younes.todolist.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

//    @Query("select new Todo(t.description,t.targetDate) from Todo t where t.user.login=:login ")
    List<Todo> findByUserLogin(String  login);
}
