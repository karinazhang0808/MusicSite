package org.bs.service;

import java.util.List;
import org.bs.model.Memberloves;

public interface MemberlovesService {
	public void add(Memberloves memberloves);

	public void delete(Memberloves memberloves);

	public void update(Memberloves memberloves);

	public Memberloves findById(int id);

	public Memberloves findByUserId(int id);

	public List<Memberloves> search();
	public List<Memberloves> search(String str);
}