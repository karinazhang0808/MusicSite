package org.bs.dao;

import java.util.List;
import org.bs.model.Role;

public interface RoleDao {
	public void save(Role role);

	public void delete(int id);

	public void update(Role role);

	public Role getById(int id);

	public List<Role> query();
}