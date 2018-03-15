package org.bs.dao;

import java.util.List;

import org.bs.model.User;

public interface UserDao {

	public void save(User user);

	public void delete(int id);

	public void update(User user);

	public User getById(int id);

	public User getUserByUsername(String username);

	public List<User> query(String title);

	public List<User> query();

	public void updatePassword(User user);

	public void present(User user);

	public List<User> queryPresent(String name);
}
