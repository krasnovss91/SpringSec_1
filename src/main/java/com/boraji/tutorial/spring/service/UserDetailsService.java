package com.boraji.tutorial.spring.service;

import com.boraji.tutorial.spring.model.User;

import java.util.List;

public interface UserDetailsService {
    public void saveUser(User user);
    public User getUserById(long id);
    public List<User> getAllUsers();
    public void editUser(User user);
    public void deleteUser(long id);
    public boolean findUserByName(String name);
}
