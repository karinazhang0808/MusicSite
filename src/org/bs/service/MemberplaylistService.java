package org.bs.service;

import java.util.List;
import org.bs.model.Memberplaylist;

public interface MemberplaylistService {
	public void add(Memberplaylist memberplaylist);

	public void delete(Memberplaylist memberplaylist);

	public void update(Memberplaylist memberplaylist);

	public Memberplaylist findById(int id);

	public Memberplaylist findByUserId(int id);

	public List<Memberplaylist> search();
}