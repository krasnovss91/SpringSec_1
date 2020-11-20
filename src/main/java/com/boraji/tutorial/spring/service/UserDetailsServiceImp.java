package com.boraji.tutorial.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boraji.tutorial.spring.dao.UserDetailsDao;
import com.boraji.tutorial.spring.model.User;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService, com.boraji.tutorial.spring.service.UserDetailsService {

  /*
  @Autowired
  private UserDetailsDao userDetailsDao;
   */
  private UserDetailsDao userDetailsDao;

  @Autowired
  public void UserServiceImpl(UserDetailsDao userDetailsDao) {
    this.userDetailsDao = userDetailsDao;
  }

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userDetailsDao.findUserByUsername(username);
    UserBuilder builder = null;
    if (user != null) {
      
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.disabled(!user.isEnabled());
      builder.password(user.getPassword());
      String[] authorities = user.getAuthorities()
          .stream().map(a -> a.getAuthority()).toArray(String[]::new);

      builder.authorities(authorities);
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }

  @Override
  @Transactional
  public boolean findUserByName(String name){
    
  }
  @Override
  @Transactional
  public void saveUser(User user) {
    userDetailsDao.saveUser(user);
  }

  @Override
  @Transactional
  public User getUserById(long id) {
    return userDetailsDao.getUserById(id);
  }

  @Override
  @Transactional
  public List<User> getAllUsers() {
    return userDetailsDao.getAllUsers();
  }

  @Override
  @Transactional
  public void editUser(User user) {
    userDetailsDao.editUser(user);
  }

  @Override
  @Transactional
  public void deleteUser(long id) {
    userDetailsDao.deleteUser(id);
  }
}
