package org.bs.service;

import java.util.List;

import org.bs.model.User;

public interface UserService {
	public void add(User user);

	public void delete(int id);

	public void update(User user);

	public void updatePassword(User user);

	public User findById(int id);

	public User findByUserName(String username);

	public List<User> search();

	public List<User> search(String name);

	public void present(User user);

	public List<User> searchPresent(String name);
}
