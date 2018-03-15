package org.bs.dao;

import java.util.List;
import org.bs.model.Memberloves;

public interface MemberlovesDao {
	public void save(Memberloves memberloves);

	public void delete(int id);

	public void update(Memberloves memberloves);

	public Memberloves getById(int id);

	public List<Memberloves> query();
	public List<Memberloves> query(String str);

	public Memberloves getByUserId(int id);
}