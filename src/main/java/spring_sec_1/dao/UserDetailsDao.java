package spring_sec_1.dao;

import spring_sec_1.model.User;

import java.util.List;

public interface UserDetailsDao {
  User findUserByUsername(String username);

  void saveUser(User user);

  User getUserById(long id);

  List<User> getAllUsers();

  void editUser(User user);

  void deleteUser(long id);

}
