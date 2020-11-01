package com.boraji.tutorial.spring.dao;

import com.boraji.tutorial.spring.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
