package org.bs.service.impl;

import java.util.List;
import org.bs.dao.MemberplaylistDao;
import org.bs.model.Memberplaylist;
import org.bs.service.MemberplaylistService;

public class MemberplaylistServiceImpl implements MemberplaylistService {
	MemberplaylistDao memberplaylistDao;

	public void add(Memberplaylist memberplaylist) {
		memberplaylistDao.save(memberplaylist);
	}

	public void delete(Memberplaylist memberplaylist) {
		memberplaylistDao.delete(memberplaylist.getId());
	}

	public void update(Memberplaylist memberplaylist) {
		memberplaylistDao.update(memberplaylist);
	}

	public Memberplaylist findById(int id) {
		return memberplaylistDao.getById(id);
	}

	public Memberplaylist findByUserId(int id) {
		return memberplaylistDao.getByUserId(id);
	}

	public List<Memberplaylist> search() {
		return memberplaylistDao.query();
	}

	public void setMemberplaylistDao(MemberplaylistDao memberplaylistDao) {
		this.memberplaylistDao = memberplaylistDao;
	}
}