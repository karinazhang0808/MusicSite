package org.bs.dao;

import java.util.List;
import org.bs.model.Member;

public interface MemberDao {
	public void save(Member member);

	public void delete(int id);

	public void update(Member member);

	public Member getById(int id);

	public List<Member> query();

	public Member getByUserId(int id);
}