package com.boraji.tutorial.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.model.User;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public User findUserByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }
  @Override
  @Transactional
  public void saveUser(User user) {
    userDao.saveUser(user);
  }

  @Override
  @Transactional
  public User getUserById(long id) {
    return userDao.getUserById(id);
  }

  @Override
  @Transactional
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @Override
  @Transactional
  public void editUser(User user) {
    userDao.editUser(user);
  }

  @Override
  @Transactional
  public void deleteUser(long id) {
    userDao.deleteUser(id);
  }
}
