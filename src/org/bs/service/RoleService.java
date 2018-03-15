package org.bs.service;

import java.util.List;
import org.bs.model.Role;

public interface RoleService {
	public void add(Role role);

	public void delete(Role role);

	public void update(Role role);

	public Role findById(int id);

	public List<Role> search();
}