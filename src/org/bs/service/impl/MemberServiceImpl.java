package org.bs.service.impl;

import java.util.List;
import org.bs.dao.MemberDao;
import org.bs.model.Member;
import org.bs.service.MemberService;

public class MemberServiceImpl implements MemberService {
	MemberDao memberDao;

	public void add(Member member) {
		memberDao.save(member);
	}

	public void delete(Member member) {
		memberDao.delete(member.getId());
	}

	public void update(Member member) {
		memberDao.update(member);
	}

	public Member findById(int id) {
		return memberDao.getById(id);
	}

	public Member findByUserId(int id) {
		return memberDao.getByUserId(id);
	}

	public List<Member> search() {
		return memberDao.query();
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}