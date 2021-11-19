package com.example.springBE;

import org.springframework.data.repository.CrudRepository;

import com.example.springBE.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
