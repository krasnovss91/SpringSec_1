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
  public User getUserById(long id) {

    return entityManager.find(User.class, id);

  }

  @Override
  public List<User> getAllUsers() {
    return entityManager.createQuery("select e from User e", User.class).getResultList();
  }


  @Override
  public void editUser(User user) {
    entityManager.merge(user);
  }

  @Override
  public void deleteUser(long id) {
    User userToBeDeleted = entityManager.getReference(User.class, id);

    if (userToBeDeleted != null) {
      entityManager.remove(userToBeDeleted);
    }

  }
}
