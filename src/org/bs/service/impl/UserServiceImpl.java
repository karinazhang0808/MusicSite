package org.bs.service.impl;

import java.util.List;

import org.bs.dao.UserDao;
import org.bs.model.User;
import org.bs.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao;

	public void add(User user) {
		userDao.save(user);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void present(User user) {
		userDao.present(user);
	}

	public void updatePassword(User user) {
		userDao.updatePassword(user);
	}

	public User findById(int id) {
		return userDao.getById(id);
	}

	public User findByUserName(String username) {
		return userDao.getUserByUsername(username);
	}

	public List<User> search() {
		return userDao.query("");
	}

	public List<User> search(String name) {
		return userDao.query(name);
	}

	public List<User> searchPresent(String name) {
		return userDao.queryPresent(name);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
