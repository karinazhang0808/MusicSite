package org.bs.service;

import java.util.List;
import org.bs.model.Member;

public interface MemberService {
	public void add(Member member);

	public void delete(Member member);

	public void update(Member member);

	public Member findById(int id);

	public Member findByUserId(int id);

	public List<Member> search();
}