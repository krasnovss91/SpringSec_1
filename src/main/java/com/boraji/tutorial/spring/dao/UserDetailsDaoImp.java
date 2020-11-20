package com.boraji.tutorial.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao {

  @Autowired
 // private SessionFactory sessionFactory;
   //     @PersistenceContext
    private EntityManager entityManager;


  @Override
  public User findUserByUsername(String username) {
    return  entityManager.find(User.class, username);
  //  return sessionFactory.getCurrentSession().get(User.class, username);
  }

  @Override
  public void saveUser(User user) {
    entityManager.persist(user);
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
