package org.bs.service.impl;

import java.util.List;
import org.bs.dao.RoleDao;
import org.bs.model.Role;
import org.bs.service.RoleService;

public class RoleServiceImpl implements RoleService {
	RoleDao roleDao;

	public void add(Role role) {
		roleDao.save(role);
	}

	public void delete(Role role) {
		roleDao.delete(role.getId());
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public Role findById(int id) {
		return roleDao.getById(id);
	}

	public List<Role> search() {
		return roleDao.query();
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}