package org.bs.dao;

import java.util.List;
import org.bs.model.Memberplaylist;

public interface MemberplaylistDao {
	public void save(Memberplaylist memberplaylist);

	public void delete(int id);

	public void update(Memberplaylist memberplaylist);

	public Memberplaylist getById(int id);

	public List<Memberplaylist> query();

	public Memberplaylist getByUserId(int id);
}